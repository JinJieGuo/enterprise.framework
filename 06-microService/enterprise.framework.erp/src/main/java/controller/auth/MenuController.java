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
 *		gl				 2020-01-05		        1.00					新建
 *******************************************************************************/

package controller.auth;

import controller.BaseController;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import enterprise.framework.pojo.auth.menu.ChoosedButtonDTO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.service.auth.menu.SysAuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/auth/menu/")
public class MenuController extends BaseController {

    @Autowired
    private SysAuthMenuService sysAuthMenuService;


    /**
     * 保存按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("saveMenu")
    public HttpResponse saveMenu(@RequestBody SysAuthMenuVO sysAuthMenuVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthMenuVO.setCreatorId(userInfo.getUserId());
        sysAuthMenuVO.setCreatorName(userInfo.getRealName());
        return sysAuthMenuService.saveMenu(sysAuthMenuVO);
    }

    @ResponseBody
    @PostMapping("saveMenuButton")
    public HttpResponse saveMenuButton(@RequestBody ChoosedButtonDTO choosedButtonDTO) {
        return sysAuthMenuService.saveMenuButton(choosedButtonDTO);
    }

    /**
     * 更新按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("updateMenu")
    public HttpResponse updateMenu(@RequestBody SysAuthMenuVO sysAuthMenuVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysAuthMenuVO.setModifierId(userInfo.getUserId());
        sysAuthMenuVO.setModifierName(userInfo.getRealName());
        sysAuthMenuVO.setModifyTime(new Date());
        return sysAuthMenuService.updateMenu(sysAuthMenuVO);
    }

    /**
     * 删除按钮
     *
     * @return
     */
    @ResponseBody
    @PostMapping("deleteMenu")
    public HttpResponse deleteMenu(@RequestBody SysAuthMenuVO sysAuthMenuVO) {
        return sysAuthMenuService.deleteMenu(sysAuthMenuVO);
    }

    @ResponseBody
    @GetMapping("getMenuById")
    public HttpResponse getMenuById(SysAuthMenuVO sysAuthMenuVO) {
        return sysAuthMenuService.getMenuById(sysAuthMenuVO);
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @ResponseBody
    @GetMapping("listAllMenu")
    public HttpResponse listAllMenu(SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthMenuService.listAllMenu();
    }

    /**
     * @param sysAuthButtonVO
     * @return
     */
    @ResponseBody
    @GetMapping("listMenuTree")
    public HttpResponse listMenuTree(SysAuthButtonVO sysAuthButtonVO) {
        return sysAuthMenuService.listMenuTree();
    }

    @ResponseBody
    @GetMapping("listMenuButton")
    public HttpResponse listMenuButton(int menuId) {
        return sysAuthMenuService.listMenuButton(menuId);
    }
}
