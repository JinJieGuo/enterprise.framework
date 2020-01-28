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

package enterprise.framework.mapper.auth.user;

import enterprise.framework.domain.auth.SysAuthUserRole;
import enterprise.framework.pojo.auth.user.SysAuthUserRoleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 权限管理 — 用户角色仓储
 */
@Component
public interface SysAuthUserRoleMapper extends Mapper<SysAuthUserRole> {

    /**
     * 批量添加用户角色
     *
     * @param sysAuthUserRoleVOList
     * @return
     */
    @Insert("<script>" +
            "INSERT INTO sys_auth_user_role (user_id, role_id) VALUES" +
            "<foreach collection='sysAuthUserRoleVOList' item='item' index='index' separator=','>" +
            "(#{item.userId}, #{item.roleId})" +
            "</foreach>" +
            "</script>")
    int saveUserRoleList(@Param(value = "sysAuthUserRoleVOList") List<SysAuthUserRoleVO> sysAuthUserRoleVOList);

    /**
     * 删除用户角色
     *
     * @param roleId
     * @return
     */
    @Update("UPDATE sys_auth_user_role SET is_deleted = 1 WHERE role_id = #{roleId}")
    int deleteUserRole(long roleId);
}
