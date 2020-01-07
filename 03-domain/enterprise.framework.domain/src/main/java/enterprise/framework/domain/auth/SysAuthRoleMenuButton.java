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
 *		gl				 2020-01-07		      1.00					新建
 *******************************************************************************/

package enterprise.framework.domain.auth;

import enterprise.framework.pojo.auth.role.SysAuthRoleMenuButtonVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;

@Table("sys_auth_role_menu_button")
public class SysAuthRoleMenuButton extends DbBaseDO {

    public SysAuthRoleMenuButton() {

    }

    public SysAuthRoleMenuButton(SysAuthRoleMenuButtonVO sysAuthRoleMenuButtonVO) {
        this.role_menu_button_id = sysAuthRoleMenuButtonVO.getRoleMenuButtonId();
        this.role_id = sysAuthRoleMenuButtonVO.getRoleId();
        this.menu_id = sysAuthRoleMenuButtonVO.getMenuId();
        this.button_id = sysAuthRoleMenuButtonVO.getButtonId();
        this.is_deleted = sysAuthRoleMenuButtonVO.getIsDeleted();
    }

    @PrimaryKey("role_menu_button_id")
    private Long role_menu_button_id;

    private Long role_id;

    private Long menu_id;

    private Long button_id;

    private Integer is_deleted;

    public void setRoleMenuButtonId(Long roleMenuButtonId) {
        this.role_menu_button_id = roleMenuButtonId;
    }

    public Long getRoleMenuButtonId() {
        return role_menu_button_id;
    }

    public void setRoleId(Long roleId) {
        this.role_id = roleId;
    }

    public Long getRoleId() {
        return role_id;
    }

    public void setMenuId(Long menuId) {
        this.menu_id = menuId;
    }

    public Long getMenuId() {
        return menu_id;
    }

    public void setButtonId(Long buttonId) {
        this.button_id = buttonId;
    }

    public Long getButtonId() {
        return button_id;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.is_deleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return is_deleted;
    }
}
