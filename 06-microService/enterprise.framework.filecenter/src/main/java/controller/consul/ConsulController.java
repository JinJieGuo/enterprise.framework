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
 *		gl				 2020-03-18		        1.00					新建
 *******************************************************************************/

package controller.consul;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul/")
public class ConsulController {
    @RequestMapping("health")
    public HttpResponse health() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.msg = "filecenter microservice is live";
        httpResponse.status = HttpStatus.SUCCESS.value();
        return httpResponse;
    }
}
