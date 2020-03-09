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
 *		gl				 2020-03-09		      1.00					新建
 *******************************************************************************/

package controller;

import enterprise.framework.core.http.HttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/file/")
public class FileCenterController {

//    @ResponseBody
//    @PostMapping("upload")
    @GetMapping("upload")
    public HttpResponse upload() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.msg = "成功";
        return httpResponse;
    }
}
