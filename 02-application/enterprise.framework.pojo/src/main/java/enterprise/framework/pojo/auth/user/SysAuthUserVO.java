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

import com.fasterxml.jackson.annotation.JsonProperty;
import enterprise.framework.utility.database.DbBaseVO;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import java.util.Date;

/**
 * 用户业务模型
 */
//@NameStyle(Style.camelhumpAndLowercase)
public class SysAuthUserVO extends DbBaseVO {

    @JsonProperty("userId")
    private Integer userId;

    private String parameters;

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getParameters() {
        return parameters;
    }

    private String loginName;

    private String password;

    private Integer isDefaultPassword;

    private String nickName;

    private String realName;

    private String headPortrait;

    private Integer gender;

    private String major;

    private String classes;

    private Long stuNumber;

    private String email;

    private Long phone;

    private String job;

    private Integer pwdErrorCount;

    private Integer loginCount;

    private Date registerTime;

    private Date lastLoginTime;

    private Integer sort;

    private Integer auditState;

    private Integer isEnabled;

    private Integer isDeleted;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setIsDefaultPassword(Integer isDefaultPassword) {
        this.isDefaultPassword = isDefaultPassword;
    }

    public Integer getIsDefaultPassword() {
        return isDefaultPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
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

    public void setStuNumber(Long stuNumber) {
        this.stuNumber = stuNumber;
    }

    public Long getStuNumber() {
        return stuNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPhone() {
        return phone;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }
}
