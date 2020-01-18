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
 *		gl				 2019-12-24		        1.00					新建
 *******************************************************************************/

package enterprise.framework;

import com.alibaba.fastjson.JSON;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.core.token.ITokenManager;
import enterprise.framework.core.token.TokenInfo;
import enterprise.framework.core.token.TokenManager;
import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.erp.ErpApplication;
import enterprise.framework.pojo.auth.user.SignInVO;
import enterprise.framework.service.auth.user.SysAuthUserService;
import enterprise.framework.utility.generaltools.TimeTypeEnum;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApplication.class)
public class AuthTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysAuthUserService sysAuthUserService;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


//    /**
//     * 初始化用户信息,并赋值token
//     *
//     * @throws Exception
//     */
//    @Test
//    public void initAdminData() throws Exception {
//        RedisHandler redisHandler = new RedisHandler(redisTemplate);
//        StrHandler strHandler = new StrHandler();
//        //初始化管理员令牌信息
//        ITokenManager tokenManager = new TokenManager();
//        Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
//        SysAuthUser user = new SysAuthUser();
//        user.setLoginName("admin");
//        user.setPassword(Base64Utils.encode(RSAUtils.decryptByPublicKey("123456".getBytes("utf-8"), RSAUtils.getPublicKey(keyMap))));
////        HttpResponse response = sysAuthUserService.updateUser(user);
////        sysAuthUserService.saveUser(user);
////        sysAuthUserService.createUser(user);
//        //用户保存成功后,为用户生成完整token信息,并缓存token
//        TokenInfo tokenInfo = tokenManager.createToken("33", keyMap, 30, TimeTypeEnum.MINUTE);
//        redisHandler.set("token_info:33", strHandler.toBinary(JSON.toJSONString(tokenInfo)));
//    }


    /**
     * 更新用户token
     */
    @Test
    public void updateUserToken() throws Exception {
        try {
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            StrHandler strHandler = new StrHandler();
            SignInVO signInModel = new SignInVO();
            signInModel.setLoginName("admin");

            Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
            SysAuthUser sysAuthUser = new SysAuthUser();
            sysAuthUser.setLoginName(signInModel.getLoginName());
            HttpResponse response = sysAuthUserService.listUserByParameters(signInModel.getLoginName());
            List<SysAuthUser> userList = (List<SysAuthUser>) response.content;
            SysAuthUser user = userList.get(0);
            byte[] pass_word_byte = RSAUtils.encryptByPublicKey("123456".getBytes("utf-8"), RSAUtils.getPublicKey(keyMap));
            user.setPassword(Base64Utils.encode(pass_word_byte));
            HttpResponse result = sysAuthUserService.updateUser(user);
            if (result.status == HttpStatus.SUCCESS.value()) {
                ITokenManager tokenManager = new TokenManager();
                TokenInfo tokenInfo = tokenManager.createToken(user.getUserId().toString(), keyMap, 30, TimeTypeEnum.MINUTE);
                HttpResponse tokenRedisResult = redisHandler.set("token_info:" + user.getUserId(), strHandler.toBinary(JSON.toJSONString(tokenInfo)));
            }
        } catch (Exception error) {

        }

    }

    @Test
    public void DateTest() {
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    public void verifyToken() {
        HttpResponse tokenInfoResponse = getTokenInfo("token_info:1");
        if (tokenInfoResponse.status == HttpStatus.SUCCESS.value()) {
            TokenInfo tokenInfo = (TokenInfo) tokenInfoResponse.content;
            ITokenManager tokenManager = new TokenManager();
            if (tokenManager.tokenInfoIsInvalid(tokenInfo)) {
                //用户令牌已失效
                boolean res = tokenManager.extendTokenTime(tokenInfo, 30, TimeTypeEnum.MINUTE);
            }
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
