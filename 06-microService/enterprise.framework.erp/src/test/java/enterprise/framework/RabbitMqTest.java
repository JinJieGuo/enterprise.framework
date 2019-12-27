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

import enterprise.framework.core.rabbitmq.IMQManager;
import enterprise.framework.core.rabbitmq.MQManager;
import enterprise.framework.erp.ErpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApplication.class)
public class RabbitMqTest {

    @Test
    public void ConnectionRabbitTest() throws IOException, TimeoutException {
        IMQManager imqManager = new MQManager();
        imqManager.instance().createConnection();
    }
}
