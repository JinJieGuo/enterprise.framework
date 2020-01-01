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

package enterprise.framework.business.engine;

import enterprise.framework.business.auth.AuthManager;
import enterprise.framework.business.auth.IAuthManager;

public class BaseScheduler implements IScheduler {


    private IAuthManager iAuthManager;

    @Override
    public IAuthManager authManager() {
        return iAuthManager;
    }


    public void setiAuthManager(IAuthManager iAuthManager) {
        this.iAuthManager = iAuthManager;
    }

    private boolean init;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public BaseScheduler() {
        try {
            if (!init) initComponents();
        } catch (Exception error) {

        }
    }

    private void initComponents() {
        if (!init && iAuthManager == null) {
            iAuthManager = new AuthManager();
        }
        init = true;
    }



}
