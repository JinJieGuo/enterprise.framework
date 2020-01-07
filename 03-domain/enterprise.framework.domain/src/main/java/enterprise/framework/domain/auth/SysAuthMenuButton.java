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

import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

@Table("sys_auth_menu_button")
@NameStyle(Style.camelhumpAndLowercase)
public class SysAuthMenuButton extends DbBaseDO {

    public SysAuthMenuButton() {

    }

    public SysAuthMenuButton(SysAuthMenuButtonVO sysAuthMenuButtonVO) {
        this.menu_button_id = sysAuthMenuButtonVO.getMenuButtonId();
        this.menu_id = sysAuthMenuButtonVO.getMenuId();
        this.button_id = sysAuthMenuButtonVO.getButtonId();
        this.is_deleted = sysAuthMenuButtonVO.getIsDeleted();
    }

    @PrimaryKey("menu_button_id")
    private Long menu_button_id;

    private Long menu_id;

    private Long button_id;

    private Integer is_deleted;

    public void setMenuButtonId(Long menuButtonId) {
        this.menu_button_id = menuButtonId;
    }

    public Long getMenuButtonId() {
        return menu_button_id;
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

    public Integer getIs_deleted() {
        return is_deleted;
    }
}
