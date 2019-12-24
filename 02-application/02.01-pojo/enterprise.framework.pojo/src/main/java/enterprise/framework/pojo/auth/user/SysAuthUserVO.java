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
 *		gl				 2019-12-24		      1.00					新建
 *******************************************************************************/

package enterprise.framework.pojo.auth.user;

import java.util.Date;

/**
 * 用户业务模型
 */
public class SysAuthUserVO {

    private Integer user_id;

    private String login_name;

    private String password;

    private Integer is_defaultPassword;

    private String nick_name;

    private String real_name;

    private String head_portrait;

    private String major;

    private String classes;

    private Integer stu_number;

    private String email;

    private Integer phone;

    private String job;

    private Integer pwd_error_count;

    private Integer login_count;

    private Date register_time;

    private Date last_login_time;

    private Integer sort;

    private Integer audit_state;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setLoginName(String loginName) {
        this.login_name = loginName;
    }

    public String getLoginName() {
        return login_name;
    }

    public void setIsDefaultPassword(Integer isDefaultPassword) {
        this.is_defaultPassword = isDefaultPassword;
    }

    public Integer getIsDefaultPassword() {
        return is_defaultPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNickName(String nickName) {
        this.nick_name = nickName;
    }

    public String getNickName() {
        return nick_name;
    }

    public void setRealName(String realName) {
        this.real_name = realName;
    }

    public String getRealName() {
        return real_name;
    }

    public void setHeadPortrait(String headPortrait) {
        this.head_portrait = headPortrait;
    }

    public String getHeadPortrait() {
        return head_portrait;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClasses() {
        return classes;
    }

    public void setStuNumber(Integer stuNumber) {
        this.stu_number = stuNumber;
    }

    public Integer getStuNumber() {
        return stu_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwd_error_count = pwdErrorCount;
    }

    public Integer getPwdErrorCount() {
        return pwd_error_count;
    }

    public void setLoginCount(Integer loginCount) {
        this.login_count = loginCount;
    }

    public Integer getLoginCount() {
        return login_count;
    }

    public void setRegisterTime(Date registerTime) {
        this.register_time = registerTime;
    }

    public Date getRegisterTime() {
        return register_time;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.last_login_time = lastLoginTime;
    }

    public Date getLastLoginTime() {
        return last_login_time;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
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
