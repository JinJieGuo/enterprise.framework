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

package enterprise.framework.domain.config;

import enterprise.framework.pojo.config.tree.SysConfigTreeVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;

@Table("sys_config_tree")
public class SysConfigTree extends DbBaseDO {

    public SysConfigTree() {

    }

    public SysConfigTree(SysConfigTreeVO sysConfigTreeVO) {
        this.tree_id = sysConfigTreeVO.getTreeId();
        this.tree_code = sysConfigTreeVO.getTreeCode();
        this.tree_name = sysConfigTreeVO.getTreeName();
        this.parent_id = sysConfigTreeVO.getParentId();
        this.parent_name = sysConfigTreeVO.getParentName();
        this.icon = sysConfigTreeVO.getIcon();
        this.description = sysConfigTreeVO.getDescription();
        this.sort = sysConfigTreeVO.getSort();
        this.is_deleted = sysConfigTreeVO.getIsDeleted();
        this.setCreatorId(sysConfigTreeVO.getCreatorId());
        this.setCreatorName(sysConfigTreeVO.getCreatorName());
        this.setModifierId(sysConfigTreeVO.getModifierId());
        this.setModifierName(sysConfigTreeVO.getModifierName());
        this.setModifyTime(sysConfigTreeVO.getModifyTime());
    }


    @PrimaryKey("tree_id")
    private Long tree_id;

    private String tree_code;

    private String tree_name;

    private Long parent_id;

    private String parent_name;

    private String icon;

    private String description;

    private String sort;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setTreeId(Long treeId) {
        this.tree_id = treeId;
    }

    public Long getTreeId() {
        return tree_id;
    }

    public void setTreeCode(String treeCode) {
        this.tree_code = treeCode;
    }

    public String getTreeCode() {
        return tree_code;
    }

    public void setTreeName(String treeName) {
        this.tree_name = treeName;
    }

    public String getTreeName() {
        return tree_name;
    }

    public void setParentId(Long parentId) {
        this.parent_id = parentId;
    }

    public Long getParentId() {
        return parent_id;
    }

    public void setParentName(String parentName) {
        this.parent_name = parentName;
    }

    public String getParentName() {
        return parent_name;
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

    public void SetIsEnabled(Integer isEnabled) {
        this.is_enabled = isEnabled;
    }

    public Integer GetIsEnabled() {
        return is_enabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.is_deleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return is_deleted;
    }
}
