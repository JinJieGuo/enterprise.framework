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
 *		gl				 2020-01-03		      1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.auth.button;

import enterprise.framework.domain.auth.SysAuthButton;
import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.mapper.auth.user.SysAuthUserGenerateSql;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface SysAuthButtonMapper extends Mapper<SysAuthButton> {

    /**
     * 保存单个按钮
     *
     * @param sysAuthButton
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "button_id", keyColumn = "button_id")
    @InsertProvider(type = SysAuthButtonGenerateSql.class, method = "generateSaveSql")
    int saveButton(SysAuthButton sysAuthButton);

    /**
     * 更新单个按钮
     *
     * @param sysAuthButton
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "button_id", keyColumn = "button_id")
    @UpdateProvider(type = SysAuthButtonGenerateSql.class, method = "generateUpdateSql")
    int updateButton(SysAuthButton sysAuthButton);

    /**
     * 查询所有按钮
     *
     * @return
     */
    @Select("SELECT a.*, @rank:=@rank + 1 AS `index` FROM\n" +
            "(\n" +
            "SELECT button_id, button_name, button_code, button_class, tree_id, icon, description, sort, audit_state, is_enabled, is_deleted\n" +
            "FROM sys_auth_button WHERE is_deleted = 0\n" +
            ")a, (SELECT @rank:= 0) b")
    List<SysAuthButtonVO> listAllButton();

    /**
     * 获取所有未删除的按钮用于为菜单分配按钮
     *
     * @return
     */
    @Select("SELECT button_id, button_name, button_code, icon FROM sys_auth_button WHERE is_deleted = 0")
    List<SysAuthButtonVO> listAllButtonInfo();
}
