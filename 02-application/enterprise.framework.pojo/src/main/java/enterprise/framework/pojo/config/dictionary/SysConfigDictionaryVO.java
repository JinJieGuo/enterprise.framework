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

package enterprise.framework.pojo.config.dictionary;

public class SysConfigDictionaryVO {
    private Long dictionaryId;

    private Long treeId;

    private String dictionaryCode;

    private String dictionaryName;

    private String keyName;

    private String keyValue;

    private String icon;

    private String description;

    private Integer sort;

    private Integer isEnabled;

    private Integer isDeleted;

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyValue() {
        return keyValue;
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
        this.isEnabled = isEnabled;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }
}
