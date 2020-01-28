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

package enterprise.framework.pojo.auth.role;

import java.util.List;

public class ChoosedRoleMenuButtonDTO {
    private long roleId;

    private List<SysAuthRoleMenuButtonVO> sysAuthRoleMenuButtonVOList;

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setSysAuthRoleMenuButtonVOList(List<SysAuthRoleMenuButtonVO> sysAuthRoleMenuButtonVOList) {
        this.sysAuthRoleMenuButtonVOList = sysAuthRoleMenuButtonVOList;
    }

    public List<SysAuthRoleMenuButtonVO> getSysAuthRoleMenuButtonVOList() {
        return sysAuthRoleMenuButtonVOList;
    }
}
