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
import enterprise.framework.pojo.auth.user.SignInVO;
import enterprise.framework.pojo.auth.user.SignOutVO;
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
            userInfo.setPassword(Base64Utils.encode(RSAUtils.encryptByPublicKey(userInfo.getPassword().getBytes("utf-8"), RSAUtils.getPublicKey(keyMap))));
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
}
