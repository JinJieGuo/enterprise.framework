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
 *		gl				 2019-12-27		      1.00					新建
 *******************************************************************************/

package enterprise.framework.core.rabbitmq;

import java.util.List;
import java.util.Map;

/**
 * 队列配置信息
 */
public class RabbitMqInfo {

    private String mqName;

    private String userName;

    private String password;

    private String hostName;

    private Integer port;

    private String virtualHost;

    private Map<String, Object> channelList;

    public void setMqName(String mqName) {
        this.mqName = mqName;
    }

    public String getMqName() {
        return mqName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setChannelList(Map<String, Object> channelList) {
        this.channelList = channelList;
    }

    public Map<String, Object> getChannelList() {
        return channelList;
    }
}
