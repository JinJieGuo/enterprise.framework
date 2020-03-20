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

package enterprise.framework.utility.tree;

import enterprise.framework.utility.database.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Recursive <T>{


    public void recursiveTreeTable(TreeTableVO rootNode, List<T> dataSource) {
        List<TreeTableVO> childrenList = new ArrayList<>();
        Field[] allFields = dataSource.getClass().getDeclaredFields();
        for (int index = 0; index < allFields.length; index++) {
            String propertyName = allFields[index].getName();
        }
//        for (SysAuthMenuVO sysAuthMenuVO : dataSource) {
//            if (sysAuthMenuVO.getParentId() != null && sysAuthMenuVO.getParentId() != 0 && sysAuthMenuVO.getParentId() == rootNode.getId()) {
//                TreeTableVO treeTableVO = new TreeTableVO();
//                treeTableVO.setId(sysAuthMenuVO.getMenuId());
//                treeTableVO.setTreeName(sysAuthMenuVO.getMenuName());
//                treeTableVO.setTreeCode(sysAuthMenuVO.getMenuCode());
//                treeTableVO.setLevel(rootNode.getLevel() + 1);
//                treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
////                treeTableVO.setIs_parent(sysAuthMenuVO.getIs_parent());
//                treeTableVO.setParentId(sysAuthMenuVO.getParentId());
//                treeTableVO.setParentName(sysAuthMenuVO.getParentName());
//                treeTableVO.setIcon(sysAuthMenuVO.getIcon());
//                treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
//                treeTableVO.setExpand(true);
//                childrenList.add(treeTableVO);
//            }
//        }
//        rootNode.setChildren(childrenList);
//        if (rootNode.getChildren().size() > 0) {
//            for (TreeTableVO childrenCategory : rootNode.getChildren()) {
//                recursiveTreeTable(childrenCategory, dataSource);
//            }
//        }
    }
}
