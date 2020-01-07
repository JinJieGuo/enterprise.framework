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

package enterprise.framework.pojo.auth.menu;

import enterprise.framework.utility.database.DbBaseVO;

public class SysAuthMenuButtonVO extends DbBaseVO {


    private Long menuButtonId;

    private Long menuId;

    private Long buttonId;

    public void setMenuButtonId(Long menuButtonId) {
        this.menuButtonId = menuButtonId;
    }

    public Long getMenuButtonId() {
        return menuButtonId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public Long getButtonId() {
        return buttonId;
    }
}
