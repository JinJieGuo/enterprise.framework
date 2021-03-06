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
import enterprise.framework.core.token.TokenInfo;
import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.pojo.auth.user.SignInVO;
import enterprise.framework.pojo.auth.user.SignOutVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.service.auth.user.SysAuthUserService;
import enterprise.framework.utility.generaltools.PrefixEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth/passport/")
public class PassportController {

    @Autowired(required = false)
    private SysAuthUserService sysAuthUserService;

    IScheduler businessScheduler = new Components();

//    /**
//     * 用户注册
//     *
//     * @param sysAuthUserVO
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "register")
//    public HttpResponse register(@RequestBody SysAuthUserVO sysAuthUserVO) throws Exception {
//
////        SysAuthUser sysAuthUser = new SysAuthUser(sysAuthUserVO);
//        return businessScheduler.singleSignOnManager().instance().register(sysAuthUserVO);
//    }

    /**
     * 用户登录
     *
     * @param signInModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public ResponseEntity<HttpResponse> signIn(@RequestBody SignInVO signInModel) {
        HttpHeaders responseHeaders = new HttpHeaders();
        Map<String, Object> map = businessScheduler.authManager().instance().singleSignOn(signInModel);
        HttpResponse httpResponse = (HttpResponse) map.get("response");
        if (httpResponse.status == enterprise.framework.core.http.HttpStatus.SUCCESS.value()) {
            TokenInfo tokenInfo = (TokenInfo) map.get(PrefixEnum.TOKENINFO.toString());
            if (tokenInfo != null) {//# BUG => 用户重复登录时,分发上一次token与公钥
                responseHeaders.set("authorization", tokenInfo.getToken_str());
                responseHeaders.set("public_key", tokenInfo.getPublic_key());
            }
        }
        return new ResponseEntity(httpResponse, responseHeaders, HttpStatus.OK);
    }

    /**
     * 用户登出
     *
     * @return
     */
    @ResponseBody
    @PostMapping("signOut")
    public HttpResponse signOut(@RequestBody SignOutVO signOutModel) {
        return businessScheduler.authManager().instance().signOut(signOutModel);
    }
}
