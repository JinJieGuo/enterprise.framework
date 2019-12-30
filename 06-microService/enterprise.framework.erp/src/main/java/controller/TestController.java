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

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import enterprise.framework.business.engine.Handler;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.rabbitmq.IMQManager;
import enterprise.framework.core.rabbitmq.MQManager;
import enterprise.framework.core.rabbitmq.RabbitMqInfo;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.utility.generaltools.YmlPropUtils;
import enterprise.framework.utility.transform.MapHandler;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@RequestMapping("/erp/v1/test")
public class TestController {

    private static long count;

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

    @Autowired(required = false)
    public enterprise.framework.core.rabbitmq.RabbitMq rabbitMq;


    @RequestMapping("test")
    public HttpResponse Test() throws IOException, TimeoutException, InterruptedException {

        IMQManager imqManager = new MQManager();
        enterprise.framework.core.rabbitmq.RabbitMqInfo rabbitMqInfo = MapHandler.mapToObject(rabbitMq.getRabbitMqInfo().get(0), RabbitMqInfo.class);

        Connection rabbitMqConnection = imqManager.instance().createConnection(rabbitMq.getRabbitMqInfo().get(0));
//        Connection rabbitMqConnection1 = imqManager.instance().createConnection(rabbitMq.getRabbitMqInfo().get(1));

        List<Channel> channelList = new ArrayList<>();
        Map<String, Channel> channelMap = new HashMap<>();
        for (int i = 0; i < rabbitMqInfo.getChannelList().size(); i++) {
            Channel channel = rabbitMqConnection.createChannel();
            if (channelList.indexOf(channel) > 0) {
                continue;
            }
            channelList.add(channel);
//            String index = String.valueOf(i);
//            String queueName = rabbitMqInfo.getChannelList().get(index).toString();
//            channelMap.put(queueName, rabbitMqConnection.createChannel());
        }
        ExecutorService executor = new ThreadPoolExecutor(5, 5,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 100000000; i++) {
            count = i + 1;
            String index = String.valueOf(i + 1);
            String queueIndex = index.substring(index.length() - 1);
            String queueName = "";
            if (queueIndex.contains("1") || queueIndex.contains("6")) {
                Channel channel = channelList.get(0);
                queueName = rabbitMqInfo.getChannelList().get("0").toString();
                String message = "Hello World!";
                Future future = SendMsg(executor, channel, queueName, message);
                try {
                    future.get();//需要捕获两种异常
                    continue;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
            if (queueIndex.contains("2") || queueIndex.contains("7")) {
                Channel channel = channelList.get(1);
                queueName = rabbitMqInfo.getChannelList().get("1").toString();
                String message = "Hello World!";
                Future future = SendMsg(executor, channel, queueName, message);
                try {
                    future.get();//需要捕获两种异常
                    continue;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
            if (queueIndex.contains("3") || queueIndex.contains("8")) {
                Channel channel = channelList.get(2);
                queueName = rabbitMqInfo.getChannelList().get("2").toString();
                String message = "Hello World!";
                Future future = SendMsg(executor, channel, queueName, message);
                try {
                    future.get();//需要捕获两种异常
                    continue;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
            if (queueIndex.contains("4") || queueIndex.contains("9")) {
                Channel channel = channelList.get(3);
                queueName = rabbitMqInfo.getChannelList().get("3").toString();
                String message = "Hello World!";
                Future future = SendMsg(executor, channel, queueName, message);
                try {
                    future.get();//需要捕获两种异常
                    continue;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
            if (queueIndex.contains("5") || queueIndex.contains("0")) {
                Channel channel = channelList.get(4);
                queueName = rabbitMqInfo.getChannelList().get("4").toString();
                String message = "Hello World!";
                Future future = SendMsg(executor, channel, queueName, message);
                try {
                    future.get();//需要捕获两种异常
                    continue;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }

        }

//        for ()
//        if (rabbitMqConnection == rabbitMqConnection1) {
//            HttpResponse httpResponse = new HttpResponse();
//        }
        do {
            Thread.sleep(100000000L);
        } while (true);
//        return new HttpResponse();

    }

    public Future SendMsg(ExecutorService executor, Channel channel, String queueName, String message) throws InterruptedException {
        return executor.submit(() -> {
            try {
//                System.out.println(Thread.currentThread().getId());
                channel.queueDeclare(queueName, true, false, false, null);
                channel.basicPublish("", queueName, null, message.getBytes());
//                Thread.sleep(1000L);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
