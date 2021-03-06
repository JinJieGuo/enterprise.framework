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

package controller.auth;

import controller.BaseController;
import enterprise.framework.business.engine.Components;
import enterprise.framework.business.engine.IScheduler;
import enterprise.framework.pojo.auth.user.ChoosedUserRoleDTO;
import enterprise.framework.pojo.auth.user.PasswordVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.service.auth.user.SysAuthUserService;
import org.springframework.web.bind.annotation.*;
import enterprise.framework.core.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/v1/auth/user/")
public class UserController extends BaseController {

    @Autowired(required = false)
    private SysAuthUserService sysAuthUserService;

    /**
     * 用户新增
     *
     * @param sysAuthUserVO
     * @return
     */
    @ResponseBody
    @PostMapping("saveUser")
    public HttpResponse saveUser(@RequestBody SysAuthUserVO sysAuthUserVO, HttpServletRequest request) {
        IScheduler businessScheduler = new Components();
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthUserVO.setCreatorId(userInfo.getUserId());
        sysAuthUserVO.setCreatorName(userInfo.getRealName());
        return businessScheduler.authManager().instance().register(sysAuthUserVO);
    }

    /**
     * 用户更新
     *
     * @param sysAuthUserVO
     * @return
     */
    @ResponseBody
    @PostMapping("updateUser")
    public HttpResponse updateUser(@RequestBody SysAuthUserVO sysAuthUserVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthUserVO.setModifierId(userInfo.getUserId());
        sysAuthUserVO.setModifierName(userInfo.getRealName());
        sysAuthUserVO.setModifyTime(new Date());
        return sysAuthUserService.updateUser(sysAuthUserVO);
    }

    /***
     * 修改密码
     * @param passwordVO
     * @return
     */
    @ResponseBody
    @PostMapping("updatePwd")
    public HttpResponse updatePwd(@RequestBody PasswordVO passwordVO) {
        return sysAuthUserService.updatePwd(passwordVO);
    }

    /**
     * 用户删除
     *
     * @param sysAuthUserVO
     * @return
     */
    @ResponseBody
    @PostMapping("deleteUser")
    public HttpResponse deleteUser(@RequestBody SysAuthUserVO sysAuthUserVO) {
        return sysAuthUserService.deleteUser(sysAuthUserVO);
    }

    /**
     * @param choosedUserRoleDTO
     * @return
     */
    @ResponseBody
    @PostMapping("saveUserRoleList")
    public HttpResponse saveUserRoleList(@RequestBody ChoosedUserRoleDTO choosedUserRoleDTO) {
        return sysAuthUserService.saveUserRoleList(choosedUserRoleDTO);
    }

    /**
     * 获取单个用户
     *
     * @param sysAuthUserVO
     * @return
     */
    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public HttpResponse getUserById(SysAuthUserVO sysAuthUserVO) {
        return sysAuthUserService.getUserById(sysAuthUserVO);
    }

    /**
     * 获取用户集合
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listAllUser", method = RequestMethod.GET)
    public HttpResponse listAllUser(HttpServletRequest request) {
        return sysAuthUserService.listAllUser();
    }

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listUserAuth", method = RequestMethod.GET)
    public HttpResponse listUserAuth(int userId) {
        return sysAuthUserService.listUserAuth(userId);
    }
}
