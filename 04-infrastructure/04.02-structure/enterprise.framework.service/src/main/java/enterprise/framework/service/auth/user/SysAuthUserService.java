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
 *		gl				 2019-12-21		        1.00					新建
 *******************************************************************************/

package enterprise.framework.service.auth.user;

import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.core.http.HttpResponse;
import org.springframework.stereotype.Component;

@Component
public interface SysAuthUserService {

    /**
     * 保存用户信息
     *
     * @param sysAuthUser 用户实体
     * @return
     */
    HttpResponse saveUser(SysAuthUser sysAuthUser);

    /**
     * 更新用户
     *
     * @param sysAuthUser
     * @return
     */
    HttpResponse updateUser(SysAuthUser sysAuthUser);

    /**
     * 获取所有用户
     *
     * @return
     */
    HttpResponse listAllUser();

    /**
     * 根据条件获取用户集合
     *
     * @param sysAuthUser
     * @return
     */
    HttpResponse listUserByParameters(SysAuthUser sysAuthUser);
}
