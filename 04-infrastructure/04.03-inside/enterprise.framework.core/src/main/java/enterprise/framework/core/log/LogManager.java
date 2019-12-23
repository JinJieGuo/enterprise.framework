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
 *      11.Others: logback 配置:https://www.jianshu.com/p/5cc75104c90f
 * EditResume:
 *	   Author				Date			  version			   ChangeContent 
 *		gl				 2019-11-25		      1.00					新建
 *******************************************************************************/

package enterprise.framework.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager implements ILogManager {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(LogManager.class);
    private static Logger logger;

    @Override
    public Logger instance() {
        if (logger == null) {
            //加上同步锁，保证线程安全
            synchronized (LogManager.class) {
                logger = LoggerFactory.getLogger(LogManager.class);
            }
        }
        return logger;
    }
}
