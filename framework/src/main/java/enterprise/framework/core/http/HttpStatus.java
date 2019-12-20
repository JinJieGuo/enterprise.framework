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
    SUCCESS(10000, "Success"),

    /**
     * 错误日志
     */
    ERROR(10001, "Error"),

    /**
     * log输出信息
     */
    INFO(10002, "Info"),

    /**
     * 调试日志
     */
    DEBUG(10003, "Debug"),

    /**
     * 调试日志
     */
    TRACE(10004, "Trace"),

    /**
     * 异常日志
     */
    FATAL(10005, "Fatal"),

    /**
     * 警告日志
     */
    WARN(10006, "Warn"),

    /**
     * 格式错误
     */
    FORMAT_ERROR(20000, "Format Error"),

    /**
     * 许可证已过期
     */
    OVERDUE(20001, "Overdue"),

    /**
     * 用户不存在
     */
    USER_NOTEXIST(20002, "User NotExist"),

    /**
     * 用户已存在
     */
    USER_EXIST(20003, "User Exist"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(20004, "Password Error");

    private final int value;
    private final String reasonPhrase;

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }


}
