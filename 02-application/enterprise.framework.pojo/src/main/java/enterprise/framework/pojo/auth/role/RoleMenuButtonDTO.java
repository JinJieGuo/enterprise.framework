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
 *		gl				 2020-01-15		        1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.role;

public class RoleMenuButtonDTO {

    private long buttonId;

    private String buttonName;

    private String buttonIcon;

    private long treeId;

    private String method;

    private String buttonClass;

    private String description;

    public long menuId;

    public void setButtonId(long buttonId) {
        this.buttonId = buttonId;
    }

    public long getButtonId() {
        return buttonId;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonIcon(String buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    public String getButtonIcon() {
        return buttonIcon;
    }

    public void setTreeId(long treeId) {
        this.treeId = treeId;
    }

    public long getTreeId() {
        return treeId;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId() {
        return menuId;
    }
}
