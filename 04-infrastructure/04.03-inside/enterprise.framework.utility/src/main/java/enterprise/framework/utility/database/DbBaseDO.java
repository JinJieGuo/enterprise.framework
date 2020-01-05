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
 *		gl				 2019-12-20		        1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DbBaseDO {

    private Long creator_id;

    private String creator_name;

    private Date create_time;

    private Long modifier_id;

    private String modifier_name;

    private Date modify_time;

    public void setCreatorId(Long creatorId) {
        this.creator_id = creatorId;
    }

    public Long getCreatorId() {
        return creator_id;
    }

    public void setCreatorName(String creatorName) {
        this.creator_name = creatorName;
    }

    public String getCreatorName() {
        return creator_name;
    }

    public void setCreateTime(Date createTime) {
        this.create_time = createTime;
    }

    public Date getCreateTime() {
        return create_time;
    }

    public void setModifierId(Long modifierId) {
        this.modifier_id = modifierId;
    }

    public Long getModifierId() {
        return modifier_id;
    }

    public void setModifierName(String modifierName) {
        this.modifier_name = modifierName;
    }

    public String getModifierName() {
        return modifier_name;
    }

    public void setModifyTime(Date modifyTime) {
        this.modify_time = modifyTime;
    }

    public Date getModifyTime() {
        return modify_time;
    }
}
