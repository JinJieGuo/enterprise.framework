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
import enterprise.framework.core.rabbitmq.IMQManager;
import enterprise.framework.core.rabbitmq.MQManager;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.utility.generaltools.YmlPropUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

    @Autowired
    public RabbitMq rabbitMq;

    @Autowired
    public RabbitMqInfo rabbitMqInfo;

    @Autowired
    private Test test;

    @Value("${test.temp}")
    private String temp;


    @RequestMapping("test")
    public HttpResponse Test() throws IOException, TimeoutException {

        //读取配置
//        https://blog.csdn.net/qq_35337467/article/details/81508685
//        RabbitMq rabbitMq = new RabbitMq();

//        IMQManager imqManager = new MQManager();
//        imqManager.instance().createConnection();
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
