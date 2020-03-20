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
 *		gl				 2020-03-20		        1.00					新建
 *******************************************************************************/

package enterprise.framework.mapper.config.tree;

import enterprise.framework.domain.config.SysConfigTree;
import enterprise.framework.pojo.config.tree.SysConfigTreeVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysConfigTreeMapper extends Mapper<SysConfigTree> {

    /**
     * 新增字典树
     * @param sysConfigTree
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "tree_id", keyColumn = "tree_id")
    @InsertProvider(type = SysConfigTreeGenerateSql.class, method = "generateSaveSql")
    int saveTree(SysConfigTree sysConfigTree);

    /**
     * 编辑字典树
     * @param sysConfigTree
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "tree_id", keyColumn = "tree_id")
    @UpdateProvider(type = SysConfigTreeGenerateSql.class, method = "generateUpdateSql")
    int updateTree(SysConfigTree sysConfigTree);

    /**
     * 获取字典树
     * @return
     */
    @Select("SELECT  a.*,@rank:=@rank + 1 AS `index`\n" +
            "FROM\n" +
            "(\n" +
            "\tSELECT tree_id, parent_id, parent_name, tree_name, tree_code, icon, description, sort\n" +
            "\tFROM sys_config_tree \n" +
            "\tWHERE is_deleted = 0\n" +
            ")a, (SELECT @rank:= 0) b ORDER BY a.sort ASC")
    List<SysConfigTreeVO> listAllTree();
}
