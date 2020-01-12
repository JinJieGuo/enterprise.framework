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
 *		gl				 2020-01-04		        1.00					新建
 *******************************************************************************/

package controller.auth;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import enterprise.framework.service.auth.role.SysAuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth/role/")
public class RoleController {

    @Autowired
    private SysAuthRoleService sysAuthRoleService;

    /**
     * 保存按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("saveRole")
    public HttpResponse saveRole(@RequestBody SysAuthRoleVO sysAuthRoleVO) {
        return sysAuthRoleService.saveRole(sysAuthRoleVO);
    }

    /**
     * 更新按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("updateRole")
    public HttpResponse updateRole(@RequestBody SysAuthRoleVO sysAuthRoleVO) {
        return sysAuthRoleService.updateRole(sysAuthRoleVO);
    }

    /**
     * 删除按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("deleteRole")
    public HttpResponse deleteRole(@RequestBody SysAuthRoleVO sysAuthRoleVO) {
        return sysAuthRoleService.deleteRole(sysAuthRoleVO);
    }

    @ResponseBody
    @GetMapping("getRoleById")
    public HttpResponse getRoleById(SysAuthRoleVO sysAuthRoleVO) {
        return sysAuthRoleService.getRoleById(sysAuthRoleVO);
    }

    /**
     * 获取所有按钮
     *
     * @return
     */
    @ResponseBody
    @GetMapping("listAllRole")
    public HttpResponse listAllRole(SysAuthRoleVO sysAuthRoleVO) {
        return sysAuthRoleService.listAllRole();
    }

}
