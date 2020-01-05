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

package enterprise.framework.pojo.config.tree;

import java.util.List;

public class TreeTableVO {
    private Long id;

    private String treeName;

    private String treeCode;

    private Long parentId;

    private String parentName;

    private String icon;

    private int level;

    private boolean expand;

    private boolean is_parent;

    private List<TreeTableVO> children;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setIs_parent(boolean is_parent) {
        this.is_parent = is_parent;
    }

    public boolean getIs_parent() {
        return is_parent;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setChildren(List<TreeTableVO> children) {
        this.children = children;
    }

    public List<TreeTableVO> getChildren() {
        return children;
    }
}
