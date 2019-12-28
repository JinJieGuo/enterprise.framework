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
 *		gl				 2019-12-28		      1.00					新建
 *******************************************************************************/

package controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
//, ignoreInvalidFields = true
@ConfigurationProperties(prefix = "myapp.mail")
public class MailModuleProperties {
//    private List<String> smtpServers;
//
//    public void setSmtpServers(List<String> smtpServers) {
//        this.smtpServers = smtpServers;
//    }



    private List<Map<String, String>> smtpServers;

    public void setSmtpServers(List<Map<String, String>> smtpServers) {
        this.smtpServers = smtpServers;
    }

    public List<Map<String, String>> getSmtpServers() {
        return smtpServers;
    }
}
