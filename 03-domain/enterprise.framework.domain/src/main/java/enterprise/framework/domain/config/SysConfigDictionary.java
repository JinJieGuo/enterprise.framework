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

import enterprise.framework.pojo.config.dictionary.SysConfigDictionaryVO;
import enterprise.framework.utility.database.DbBaseDO;
import enterprise.framework.utility.database.PrimaryKey;
import enterprise.framework.utility.database.Table;

@Table("sys_config_dictionary")
public class SysConfigDictionary extends DbBaseDO {

    public SysConfigDictionary() {

    }

    public SysConfigDictionary(SysConfigDictionaryVO sysConfigDictionaryVO) {

    }

    @PrimaryKey("dictionary_id")
    private Long dictionary_id;

    private Long tree_id;

    private String dictionary_code;

    private String dictionary_name;

    private String key_name;

    private String key_value;

    private String icon;

    private String description;

    private Integer sort;

    private Integer is_enabled;

    private Integer is_deleted;

    public void setDictionaryId(Long dictionaryId) {
        this.dictionary_id = dictionaryId;
    }

    public Long getDictionaryId() {
        return dictionary_id;
    }

    public void setTreeId(Long treeId) {
        this.tree_id = treeId;
    }

    public Long getTreeId() {
        return tree_id;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionary_code = dictionaryCode;
    }

    public String getDictionaryCode() {
        return dictionary_code;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionary_name = dictionaryName;
    }

    public String getDictionaryName() {
        return dictionary_name;
    }

    public void setKeyName(String keyName) {
        this.key_name = keyName;
    }

    public String getKeyName() {
        return key_name;
    }

    public void setKeyValue(String keyValue) {
        this.key_value = keyValue;
    }

    public String getKeyValue() {
        return key_value;
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

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.is_enabled = isEnabled;
    }

    public Integer getIsEnabled() {
        return is_enabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.is_deleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return is_deleted;
    }
}
