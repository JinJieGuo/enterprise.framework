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

public class ChoosedButtonVO {
    private Integer chooseButtonId;

    private String chooseButtonName;

    private Integer buttonId;

    private String buttonName;

    private String icon;

    private Integer buttonState;

    private boolean isChecked;

    public void setChooseButtonId(Integer chooseButtonId) {
        this.chooseButtonId = chooseButtonId;
    }

    public Integer getChooseButtonId() {
        return chooseButtonId;
    }

    public void setChooseButtonName(String chooseButtonName) {
        this.chooseButtonName = chooseButtonName;
    }

    public String getChooseButtonName() {
        return chooseButtonName;
    }

    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    public Integer getButtonId() {
        return buttonId;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setButtonState(Integer buttonState) {
        this.buttonState = buttonState;
    }

    public Integer getButtonState() {
        return buttonState;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getIsChecked() {
        return isChecked;
    }
}
