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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Configuration
//@Component
//@EnableConfigurationProperties(RabbitMqInfo.class)
@ConfigurationProperties(prefix = "mq", ignoreInvalidFields = true)
public class RabbitMq {

    private List<Map<String, String>> infos;

    public void setInfos(List<Map<String, String>> infos) {
        this.infos = infos;
    }

    public List<Map<String, String>> getInfos() {
        return infos;
    }

    //    private List<RabbitMqInfo> infos;
//
//    public void setInfos(List<RabbitMqInfo> infos) {
//        this.infos = infos;
//    }
//
//    public List<RabbitMqInfo> getInfos() {
//        return infos;
//    }
}
