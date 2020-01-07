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

import enterprise.framework.pojo.auth.user.SysAuthUserRoleVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;

@Table("sys_auth_user_role")
public class SysAuthUserRole extends DbBaseDO {

    public SysAuthUserRole() {

    }

    public SysAuthUserRole(SysAuthUserRoleVO sysAuthUserRoleVO) {
        this.user_role_id = sysAuthUserRoleVO.getUserRoleId();
        this.user_id = sysAuthUserRoleVO.getUserId();
        this.role_id = sysAuthUserRoleVO.getRoleId();
        this.is_deleted = sysAuthUserRoleVO.getIsDeleted();
    }

    @PrimaryKey("user_role_id")
    private Long user_role_id;

    private Long user_id;

    private Long role_id;

    private Integer is_deleted;

    public void setUserRoleId(Long userRoleId) {
        this.user_role_id = userRoleId;
    }

    public Long getUserRoleId() {
        return user_role_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setRoleId(Long roleId) {
        this.role_id = roleId;
    }

    public Long getRoleId() {
        return role_id;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.is_deleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return is_deleted;
    }
}
