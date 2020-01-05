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

package enterprise.framework.domain.auth;

import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**
 * 权限管理 — 菜单
 */
@Table("sys_auth_menu")
@NameStyle(Style.camelhumpAndLowercase)
public class SysAuthMenu extends DbBaseDO {

    public SysAuthMenu() {

    }

    /**
     * 构造函数 - 用户业务实体转换为数据实体
     * @param sysAuthMenuVO
     */
    public SysAuthMenu(SysAuthMenuVO sysAuthMenuVO) {
        this.menu_id = sysAuthMenuVO.getMenuId();
        this.parent_id = sysAuthMenuVO.getParentId();
        this.parent_name = sysAuthMenuVO.getParentName();
        this.menu_name = sysAuthMenuVO.getMenuName();
        this.menu_code = sysAuthMenuVO.getMenuCode();
        this.navigate_url = sysAuthMenuVO.getNavigateUrl();
        this.icon = sysAuthMenuVO.getIcon();
        this.description = sysAuthMenuVO.getDescription();
        this.is_menu = sysAuthMenuVO.getIsMenu();
        this.sort = sysAuthMenuVO.getSort();
        this.audit_state = sysAuthMenuVO.getAuditState();
        this.is_enabled = sysAuthMenuVO.getIsEnabled();
        this.is_deleted = sysAuthMenuVO.getIsDeleted();
    }

    @PrimaryKey("menu_id")
    private Long menu_id;

    private Long parent_id;

    private String parent_name;

    private String menu_name;

    private String menu_code;

    private String navigate_url;

    private String icon;

    private String description;

    private Integer is_menu;

    private Long sort;

    private Integer audit_state;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setMenuId(Long menuId) {
        this.menu_id = menuId;
    }

    public Long getMenuId() {
        return menu_id;
    }

    public void setParentId(Long parentId) {
        this.parent_id = parentId;
    }

    public Long getParentId() {
        return parent_id;
    }

    public void setParentName(String parentName) {
        this.parent_name = parentName;
    }

    public String getParentName() {
        return parent_name;
    }

    public void setMenuName(String menuName) {
        this.menu_name = menuName;
    }

    public String getMenuName() {
        return menu_name;
    }

    public void setMenuCode(String menuCode) {
        this.menu_code = menuCode;
    }

    public String getMenuCode() {
        return menu_code;
    }

    public void setNavigateUrl(String navigateUrl) {
        this.navigate_url = navigateUrl;
    }

    public String getNavigateUrl() {
        return navigate_url;
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
        this.is_menu = isMenu;
    }

    public Integer getIsMenu() {
        return is_menu;
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
