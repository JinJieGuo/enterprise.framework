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
 *		gl				 2020-01-07		      1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.auth.menu;

import enterprise.framework.domain.auth.SysAuthMenu;
import enterprise.framework.domain.auth.SysAuthMenuButton;
import enterprise.framework.pojo.auth.menu.ChoosedButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface SysAuthMenuButtonMapper extends Mapper<SysAuthMenuButton> {

    /**
     * 保存单个菜单
     *
     * @param sysAuthMenu
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "menu_button_id", keyColumn = "menu_button_id")
    @InsertProvider(type = SysAuthMenuButtonGenerateSql.class, method = "generateSaveSql")
    int saveMenu(SysAuthMenuButton sysAuthMenu);

    @Insert("<script>" +
            "INSERT INTO sys_auth_menu_button (menu_id, button_id) VALUES" +
            "<foreach collection='sysAuthMenuButtonVOList' item='item' index='index' separator=','>" +
            "(#{item.menuId}, #{item.buttonId})" +
            "</foreach>" +
            "</script>")
    int saveMenuButtonList(@Param(value = "sysAuthMenuButtonVOList") List<SysAuthMenuButtonVO> sysAuthMenuButtonVOList);

    /**
     * 更新单个角色
     *
     * @param sysAuthMenu
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "menu_button_id", keyColumn = "menu_button_id")
    @UpdateProvider(type = SysAuthMenuButtonGenerateSql.class, method = "generateUpdateSql")
    int updateMenu(SysAuthMenuButton sysAuthMenu);

    /**
     * 逻辑删除菜单下的按钮
     *
     * @param menuId
     * @return
     */
    @Update("\tUPDATE sys_auth_menu_button SET is_deleted = 1 WHERE menu_id = #{menuId}")
    int deleteMenuButton(long menuId);

    @Select("SELECT  t3.button_id AS chooseButtonId\n" +
            "\t\t\t, t3.button_name AS chooseButtonName\n" +
            "\t\t\t, t4.button_id, t4.button_name, t4.icon\n" +
            "\t\t\t, (CASE WHEN t3.button_id IS NOT NULL THEN 1 ELSE 0 END)AS isChecked\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT t1.button_id, t2.button_name FROM sys_auth_menu_button t1\n" +
            "\tLEFT JOIN sys_auth_button t2 ON t2.button_id = t1.button_id\n" +
            "\tWHERE t1.menu_id = #{menuId} AND t1.is_deleted = 0\n" +
            ")t3\n" +
            "RIGHT JOIN sys_auth_button t4 ON t4.button_id = t3.button_id")
    List<ChoosedButtonVO> listMenuButton(int menuId);
}
