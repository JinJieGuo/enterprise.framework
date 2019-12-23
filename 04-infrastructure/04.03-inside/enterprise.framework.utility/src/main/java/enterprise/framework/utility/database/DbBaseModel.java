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
 *		gl				 2019-11-28		      1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.database;

import java.util.Date;

public class DbBaseModel {

    private int sort;

    private boolean is_delete;

    private boolean is_enable;

    private int creator_id;

    private String creator_name;

    private Date creator_time;

    private int modifier_id;

    private String modifier_name;

    private Date modifier_time;


}
