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

package enterprise.framework.core.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQHandler {

    private static ConnectionFactory connectionFactory;

    public Connection createConnection() throws IOException, TimeoutException {
        if (connectionFactory != null) {
            return connectionFactory.newConnection();
        }

        RabbitMq rabbitMq = new RabbitMq();
//        RabbitMqInfo rabbitMqInfo = new RabbitMqInfo();

        return connectionFactory.newConnection();
    }

}
