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
 *		gl				 2020-01-17		        1.00					新建
 *******************************************************************************/

package controller;

import com.alibaba.fastjson.JSON;
//import com.sun.istack.internal.NotNull;
//import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.NotNull;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.utility.generaltools.PrefixEnum;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(@NotNull RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


    protected SysAuthUserVO currentUserInfo(HttpServletRequest request) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUserVO sysAuthUserVO = new SysAuthUserVO();
            String user_id = request.getHeader("id");
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            HttpResponse response = redisHandler.get(PrefixEnum.USERINFO.toString() + ":" + user_id);
            if (response.status == HttpStatus.SUCCESS.value()) {
                StrHandler strHandler = new StrHandler();
                sysAuthUserVO = JSON.parseObject(strHandler.binaryToStr(response.content.toString()), SysAuthUserVO.class);
            }
            return sysAuthUserVO;
        } catch (Exception error) {
            httpResponse.msg = error.getMessage();
            httpResponse.status = HttpStatus.ERROR.value();
            return null;
        }
    }
}
