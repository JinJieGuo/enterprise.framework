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
 *		gl				 2020-01-27		      1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserAuthMenuDTO {
    private long menuId;

    private String text;

    private long parentId;

    private String i18n;

    private String menuSort;

    private String icon;

    private String link;

    private boolean isMenu;

    private boolean group;

    private boolean hideInBreadcrumb;

    private boolean hide;

    private List<UserAuthMenuDTO> children = new ArrayList<>();

    private List<UserAuthMenuButtonDTO> buttonDTOList = new ArrayList<>();

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public String getI18n() {
        return i18n;
    }

    public void setMenuSort(String menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuSort() {
        return menuSort;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setIsMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    public boolean getIsMenu() {
        return isMenu;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public boolean getGroup() {
        return group;
    }

    public void setHideInBreadcrumb(boolean hideInBreadcrumb) {
        this.hideInBreadcrumb = hideInBreadcrumb;
    }

    public boolean getHideInBreadcrumb() {
        return hideInBreadcrumb;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean getHide() {
        return hide;
    }

    public void setChildren(List<UserAuthMenuDTO> children) {
        this.children = children;
    }

    public List<UserAuthMenuDTO> getChildren() {
        return children;
    }

    public void setButtonDTOList(List<UserAuthMenuButtonDTO> buttonDTOList) {
        this.buttonDTOList = buttonDTOList;
    }

    public List<UserAuthMenuButtonDTO> getButtonDTOList() {
        return buttonDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthMenuDTO that = (UserAuthMenuDTO) o;
        return menuId == that.menuId &&
                parentId == that.parentId &&
                isMenu == that.isMenu &&
                group == that.group &&
                hideInBreadcrumb == that.hideInBreadcrumb &&
                hide == that.hide &&
                Objects.equals(text, that.text) &&
                Objects.equals(i18n, that.i18n) &&
                Objects.equals(menuSort, that.menuSort) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(link, that.link) &&
                Objects.equals(children, that.children) &&
                Objects.equals(buttonDTOList, that.buttonDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, text, parentId, i18n, menuSort, icon, link, isMenu, group, hideInBreadcrumb, hide, children, buttonDTOList);
    }
}
