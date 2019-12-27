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

package controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Component
@ConfigurationProperties(value = "mq", ignoreInvalidFields = true)
public class RabbitMq {

    private List<RabbitMqInfo> info;

    public void setRabbitMqInfoList(List<RabbitMqInfo> info) {
        this.info = info;
    }

    public List<RabbitMqInfo> getRabbitMqInfoList() {
        return info;
    }
}
