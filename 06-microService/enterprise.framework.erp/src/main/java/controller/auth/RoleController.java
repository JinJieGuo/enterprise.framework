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

import controller.BaseController;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.role.ChoosedRoleMenuButtonDTO;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.service.auth.role.SysAuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/v1/auth/role/")
public class RoleController extends BaseController {

    @Autowired
    private SysAuthRoleService sysAuthRoleService;

    /**
     * 保存按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("saveRole")
    public HttpResponse saveRole(@RequestBody SysAuthRoleVO sysAuthRoleVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthRoleVO.setCreatorId(userInfo.getUserId());
        sysAuthRoleVO.setCreatorName(userInfo.getRealName());
        return sysAuthRoleService.saveRole(sysAuthRoleVO);
    }

    /**
     * 保存角色下勾选的菜单与按钮权限
     *
     * @param choosedRoleMenuButtonDTO
     * @return
     */
    @ResponseBody
    @PostMapping("saveRoleMenuButton")
    public HttpResponse saveRoleMenuButton(@RequestBody ChoosedRoleMenuButtonDTO choosedRoleMenuButtonDTO) {
        return sysAuthRoleService.saveRoleMenuButton(choosedRoleMenuButtonDTO);
    }

    /**
     * 更新按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("updateRole")
    public HttpResponse updateRole(@RequestBody SysAuthRoleVO sysAuthRoleVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthRoleVO.setModifierId(userInfo.getUserId());
        sysAuthRoleVO.setModifierName(userInfo.getRealName());
        sysAuthRoleVO.setModifyTime(new Date());
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
    public HttpResponse listAllRole(SysAuthRoleVO sysAuthRoleVO, HttpServletRequest request) {
        return sysAuthRoleService.listAllRole();
    }

    /**
     * 为角色分配用户 — 获取所有用户及该角色下已包含的用户
     *
     * @param roleId 角色主键
     * @return
     */
    @ResponseBody
    @GetMapping("listRoleUser")
    public HttpResponse listRoleUser(long roleId) {
        return sysAuthRoleService.listRoleUser(roleId);
    }

    /**
     * 根据角色获取所有菜单权限(包括已选和所有权限)
     *
     * @param roleId 角色主键
     * @return
     */
    @ResponseBody
    @GetMapping("listRoleMenuAuth")
    public HttpResponse listRoleMenuAuth(long roleId, long menuId) {
        return sysAuthRoleService.listRoleMenuAuth(roleId, menuId);
    }

}
