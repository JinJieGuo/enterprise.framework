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

package enterprise.framework.domain.auth;

import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import java.util.Date;

@Table("sys_auth_role")
@NameStyle(Style.camelhumpAndLowercase)
public class SysAuthRole extends DbBaseDO {

    //nested exception is org.apache.ibatis.executor.ExecutorException: No constructor found
    public SysAuthRole() {

    }

    public SysAuthRole(SysAuthRoleVO sysAuthRoleVO) {
        this.role_id = sysAuthRoleVO.getRoleId();
        this.role_name = sysAuthRoleVO.getRoleName();
        this.role_code = sysAuthRoleVO.getRoleCode();
        this.description = sysAuthRoleVO.getDescription();
        this.sort = sysAuthRoleVO.getSort();
        this.audit_state = sysAuthRoleVO.getAuditState();
        this.is_enabled = sysAuthRoleVO.getIsEnabled();
        this.is_deleted = sysAuthRoleVO.getIsDeleted();
        this.setCreatorId(sysAuthRoleVO.getCreatorId());
        this.setCreatorName(sysAuthRoleVO.getCreatorName());
        this.setModifierId(sysAuthRoleVO.getModifierId());
        this.setModifierName(sysAuthRoleVO.getModifierName());
        this.setModifyTime(sysAuthRoleVO.getModifyTime());
    }

    @PrimaryKey("role_id")
    private Long role_id;

    private String role_name;

    private String role_code;

    private String description;

    private Long sort;

    private Integer audit_state;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setRoleId(Long roleId) {
        this.role_id = roleId;
    }

    public Long getRoleId() {
        return role_id;
    }

    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleCode(String roleCode) {
        this.role_code = roleCode;
    }

    public String getRoleCode() {
        return role_code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    public void setAuditState(Integer auditState) {
        this.audit_state = auditState;
    }

    public Integer getAuditState() {
        return audit_state;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.is_enabled = isEnabled;
    }

    public Integer getIsEnabled() {
        return is_enabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.is_deleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return is_deleted;
    }
}
