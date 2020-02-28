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
import enterprise.framework.pojo.auth.user.UserAuthDTO;
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
    List<SysAuthUserVO> listUserByLoginName(String loginName);

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
//    @Select("SELECT DISTINCT t3.menu_id, t3.menu_name AS text, t3.parent_id, t3.menu_code AS i18n, t3.sort AS menuSort, t3.icon, t3.navigate_url AS link, t3.is_menu, t3.is_show_group AS 'group'\n" +
//            "\t\t\t\t\t\t\t, t3.is_hide_in_breadcrumb AS hideInBreadcrumb, t3.is_hide AS hide, t4.button_id, t4.button_name, t4.icon AS buttonIcon, t4.button_class, t4.sort AS buttonSort, t4.method\n" +
//            "FROM sys_auth_user_role t1\n" +
//            "LEFT JOIN sys_auth_role_menu_button t2 ON t2.role_id = t1.role_id AND t2.is_deleted = 0\n" +
//            "LEFT JOIN sys_auth_menu t3 ON t3.menu_id = t2.menu_id \n" +
//            "LEFT JOIN sys_auth_button t4 ON t4.button_id = t2.button_id\n" +
//            "WHERE t1.is_deleted = 0 AND t1.user_id = #{userId}\n" +
//            "ORDER BY t3.sort, t4.sort")
    // 修复BUG=> 菜单按钮去掉某个菜单下的按钮,在角色管理的设置权限中,仍然能看到删除的按钮
    @Select("SELECT DISTINCT t2.is_deleted, t3.menu_id, t3.menu_name AS text, t3.parent_id, t3.menu_code AS i18n, t3.sort AS menuSort, t3.icon, t3.navigate_url AS link, t3.is_menu, t3.is_show_group AS 'group'\n" +
            "            , t3.is_hide_in_breadcrumb AS hideInBreadcrumb, t3.is_hide AS hide, t4.button_id, t4.button_name, t4.icon AS buttonIcon, t4.button_class, t4.sort AS buttonSort, t4.method\n" +
            "            FROM sys_auth_user_role t1\n" +
            "            LEFT JOIN sys_auth_role_menu_button t2 ON t2.role_id = t1.role_id AND t2.is_deleted = 0\n" +
            "            LEFT JOIN sys_auth_menu t3 ON t3.menu_id = t2.menu_id\n" +
            "            LEFT JOIN (SELECT t6.menu_id, t7.button_id, t7.button_name, t7.icon, t7.button_class, t7.sort, t7.method FROM sys_auth_menu_button t6\n" +
            "LEFT JOIN sys_auth_button t7 ON t7.button_id = t6.button_id\n" +
            "WHERE t6.is_deleted = 0 AND t7.is_deleted = 0) t4 ON t4.menu_id = t2.menu_id AND t4.button_id = t2.button_id\n" +
            "            WHERE t1.is_deleted = 0 AND t1.user_id = #{userId}\n" +
            "            ORDER BY t3.sort, t4.sort")
    List<UserAuthDTO> listUserAuth(int userId);
}


