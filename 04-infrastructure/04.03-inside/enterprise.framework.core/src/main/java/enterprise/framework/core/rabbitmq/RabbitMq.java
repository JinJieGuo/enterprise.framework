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
 *		gl				 2019-12-27		      1.00					新建
 *******************************************************************************/

package enterprise.framework.core.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq", ignoreInvalidFields = true)
public class RabbitMq {

    private List<Map<String, Object>> rabbitMqInfo;

    public void setRabbitMqInfo(List<Map<String, Object>> rabbitMqInfo) {
        this.rabbitMqInfo = rabbitMqInfo;
    }

    public List<Map<String, Object>> getRabbitMqInfo() {
        return rabbitMqInfo;
    }

    //    private List<RabbitMqInfo> rabbitmqInfo;
//
//    public void setRabbitMqInfoList(List<RabbitMqInfo> rabbitmqInfo) {
//        this.rabbitmqInfo = rabbitmqInfo;
//    }
//
//    public List<RabbitMqInfo> getRabbitMqInfoList() {
//        return rabbitmqInfo;
//    }
}
