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
 *		gl				 2020-01-04		        1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.auth.role;

import enterprise.framework.domain.auth.SysAuthRole;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAuthRoleMapper extends Mapper<SysAuthRole> {

    /**
     * 保存单个角色
     *
     * @param sysAuthRole
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "role_id", keyColumn = "role_id")
    @InsertProvider(type = SysAuthRoleGenerateSql.class, method = "generateSaveSql")
    int saveRole(SysAuthRole sysAuthRole);

    /**
     * 更新单个角色
     *
     * @param sysAuthRole
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "role_id", keyColumn = "role_id")
    @UpdateProvider(type = SysAuthRoleGenerateSql.class, method = "generateUpdateSql")
    int updateRole(SysAuthRole sysAuthRole);

    /**
     * 获取所有角色
     *
     * @return
     */
    @Select("SELECT  a.*,@rank:=@rank + 1 AS `index` \n" +
            "FROM\n" +
            "(\n" +
            "SELECT role_id, role_name, role_code, description, sort, \n" +
            "CASE \n" +
            "\tWHEN audit_state = 1 THEN '通过'\n" +
            "\tWHEN audit_state = 2 THEN '拒绝'\n" +
            "END AS audit_state_text,\n" +
            "CASE WHEN is_enabled = 0 THEN '否' ELSE '是' END AS is_enabled_text,\n" +
            "CASE WHEN is_deleted = 0 THEN '否' ELSE '是' END AS is_deleted_text\n" +
            "FROM sys_auth_role\n" +
            ")a, (SELECT @rank:= 0) b")
    List<SysAuthRoleVO> listAllRole();
}
