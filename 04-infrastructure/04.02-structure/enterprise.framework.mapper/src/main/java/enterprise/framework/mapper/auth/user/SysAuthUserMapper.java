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
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
    @InsertProvider(type = SysAuthUserGenerateSql.class, method = "generateUpdateSql")
    int updateUser(SysAuthUser sysAuthUser);

    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("SELECT * FROM sys_auth_user")
    List<SysAuthUser> listAllUser();
}


