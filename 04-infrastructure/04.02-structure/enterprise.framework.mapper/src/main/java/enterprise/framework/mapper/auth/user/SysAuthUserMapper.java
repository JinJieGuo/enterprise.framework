/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:SysAuthUserMapper.java
 *		3.FunctionDescription:权限管理 — 用户仓储
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
 *		gl				 2019-12-21		        1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.auth.user;

import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//@Mapper

/**
 * 权限管理 — 用户仓储
 */
@Component
public interface SysAuthUserMapper extends Mapper<SysAuthUser> {

    /**
     * 保存单个用户
     *
     * @param sysAuthUser
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    @InsertProvider(type = SysAuthUserGenerateSql.class, method = "generateSaveSql")
    int saveUser(SysAuthUser sysAuthUser);

    /**
     * 更新单个用户
     *
     * @param sysAuthUser
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    @UpdateProvider(type = SysAuthUserGenerateSql.class, method = "generateUpdateSql")
    int updateUser(SysAuthUser sysAuthUser);

    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("SELECT a.*,@rank:=@rank + 1 AS `index` FROM\n" +
            "(\n" +
            "SELECT user_id, login_name, nick_name, real_name, head_portrait, major, classes, stu_number, email, phone, job, pwd_error_count, login_count, register_time, last_login_time, sort, is_enabled, audit_state\n" +
            "FROM sys_auth_user WHERE is_deleted = 0\n" +
            ")a, (SELECT @rank:= 0) b")
    List<SysAuthUserVO> listAllUser();

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return
     */
    @Select("SELECT user_id, login_name, password, nick_name, real_name, head_portrait, gender, major, classes, stu_number, email, phone, job, pwd_error_count, login_count\n" +
            "\t\t , register_time, last_login_time, sort, audit_state, is_enabled, is_deleted, creator_id, creator_name, create_time\n" +
            "FROM sys_auth_user\n" +
            "WHERE is_deleted = 0 AND login_name = #{loginName}")
    List<SysAuthUserVO> getUserByLoginName(String loginName);
}


