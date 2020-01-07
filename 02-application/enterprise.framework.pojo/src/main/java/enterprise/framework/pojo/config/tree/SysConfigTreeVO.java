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

package enterprise.framework.pojo.config.tree;

import enterprise.framework.utility.database.DbBaseVO;

public class SysConfigTreeVO extends DbBaseVO {
    private Long treeId;

    private String treeCode;

    private String treeName;

    private Long parentId;

    private String parentName;

    private String icon;

    private String description;

    private String sort;

    private Integer isDeleted;

    private Integer isEnabled;

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeName() {
        return treeName;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setIs_enabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getIs_enabled() {
        return isEnabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }
}
