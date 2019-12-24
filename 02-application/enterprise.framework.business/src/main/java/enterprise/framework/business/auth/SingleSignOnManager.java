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
 *		gl				 2019-12-23		        1.00					新建
 *******************************************************************************/

package enterprise.framework.business.auth;

import enterprise.framework.core.log.LogManager;

public class SingleSignOnManager implements ISingleSignOnManager {

    private static SingleSignOnHandler singleSignOnHandler;

    public SingleSignOnHandler instance() {
        if (singleSignOnHandler == null) {
            //加上同步锁，保证线程安全
            synchronized (LogManager.class) {
                singleSignOnHandler = new SingleSignOnHandler();
            }
        }
        return singleSignOnHandler;
    }


}
