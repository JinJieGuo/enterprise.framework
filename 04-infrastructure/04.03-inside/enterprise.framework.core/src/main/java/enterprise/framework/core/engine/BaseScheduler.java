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
 *		gl				 2019-11-26		        1.00					新建
 *******************************************************************************/

package enterprise.framework.core.engine;

import enterprise.framework.core.log.ILogManager;
import enterprise.framework.core.log.LogManager;

public class BaseScheduler implements IScheduler {

    // region
    private static ILogManager logManager;

    @Override
    public ILogManager logManager() {
        return logManager;
    }

    public static void setLogManager(ILogManager logManager) {
        BaseScheduler.logManager = logManager;
    }

    private boolean init;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
    // endregion

    public BaseScheduler() {
        try {
            if (!init) initComponents();
        } catch (Exception error) {

        }
    }

    private void initComponents() {
        if (!init && logManager == null) {
            logManager = new LogManager();
        }
        init = true;
    }
}
