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
import enterprise.framework.pojo.auth.user.ChoosedUserRoleDTO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import org.springframework.stereotype.Component;

@Component
public interface SysAuthUserService {

    /**
     * 保存用户信息
     *
     * @param sysAuthUserVO 用户实体
     * @return
     */
    HttpResponse saveUser(SysAuthUserVO sysAuthUserVO);

    /**
     * 更新用户
     *
     * @param sysAuthUser
     * @return
     */
    HttpResponse updateUser(SysAuthUser sysAuthUser);

    /**
     * 更新用户
     *
     * @param sysAuthUserVO
     * @return
     */
    HttpResponse updateUser(SysAuthUserVO sysAuthUserVO);

    /**
     * 删除用户
     *
     * @param sysAuthUserVO
     * @return
     */
    HttpResponse deleteUser(SysAuthUserVO sysAuthUserVO);

    /**
     * 保存用户角色
     *
     * @param choosedUserRoleDTO
     * @return
     */
    HttpResponse saveUserRoleList(ChoosedUserRoleDTO choosedUserRoleDTO);

    /**
     * 根据用户主键获取用户信息
     *
     * @param sysAuthUserVO
     * @return
     */
    HttpResponse getUserById(SysAuthUserVO sysAuthUserVO);

    /**
     * 获取所有用户
     *
     * @return
     */
    HttpResponse listAllUser();

    /**
     * 根据条件获取用户集合
     *
     * @param loginName
     * @return
     */
    HttpResponse listUserByParameters(String loginName);

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    HttpResponse listUserAuth(int userId);
}
