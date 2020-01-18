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
import enterprise.framework.pojo.auth.role.RoleMenuButtonDTO;
import enterprise.framework.pojo.auth.role.RoleMenuDTO;
import enterprise.framework.pojo.auth.role.RoleUserDTO;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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
            "FROM sys_auth_role WHERE is_deleted = 0\n" +
            ")a, (SELECT @rank:= 0) b")
    List<SysAuthRoleVO> listAllRole();

    /**
     * 为角色分配用户 — 获取所有用户及该角色下已包含的用户
     *
     * @param roleId 角色主键
     * @return
     */
    @Select("SELECT  t1.user_id\n" +
            "\t\t\t, t1.login_name\n" +
            "\t\t\t, t1.real_name\n" +
            "\t\t\t, t1.nick_name\n" +
            "\t\t\t, (CASE WHEN t1.gender = 1 THEN 'man' WHEN t1.gender = 2 THEN 'woman' END) AS genderIcon\n" +
            "\t\t\t, (CASE WHEN t2.user_role_id IS NOT NULL THEN 1 ELSE 0 END)AS isChoosed\n" +
            "FROM sys_auth_user t1 \n" +
            "LEFT JOIN sys_auth_user_role t2 ON t2.user_id = t1.user_id AND t2.role_id = #{roleId}")
    List<RoleUserDTO> listRoleUser(long roleId);

    /**
     * 获取所有菜单及角色已选菜单
     *
     * @param roleId 角色主键
     * @return
     */
    @Select("SELECT DISTINCT t1.menu_id\n" +
            "\t\t\t, t1.menu_name\n" +
            "\t\t\t, t1.parent_id\n" +
            "\t\t\t, t1.menu_code\n" +
            "\t\t\t, t1.navigate_url\n" +
            "\t\t\t, t1.icon\n" +
            "\t\t\t, t1.description\n" +
            "\t\t\t, t1.is_menu\n" +
            "\t\t\t, t1.sort\n" +
            "\t\t\t, t1.is_deleted\n" +
            "\t\t\t, (CASE WHEN t2.role_menu_button_id IS NOT NULL THEN 1 ELSE 0 END) AS menuIsChecked\n" +
            "FROM sys_auth_menu t1\n" +
            "LEFT JOIN sys_auth_role_menu_button t2 ON t2.menu_id = t1.menu_id AND t2.is_deleted = 0 AND t2.role_id = #{roleId} AND t2.button_id IS NOT NULL\n" +
            "WHERE t1.is_deleted = 0\n" +
            "ORDER BY t1.sort")
    List<RoleMenuDTO> listRoleMenu(long roleId);

    /**
     * 获取菜单下所有权限及模块已选权限
     *
     * @param roleId 角色主键
     * @return
     */
    @Select("<script>SELECT  t2.button_id\n" +
            "\t\t\t, t2.button_name\n" +
            "\t\t\t, t2.icon as buttonIcon\n" +
            "\t\t\t, t2.tree_id \n" +
            "\t\t\t, t2.method\n" +
            "\t\t\t, t2.button_class\n" +
            "\t\t\t, t2.description\n" +
            "\t\t\t, t3.role_id\n" +
            "\t\t\t, t1.menu_id\n" +
            "\t\t\t, (CASE WHEN t3.role_menu_button_id IS NOT NULL THEN 1 ELSE 0 END) AS buttonIsChoosed\n" +
            "\t\t\t, (CASE WHEN t3.role_menu_button_id IS NOT NULL THEN 1 ELSE 0 END) AS buttonIsAllowClick\t\n" +
            "FROM sys_auth_menu_button t1\n" +
            "LEFT JOIN sys_auth_button t2 ON t2.button_id = t1.button_id\n" +
            "LEFT JOIN sys_auth_role_menu_button t3 ON t3.button_id = t2.button_id AND t3.menu_id = t1.menu_id AND t3.role_id = #{roleId} AND t3.is_deleted = 0\n" +
            "WHERE t1.is_deleted = 0 AND t2.is_deleted = 0 <if  test= \"menuId != null and menuId != ''\">  AND t1.menu_id = #{menuId} </if>\n" +
            "ORDER BY t2.sort</script>")
//    @MapKey("menuId")
    List<RoleMenuButtonDTO> listRoleMenuAuth(@Param("roleId") long roleId, @Param("menuId") long menuId);
}
