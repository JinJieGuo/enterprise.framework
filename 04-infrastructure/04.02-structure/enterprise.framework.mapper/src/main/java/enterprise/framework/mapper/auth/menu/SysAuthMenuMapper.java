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
 *		gl				 2020-01-05		        1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.auth.menu;

import enterprise.framework.domain.auth.SysAuthMenu;
import enterprise.framework.domain.auth.SysAuthRole;
import enterprise.framework.mapper.auth.role.SysAuthRoleGenerateSql;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface SysAuthMenuMapper extends Mapper<SysAuthMenu> {

    /**
     * 保存单个菜单
     *
     * @param sysAuthMenu
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "menu_id", keyColumn = "menu_id")
    @InsertProvider(type = SysAuthMenuGenerateSql.class, method = "generateSaveSql")
    int saveMenu(SysAuthMenu sysAuthMenu);

    /**
     * 更新单个角色
     *
     * @param sysAuthMenu
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "menu_id", keyColumn = "menu_id")
    @UpdateProvider(type = SysAuthMenuGenerateSql.class, method = "generateUpdateSql")
    int updateMenu(SysAuthMenu sysAuthMenu);

    @Select("SELECT  a.*,@rank:=@rank + 1 AS `index` \n" +
            "FROM\n" +
            "(\n" +
            "SELECT menu_id, parent_id, parent_name, menu_name, menu_code, navigate_url, icon, description, is_menu, sort, \n" +
            "CASE \n" +
            "\tWHEN audit_state = 1 THEN '通过'\n" +
            "\tWHEN audit_state = 2 THEN '拒绝'\n" +
            "END AS audit_state_text,\n" +
            "CASE WHEN is_enabled = 0 THEN '否' ELSE '是' END AS is_enabled_text,\n" +
            "CASE WHEN is_deleted = 0 THEN '否' ELSE '是' END AS is_deleted_text,\n" +
            "create_time, modify_time " +
            "FROM sys_auth_menu WHERE is_deleted = 0\n" +
            ")a, (SELECT @rank:= 0) b ORDER BY a.sort ASC")
    List<SysAuthMenuVO> listAllMenu();
}
