/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:enterprise.framework.erp
 *		2.ClassName:SysAuthUser.java
 *		3.FunctionDescription:数据库模型 — 权限管理 — 用户
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
 *		gl				 2019-12-20		        1.00					新建
 *******************************************************************************/

package enterprise.framework.domain.auth;

//import enterprise.framework.pojo.auth.user.SysAuthUserVO;

import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import java.util.Date;

/**
 * 数据库模型 — 权限管理 — 用户
 *
 * @author gl
 * @date 2019年12月20日21:29:30
 */
@Table("sys_auth_user")
@NameStyle(Style.camelhumpAndLowercase)
@Component
public class SysAuthUser extends DbBaseDO {

    public SysAuthUser() {

    }

    public SysAuthUser(SysAuthUserVO sysAuthUserVO) {
        this.user_id = sysAuthUserVO.getUserId();
        this.login_name = sysAuthUserVO.getLoginName();
        this.password = sysAuthUserVO.getPassword();
        this.nick_name = sysAuthUserVO.getNickName();
        this.real_name = sysAuthUserVO.getRealName();
        this.head_portrait = sysAuthUserVO.getHeadPortrait();
        this.major = sysAuthUserVO.getMajor();
        this.classes = sysAuthUserVO.getClasses();
        this.stu_number = sysAuthUserVO.getStuNumber();
        this.email = sysAuthUserVO.getEmail();
        this.phone = sysAuthUserVO.getPhone();
        this.job = sysAuthUserVO.getJob();
        this.audit_state = sysAuthUserVO.getAuditState();
        this.is_enabled = sysAuthUserVO.getIsEnabled();
    }

    @PrimaryKey("user_id")
    private Integer user_id;

    private String login_name;

    private String password;

    private String nick_name;

    private String real_name;

    private String head_portrait;

    private String major;

    private String classes;

    private Long stu_number;

    private String email;

    private Long phone;

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

    public void setStuNumber(Long stuNumber) {
        this.stu_number = stuNumber;
    }

    public Long getStuNumber() {
        return stu_number;
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
