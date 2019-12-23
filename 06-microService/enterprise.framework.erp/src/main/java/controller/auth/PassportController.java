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
 *		gl				 2019-12-21		        1.00					新建
 *******************************************************************************/

package controller.auth;

import enterprise.framework.business.engine.Components;
import enterprise.framework.business.engine.IScheduler;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.user.RegisterModel;
import enterprise.framework.pojo.auth.user.SignInModel;
import enterprise.framework.service.auth.user.SysAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth/passport/")
public class PassportController {

    @Autowired(required = false)
    private SysAuthUserService sysAuthUserService;

    IScheduler scheduler = new Components();

    /**
     * 用户注册
     *
     * @param registerModel
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public HttpResponse register(@RequestBody RegisterModel registerModel) {
        return new HttpResponse();
    }

    /**
     * 用户登录
     *
     * @param signInModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public HttpResponse signIn(@RequestBody SignInModel signInModel) {
//        sysAuthUserService.listAllUser()
        scheduler.singleSignOnManager().instance().Test();
        return new HttpResponse();
    }

    /**
     * 用户登出
     *
     * @return
     */
    public HttpResponse signOut() {
        return new HttpResponse();
    }
}
