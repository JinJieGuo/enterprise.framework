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
 *		gl				 2020-01-14		        1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.menu;

import java.util.List;

public class ChoosedButtonDTO {

    private long menuId;

    private List<SysAuthMenuButtonVO> sysAuthMenuButtonVOList;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setSysAuthMenuButtonVOList(List<SysAuthMenuButtonVO> sysAuthMenuButtonVOList) {
        this.sysAuthMenuButtonVOList = sysAuthMenuButtonVOList;
    }

    public List<SysAuthMenuButtonVO> getSysAuthMenuButtonVOList() {
        return sysAuthMenuButtonVOList;
    }
}
