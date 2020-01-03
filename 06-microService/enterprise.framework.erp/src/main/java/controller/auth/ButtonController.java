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
 *		gl				 2020-01-03		      1.00					新建
 *******************************************************************************/

package controller.auth;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import enterprise.framework.service.auth.button.SysAuthButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth/button/")
public class ButtonController {

    @Autowired
    private SysAuthButtonService sysAuthButtonService;

    /**
     * 保存按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("saveButton")
    public HttpResponse saveButton(@RequestBody SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthButtonService.saveButton(sysAuthButtonVO);
    }

    /**
     * 更新按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("updateButton")
    public HttpResponse updateButton(@RequestBody SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthButtonService.updateButton(sysAuthButtonVO);
    }

    /**
     * 删除按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("deleteButton")
    public HttpResponse deleteButton(@RequestBody SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthButtonService.deleteButton(sysAuthButtonVO);
    }

    @ResponseBody
    @GetMapping("getButton")
    public HttpResponse getButton(SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthButtonService.getButtonById(sysAuthButtonVO);
    }

    /**
     * 获取所有按钮
     *
     * @return
     */
    @ResponseBody
    @GetMapping("listAllButton")
    public HttpResponse listAllButton(SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthButtonService.listAllButton();
    }
}
