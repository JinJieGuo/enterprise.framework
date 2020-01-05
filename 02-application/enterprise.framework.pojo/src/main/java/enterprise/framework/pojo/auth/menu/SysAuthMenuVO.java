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
 *		gl				 2020-01-05		        1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.menu;

import enterprise.framework.utility.database.DbBaseVO;

/**
 * 权限管理 — 菜单业务实体
 */
public class SysAuthMenuVO extends DbBaseVO {
    private Long menuId;

    private Long parentId;

    private String parentName;

    private String menuName;

    private String menuCode;

    private String navigateUrl;

    private String icon;

    private String description;

    private Integer isMenu;

    private Long sort;

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
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

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

}
