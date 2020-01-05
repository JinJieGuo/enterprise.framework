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
 *		gl				 2020-01-02		        1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.database;

public class DbBaseVO {

    private Integer index;

    private Integer auditState;

    private String auditStateText;

    private String isEnabledText;

    private String IsDeletedText;

    private Integer isEnabled;

    private Integer isDeleted;

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setAuditStateText(String auditStateText) {
        this.auditStateText = auditStateText;
    }

    public String getAuditStateText() {
        return auditStateText;
    }

    public void setIsEnabledText(String isEnabledText) {
        this.isEnabledText = isEnabledText;
    }

    public String getIsEnabledText() {
        return isEnabledText;
    }

    public void setIsDeletedText(String isDeletedText) {
        IsDeletedText = isDeletedText;
    }

    public String getIsDeletedText() {
        return IsDeletedText;
    }
}
