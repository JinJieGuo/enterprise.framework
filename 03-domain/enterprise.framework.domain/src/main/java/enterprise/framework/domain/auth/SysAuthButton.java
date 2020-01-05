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

package enterprise.framework.domain.auth;

import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

@Table("sys_auth_button")
@NameStyle(Style.camelhumpAndLowercase)
public class SysAuthButton extends DbBaseDO {

    public SysAuthButton() {

    }

    public SysAuthButton(SysAuthButtonVO sysAuthButtonVO) {
        this.button_id = sysAuthButtonVO.getButtonId();
        this.button_name = sysAuthButtonVO.getButtonName();
        this.button_code = sysAuthButtonVO.getButtonCode();
        this.tree_id = sysAuthButtonVO.getTreeId();
        this.icon = sysAuthButtonVO.getIcon();
        this.method = sysAuthButtonVO.getMethod();
        this.button_class = sysAuthButtonVO.getButtonClass();
        this.description = sysAuthButtonVO.getDescription();
        this.sort = sysAuthButtonVO.getSort();
        this.audit_state = sysAuthButtonVO.getAuditState();
        this.is_enabled = sysAuthButtonVO.getIsEnabled();
        this.is_deleted = sysAuthButtonVO.getIsDeleted();
    }

    @PrimaryKey("button_id")
    private Long button_id;

    private String button_name;

    private String button_code;

    private Long tree_id;

    private String icon;

    private String method;

    private String button_class;

    private String description;

    private Long sort;

    private Integer audit_state;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setButtonId(Long buttonId) {
        this.button_id = buttonId;
    }

    public Long getButtonId() {
        return button_id;
    }

    public void setButtonName(String buttonName) {
        this.button_name = buttonName;
    }

    public String getButtonName() {
        return button_name;
    }

    public void setButtonCode(String buttonCode) {
        this.button_code = buttonCode;
    }

    public String getButtonCode() {
        return button_code;
    }

    public void setTreeId(Long treeId) {
        this.tree_id = treeId;
    }

    public Long getTreeId() {
        return tree_id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setButtonClass(String buttonClass) {
        this.button_class = buttonClass;
    }

    public String getButtonClass() {
        return button_class;
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
