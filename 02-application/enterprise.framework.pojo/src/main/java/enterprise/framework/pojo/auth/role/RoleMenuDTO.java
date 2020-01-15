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

public class RoleMenuDTO {

    private long menuId;

    private String menuName;

    private String menuCode;

    private String navigateUrl;

    private String icon;

    private String description;

    private boolean isMenu;

    private int sort;

    private boolean isDeleted;

    private boolean menuIsChecked;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setNavigateUrl(String navigateUrl) {
        this.navigateUrl = navigateUrl;
    }

    public String getNavigateUrl() {
        return navigateUrl;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    public boolean getIsMenu() {
        return isMenu;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return sort;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setMenuIsChecked(boolean menuIsChecked) {
        this.menuIsChecked = menuIsChecked;
    }

    public boolean getMenuIsChecked() {
        return menuIsChecked;
    }
}
