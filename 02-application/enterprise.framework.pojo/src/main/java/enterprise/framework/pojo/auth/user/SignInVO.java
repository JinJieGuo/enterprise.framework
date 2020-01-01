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
 *		gl				 2020-01-01		        1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInVO {
    @JsonProperty(value = "user_id")
    private String user_id;

    @JsonProperty(value = "login_name")
    private String login_name;

    private String user_name;

    @JsonProperty(value = "password")
    private String password;

    private String verify_code;

    private String confirm_password;

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getUserId() {
        return user_id;
    }

    public void setLoginName(String loginName) {
        this.login_name = loginName;
    }

    public String getLoginName() {
        return login_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getUserName() {
        return user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirm_password = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setVerifyCode(String verifyCode) {
        this.verify_code = verifyCode;
    }

    public String getVerifyCode() {
        return verify_code;
    }
}
