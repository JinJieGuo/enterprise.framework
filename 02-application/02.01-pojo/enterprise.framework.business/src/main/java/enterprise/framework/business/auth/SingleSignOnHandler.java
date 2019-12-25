/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:HttpManager.cs
 *		3.FunctionDescription:核心组件 — 模拟请求处理器
 *		4.Call:
 *		5.CalledBy:
 *		6.TableAccessed:
 *		7.TableUpdated:
 *		8.Input:
 *		9.Output:
 *	    10.Return:
 *       11.Others:
 * EditResume:
 *	   Author				Date			  version			   ChangeContent
 *		gl				 2019-12-23		        1.00					新建
 *******************************************************************************/

package enterprise.framework.business.auth;

import com.alibaba.fastjson.JSON;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.core.token.ITokenManager;
import enterprise.framework.core.token.TokenInfo;
import enterprise.framework.core.token.TokenManager;
import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.pojo.auth.user.SignInModel;
import enterprise.framework.pojo.auth.user.SignOutModel;
import enterprise.framework.service.auth.user.SysAuthUserService;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SingleSignOnHandler {

    @Autowired
    private static RedisTemplate redisTemplate;

    @Autowired
    private static SysAuthUserService sysAuthUserService;

    @Autowired(required = false)
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Autowired(required = false)
    private void setSysAuthUserService(SysAuthUserService sysAuthUserService) {
        SingleSignOnHandler.sysAuthUserService = sysAuthUserService;
    }

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    public HttpResponse register(SysAuthUser userInfo) throws Exception {
        HttpResponse httpResponse = new HttpResponse();
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
            userInfo.setPassword(Base64Utils.encode(RSAUtils.decryptByPublicKey(userInfo.getPassword().getBytes("utf-8"), RSAUtils.getPublicKey(keyMap))));
            HttpResponse response = sysAuthUserService.saveUser(userInfo);
            if (response.status == HttpStatus.SUCCESS.value()) {
                //用户信息保存成功后,为用户颁发令牌并写入缓存
                ITokenManager tokenManager = new TokenManager();
                String userId = (String) response.content;
                TokenInfo tokenInfo = tokenManager.createToken(userId, keyMap);
                RedisHandler redisHandler = new RedisHandler(redisTemplate);
                StrHandler strHandler = new StrHandler();
                HttpResponse redisResponse = redisHandler.set("token_info:" + userId, strHandler.toBinary(JSON.toJSONString(tokenInfo)));
                if (redisResponse.status != HttpStatus.SUCCESS.value()) {
                    redisResponse.msg = "用户保存成功,但令牌写入缓存失败:" + redisResponse.msg;
                    return redisResponse;
                }
                httpResponse.msg = "用户保存成功,且已颁发令牌并已写入缓存";
                httpResponse.status = HttpStatus.SUCCESS.value();
                return httpResponse;
            }
            httpResponse.msg = "用户保存失败";
            httpResponse.status = HttpStatus.FAIL.value();
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 单点登录
     *
     * @param signInModel
     * @return
     */
    public Map<String, Object> singleSignOn(SignInModel signInModel) {
        Map<String, Object> map = new HashMap<>();
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUser sysAuthUser = new SysAuthUser();
            sysAuthUser.setLoginName(signInModel.getLoginName());
            HttpResponse response = sysAuthUserService.listUserByParameters(sysAuthUser);
            RedisHandler redisHandler = new RedisHandler(redisTemplate);

            if (response.status != HttpStatus.SUCCESS.value()) {
                map.put("response", response);
                return map;
            }

            List<SysAuthUser> userList = (List<SysAuthUser>) response.content;

            if (userList.size() == 0) {
                httpResponse.msg = "该用户不存在,请确认用户名是否正确!";
                httpResponse.status = HttpStatus.USER_NOTEXIST.value();
                map.put("response", httpResponse);
                return map;
            }

            if (userList.size() > 1) {
                httpResponse.msg = "该用户存在多个,请联系管理员确认!";
                httpResponse.status = HttpStatus.ERROR.value();
                map.put("response", httpResponse);
                return map;
            }
            SysAuthUser tempUser = userList.get(0);

            HttpResponse tokenInfoResponse = redisHandler.get("token_info:" + tempUser.getUserId() + "");
            if (tokenInfoResponse.status != HttpStatus.SUCCESS.value() || tokenInfoResponse.content == null) {
                httpResponse.msg = "该用户令牌丢失或用户不存在,请尝试重试账号密码!";
                httpResponse.status = HttpStatus.ERROR.value();
                map.put("response", httpResponse);
                return map;
            }
            StrHandler strHandler = new StrHandler();
            String tokenInfoJson = strHandler.binaryToStr((String) tokenInfoResponse.content);
            TokenInfo tokenInfo = JSON.parseObject(tokenInfoJson, TokenInfo.class);
            SysAuthUser userInfo = tempUser;

            HttpResponse userRedisResponse = redisHandler.get("user_info:" + tempUser.getUserId() + "");
            if (userRedisResponse.content != null && userRedisResponse.status == HttpStatus.SUCCESS.value()) {
                httpResponse.msg = "用户已登录,请勿重复登录!";
                httpResponse.status = HttpStatus.SUCCESS.value();
                map.put("response", httpResponse);
                map.put("token_info", tokenInfo);
                return map;
            }

            if (userInfo != null) {
                //验证密码
                byte[] userPasswordByte = Base64Utils.decode(userInfo.getPassword());
                byte[] passwordStr = RSAUtils.decryptByPrivateKey(userPasswordByte, tokenInfo.getPrivate_key());
                String userPassword = new String(passwordStr, "utf-8");
                if (userPassword.equals(signInModel.getPassword())) {
                    HttpResponse userInfoRedisResult = redisHandler.set("user_info:" + userInfo.getUserId() + "", strHandler.toBinary(JSON.toJSONString(userInfo)));
                    if (userInfoRedisResult.status == HttpStatus.SUCCESS.value()) {
                        //验证成功,记录token
                        map.put("token_info", tokenInfo);
                        httpResponse.msg = "登录成功";
                        httpResponse.status = HttpStatus.SUCCESS.value();
                        httpResponse.content = userInfo;
                        userInfo.setLoginCount(userInfo.getLoginCount() + 1);
                        userInfo.setLastLoginTime(new Date());
                        sysAuthUserService.updateUser(userInfo);
                    } else {
                        httpResponse.msg = "用户信息缓存失败";
                        httpResponse.status = HttpStatus.ERROR.value();
                    }
                } else {
                    httpResponse.msg = "账户名或密码错误,请重新输入!";
                    httpResponse.status = HttpStatus.ERROR.value();
                    userInfo.setPwdErrorCount(userInfo.getPwdErrorCount() + 1);
                    sysAuthUserService.updateUser(userInfo);
                }
            }
            map.put("response", httpResponse);
            return map;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = error.getMessage();
            map.put("response", httpResponse);
            return map;
        }
    }

    /**
     * 用户登出
     *
     * @param signOutModel
     * @return
     */
    public HttpResponse signOut(SignOutModel signOutModel) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            String userKey = "user_info" + signOutModel.getUserId();
            HttpResponse userInfoResponse = redisHandler.get(userKey);
            if (userInfoResponse.status == HttpStatus.SUCCESS.value() && userInfoResponse.content != null) {
                HttpResponse removeUserResponse = redisHandler.del(userKey);
                if (removeUserResponse.status == HttpStatus.SUCCESS.value()) {
                    Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
                    ITokenManager tokenManager = new TokenManager();
                    StrHandler strHandler = new StrHandler();
                    TokenInfo tokenInfo = tokenManager.createToken(signOutModel.getUserId(), keyMap);
                    HttpResponse tokenRedisResult = redisHandler.set("token_info:" + signOutModel.getUserId(), strHandler.toBinary(JSON.toJSONString(tokenInfo)));
                    if (tokenRedisResult.status == HttpStatus.SUCCESS.value()) {
                        httpResponse.status = HttpStatus.SUCCESS.value();
                        httpResponse.msg = "用户登出成功且重新颁发令牌";
                        return httpResponse;
                    }
                    httpResponse.status = HttpStatus.FAIL.value();
                    httpResponse.msg = "用户登出成功,令牌颁发失败!";
                    return httpResponse;
                } else {
                    httpResponse.status = HttpStatus.FAIL.value();
                    httpResponse.msg = "用户登出失败";
                    return httpResponse;
                }
            } else {
                return userInfoResponse;
            }
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "用户登出异常";
            return httpResponse;
        }
    }
}
