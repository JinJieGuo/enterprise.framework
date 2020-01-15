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

public class RoleUserDTO {

    private Long userId;

    private String loginName;

    private String realName;

    private String nickName;

    private String genderIcon;

    private boolean isExist;

    private boolean isChoosed;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setGenderIcon(String genderIcon) {
        this.genderIcon = genderIcon;
    }

    public String getGenderIcon() {
        return genderIcon;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }

    public boolean getIsExist() {
        return isExist;
    }

    public void setIsChoosed(boolean isChoosed) {
        this.isChoosed = isChoosed;
    }

    public boolean getIsChoosed() {
        return isChoosed;
    }
}
