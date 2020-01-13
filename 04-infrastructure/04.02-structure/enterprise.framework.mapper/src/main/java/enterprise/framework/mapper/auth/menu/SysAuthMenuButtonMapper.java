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

import enterprise.framework.domain.auth.SysAuthMenuButton;
import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysAuthMenuButtonMapper {

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
}
