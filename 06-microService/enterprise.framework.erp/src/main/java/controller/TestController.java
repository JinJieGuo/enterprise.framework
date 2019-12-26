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
 *		gl				 2019-12-23		      1.00					新建
 *******************************************************************************/

package controller;

import enterprise.framework.business.engine.Handler;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.utility.generaltools.YmlPropUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/erp/v1/test")
public class TestController {
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
    @RequestMapping("test")
    public HttpResponse Test() throws FileNotFoundException {
        RedisHandler redisHandler = new RedisHandler(redisTemplate);
        Handler handler = new Handler();
        handler.HandlerTest();
        File file = new File(ResourceUtils.getURL("classpath:").getPath());
        String path1 = System.getProperty("user.dir");
        String path = file.getAbsolutePath();
        Object property = YmlPropUtils.getInstance().getProperty("spring.datasource.type");
        System.out.println(property);
        return new HttpResponse();

    }
}
