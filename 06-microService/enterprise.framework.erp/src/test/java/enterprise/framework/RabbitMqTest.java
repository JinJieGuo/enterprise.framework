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
 *		gl				 2019-12-26		      1.00					新建
 *******************************************************************************/

package enterprise.framework;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import enterprise.framework.core.rabbitmq.IMQManager;
import enterprise.framework.core.rabbitmq.MQManager;
import enterprise.framework.core.rabbitmq.RabbitMqInfo;
import enterprise.framework.erp.ErpApplication;
import enterprise.framework.utility.transform.MapHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApplication.class)
public class RabbitMqTest {

    @Autowired(required = false)
    public enterprise.framework.core.rabbitmq.RabbitMq rabbitMq;

    @Test
    public void ConnectionRabbitTest() throws IOException, TimeoutException {
        IMQManager imqManager = new MQManager();
//        imqManager.instance().createConnection();
    }

//    @Test
//    public void receivedMsg() throws IOException, TimeoutException, InterruptedException {
//        IMQManager imqManager = new MQManager();
//        enterprise.framework.core.rabbitmq.RabbitMqInfo rabbitMqInfo = MapHandler.mapToObject(rabbitMq.getRabbitMqInfo().get(0), RabbitMqInfo.class);
//        Connection rabbitMqConnection = imqManager.instance().createConnection(rabbitMq.getRabbitMqInfo().get(0));
//        Channel channel = rabbitMqConnection.createChannel();
////        channel.basicQos(0, 1, false);
////        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
////            String message = new String(delivery.getBody(), "UTF-8");
////            System.out.println(" [x] Received '" + message + "'");
////        };
////        channel.basicConsume("msg-server-alpha-01", true, deliverCallback, consumerTag -> {
////        });
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//        };
//        channel.basicConsume("msg-server-alpha-01", false, deliverCallback, consumerTag -> {
//        });
//        do {
//            Thread.sleep(100000000L);
//        } while (true);
//
//    }
}
