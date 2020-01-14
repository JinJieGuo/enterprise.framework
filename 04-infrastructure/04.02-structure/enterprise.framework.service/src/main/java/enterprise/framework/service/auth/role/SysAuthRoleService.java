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

package enterprise.framework.service.auth.role;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;

public interface SysAuthRoleService {

    /**
     * 保存角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    HttpResponse saveRole(SysAuthRoleVO sysAuthRoleVO);

    /**
     * 更新角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    HttpResponse updateRole(SysAuthRoleVO sysAuthRoleVO);

    /**
     * 删除角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    HttpResponse deleteRole(SysAuthRoleVO sysAuthRoleVO);

    /**
     * 根据角色主键获取角色信息
     *
     * @param sysAuthRoleVO
     * @return
     */
    HttpResponse getRoleById(SysAuthRoleVO sysAuthRoleVO);

    /**
     * 获取所有用户
     *
     * @return
     */
    HttpResponse listAllRole();

    /**
     * 为角色分配用户 — 获取所有用户及该角色下已包含的用户
     *
     * @param roleId 角色主键
     * @return
     */
    HttpResponse listRoleUser(long roleId);
}
