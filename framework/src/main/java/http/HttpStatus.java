/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:HttpStatus.cs
 *		3.FunctionDescription:核心组件 — 请求状态枚举
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
 *		gl				 2019-11-24		        1.00					新建
 *******************************************************************************/

package enterprise.framework.core.http;

/**
 * 核心组件 — 请求状态枚举
 */
public enum HttpStatus {

    /**
     * 成功
     */
    SUCCESS,

    /**
     * 错误日志
     */
    ERROR,

    /**
     * log输出信息
     */
    INFO,

    /**
     * 调试日志
     */
    DEBUG,

    /**
     * 调试日志
     */
    TRACE,

    /**
     * 异常日志
     */
    FATAL,

    /**
     * 警告日志
     */
    WARN,

    /**
     * 格式错误
     */
    FORMAT_ERROR,

    /**
     * 许可证已过期
     */
    OVERDUE,

    /**
     * 用户已存在
     */
    USER_EXIST,

    /**
     * 密码错误
     */
    PASSWORD_ERROR
}
