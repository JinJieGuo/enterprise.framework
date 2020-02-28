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
 *		gl				 2020-01-01		        1.00					新建
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
import enterprise.framework.pojo.auth.user.SignInVO;
import enterprise.framework.pojo.auth.user.SignOutVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.service.auth.user.SysAuthUserService;
import enterprise.framework.utility.generaltools.TimeTypeEnum;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthHandler {

    @Autowired
    private static RedisTemplate redisTemplate;

    @Autowired(required = false)
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private static SysAuthUserService sysAuthUserService;

    @Autowired(required = false)
    private void setSysAuthUserService(SysAuthUserService sysAuthUserService) {
        AuthHandler.sysAuthUserService = sysAuthUserService;
    }

    /**
     * 用户注册
     *
     * @param sysAuthUserVO
     * @return
     */
    public HttpResponse register(SysAuthUserVO sysAuthUserVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
            HttpResponse listUserResponse = sysAuthUserService.listUserByLoginName(sysAuthUserVO.getLoginName());

            List<SysAuthUserVO> userList = (List<SysAuthUserVO>) listUserResponse.content;
            if (userList != null && userList.size() > 1) {
                httpResponse.msg = "该用户已存在";
                httpResponse.status = HttpStatus.USER_NOTEXIST.value();
                return httpResponse;
            }

            sysAuthUserVO.setPassword(sysAuthUserVO.getIsDefaultPassword() == null || sysAuthUserVO.getIsDefaultPassword() == 1 ? "123456" : sysAuthUserVO.getPassword());
            sysAuthUserVO.setPassword(Base64Utils.encode(RSAUtils.encryptByPublicKey(sysAuthUserVO.getPassword().getBytes("utf-8"), RSAUtils.getPublicKey(keyMap))));
            HttpResponse response = sysAuthUserService.saveUser(sysAuthUserVO);
            if (response.status == HttpStatus.SUCCESS.value()) {
                //用户信息保存成功后,为用户颁发令牌并写入缓存
                ITokenManager tokenManager = new TokenManager();
                String userId = ((SysAuthUserVO) response.content).getUserId().toString();
                TokenInfo tokenInfo = tokenManager.createToken(userId, keyMap, 30, TimeTypeEnum.MINUTE);
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
    public Map<String, Object> singleSignOn(SignInVO signInModel) {
        Map<String, Object> map = new HashMap<>();
        HttpResponse httpResponse = new HttpResponse();
        try {
//            SysAuthUser sysAuthUser = new SysAuthUser();
//            sysAuthUser.setLoginName(signInModel.getLoginName());
            HttpResponse response = sysAuthUserService.listUserByLoginName(signInModel.getLoginName());
            RedisHandler redisHandler = new RedisHandler(redisTemplate);

            if (response.status != HttpStatus.SUCCESS.value()) {
                map.put("response", response);
                return map;
            }

            List<SysAuthUserVO> userList = (List<SysAuthUserVO>) response.content;

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
            SysAuthUserVO tempUser = userList.get(0);
            SysAuthUserVO userInfo = tempUser;

            HttpResponse tokenInfoResponse = getTokenInfo("token_info:" + tempUser.getUserId());
            if (tokenInfoResponse.status != HttpStatus.SUCCESS.value() || tokenInfoResponse.content == null) {
                map.put("response", tokenInfoResponse);
                return map;
            }
            TokenInfo tokenInfo = (TokenInfo) tokenInfoResponse.content;
            ITokenManager tokenManager = new TokenManager();
            if (tokenManager.tokenInfoIsInvalid(tokenInfo)) {
                //用户令牌已失效,延长令牌有效时间
                boolean res = tokenManager.extendTokenTime(tokenInfo, 30, TimeTypeEnum.MINUTE);
            } else {//#1 BUG => 令牌有效期内验证用户redis
                HttpResponse userRedisResponse = redisHandler.get("user_info:" + tempUser.getUserId() + "");
                if (userRedisResponse.content != null && userRedisResponse.status == HttpStatus.SUCCESS.value()) {
                    httpResponse.msg = "用户已登录,请勿重复登录!";
                    httpResponse.status = HttpStatus.SUCCESS.value();
                    httpResponse.content = userInfo;
                    map.put("response", httpResponse);
                    map.put("token_info", tokenInfo);
                    return map;
                }
            }

            if (userInfo != null) {
                //验证密码
                byte[] userPasswordByte = Base64Utils.decode(userInfo.getPassword());
                byte[] passwordStr = RSAUtils.decryptByPrivateKey(userPasswordByte, tokenInfo.getPrivate_key());
                String userPassword = new String(passwordStr, "utf-8");
                if (userPassword.equals(signInModel.getPassword())) {
                    StrHandler strHandler = new StrHandler();
                    SysAuthUserVO tempUserInfo = new SysAuthUserVO();
                    tempUserInfo.setUserId(userInfo.getUserId());
                    tempUserInfo.setLoginName(userInfo.getLoginName());
                    tempUserInfo.setRealName(userInfo.getRealName());
                    tempUserInfo.setNickName(userInfo.getNickName());
                    tempUserInfo.setHeadPortrait(userInfo.getHeadPortrait());
                    tempUserInfo.setGender(userInfo.getUserId());
                    tempUserInfo.setMajor(userInfo.getMajor());
                    tempUserInfo.setClasses(userInfo.getClasses());
                    tempUserInfo.setStuNumber(userInfo.getStuNumber());
                    tempUserInfo.setEmail(userInfo.getEmail());
                    tempUserInfo.setPhone(userInfo.getPhone());
                    tempUserInfo.setJob(userInfo.getJob());
                    tempUserInfo.setPwdErrorCount(userInfo.getPwdErrorCount());
                    tempUserInfo.setLoginCount(userInfo.getLoginCount());
                    tempUserInfo.setRegisterTime(userInfo.getRegisterTime());
                    tempUserInfo.setLastLoginTime(userInfo.getLastLoginTime());
                    tempUserInfo.setSort(userInfo.getSort());
                    tempUserInfo.setAuditState(userInfo.getAuditState());
                    tempUserInfo.setIsEnabled(userInfo.getIsEnabled());
                    tempUserInfo.setIsDeleted(userInfo.getIsDeleted());
                    tempUserInfo.setCreatorId(userInfo.getCreatorId());
                    tempUserInfo.setCreatorName(userInfo.getCreatorName());
                    tempUserInfo.setCreateTime(userInfo.getCreateTime());
                    HttpResponse userInfoRedisResult = redisHandler.set("user_info:" + userInfo.getUserId() + "", strHandler.toBinary(JSON.toJSONString(userInfo)));
                    if (userInfoRedisResult.status == HttpStatus.SUCCESS.value()) {
                        //验证成功,记录token
                        map.put("token_info", tokenInfo);
                        httpResponse.msg = "登录成功";
                        httpResponse.status = HttpStatus.SUCCESS.value();
                        httpResponse.content = tempUserInfo;
                        userInfo.setLoginCount(userInfo.getLoginCount() == null ? 1 : userInfo.getLoginCount() + 1);
                        userInfo.setLastLoginTime(new Date());
                        sysAuthUserService.updateUser(userInfo);
                    } else {
                        httpResponse.msg = "用户信息缓存失败";
                        httpResponse.status = HttpStatus.ERROR.value();
                    }
                } else {
                    httpResponse.msg = "账户名或密码错误,请重新输入!";
                    httpResponse.status = HttpStatus.ERROR.value();
                    userInfo.setPwdErrorCount(userInfo.getPwdErrorCount() == null ? 1 : userInfo.getPwdErrorCount() + 1);
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

    public HttpResponse signOut(SignOutVO signOutVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            if (signOutVO.getUserId() == null && signOutVO.getUserId().equals("")) {
                httpResponse.msg = "用户主键不能为空";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            // 获取用户缓存信息并验证是否为空
            StrHandler strHandler = new StrHandler();
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            HttpResponse userInfoResponse = redisHandler.get("user_info:" + signOutVO.getUserId());
            if (userInfoResponse.status != HttpStatus.SUCCESS.value() || userInfoResponse.content == null) {
                httpResponse.msg = "获取用户缓存信息失败";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            // 用户缓存不为空,获取用户令牌并验证是否为空
            SysAuthUser sysAuthUser = JSON.parseObject(strHandler.binaryToStr((String) userInfoResponse.content), SysAuthUser.class);
            HttpResponse tokenInfoResponse = redisHandler.get("token_info:" + signOutVO.getUserId());
            if (tokenInfoResponse.status != HttpStatus.SUCCESS.value() || tokenInfoResponse.content == null) {
                httpResponse.msg = "获取用户令牌失败";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            // 用户旧令牌不为空,且将用户密码解密为明文
            TokenInfo oldTokenInfo = JSON.parseObject(strHandler.binaryToStr((String) tokenInfoResponse.content), TokenInfo.class);
            byte[] userPasswordByte = Base64Utils.decode(sysAuthUser.getPassword());
            byte[] passwordStr = RSAUtils.decryptByPrivateKey(userPasswordByte, oldTokenInfo.getPrivate_key());
            String userPassword = new String(passwordStr, "utf-8");

            // 根据用户主键重新生成token并保存至redis
            Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
            ITokenManager tokenManager = new TokenManager();
            TokenInfo tokenInfo = tokenManager.createToken(signOutVO.getUserId(), keyMap, 30, TimeTypeEnum.MINUTE);
            HttpResponse tokenRedisResult = redisHandler.set("token_info:" + signOutVO.getUserId(), strHandler.toBinary(JSON.toJSONString(tokenInfo)));

            if (tokenRedisResult.status != HttpStatus.SUCCESS.value()) {
                httpResponse.msg = "重新生成的用户令牌缓存失败";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            // 将用户明文密码根据新令牌重新生成密文并更新,更新成功后移除用户缓存信息
            sysAuthUser.setPassword(Base64Utils.encode(RSAUtils.encryptByPublicKey(userPassword.getBytes("utf-8"), tokenInfo.getPublic_key())));
            HttpResponse userResponse = sysAuthUserService.updateUser(sysAuthUser);

            if (userResponse.status != HttpStatus.SUCCESS.value()) {
                httpResponse.msg = "新令牌生成的密码更新失败";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            // 移除用户信息
            HttpResponse removeUserResponse = redisHandler.del("user_info:" + signOutVO.getUserId());
            if (removeUserResponse.status != HttpStatus.SUCCESS.value()) {
                httpResponse.msg = "旧用户信息移除失败";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }

            httpResponse.msg = "用户密码已重置且重新颁发令牌,用户登出成功";
            httpResponse.status = HttpStatus.SUCCESS.value();
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "用户退出登录异常:" + error.getMessage();
            return httpResponse;
        }
    }


    /**
     * 根据用户id获取token
     *
     * @param key
     * @return
     */
    private HttpResponse getTokenInfo(String key) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            HttpResponse tokenInfoResponse = redisHandler.get(key);
            if (tokenInfoResponse.status != HttpStatus.SUCCESS.value() || tokenInfoResponse.content == null) {
                httpResponse.msg = "该用户令牌丢失或用户不存在,请尝试重试账号密码!";
                httpResponse.status = HttpStatus.FAIL.value();
                return httpResponse;
            }
            StrHandler strHandler = new StrHandler();
            String tokenInfoJson = strHandler.binaryToStr((String) tokenInfoResponse.content);
            TokenInfo tokenInfo = JSON.parseObject(tokenInfoJson, TokenInfo.class);
            httpResponse.msg = "用户token获取成功";
            httpResponse.status = HttpStatus.SUCCESS.value();
            httpResponse.content = tokenInfo;
            return httpResponse;
        } catch (Exception error) {
            httpResponse.msg = "用户token获取异常:" + error.getMessage();
            httpResponse.status = HttpStatus.ERROR.value();
            return httpResponse;
        }
    }

}
