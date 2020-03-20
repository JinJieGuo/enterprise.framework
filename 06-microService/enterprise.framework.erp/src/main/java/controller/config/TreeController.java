/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:enterprise.framework.erp
 *		2.ClassName:TreeController.java
 *		3.FunctionDescription:基础服务 — 字典表树
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
 *		gl				 2020-03-20		        1.00					新建
 *******************************************************************************/

package controller.config;

import controller.BaseController;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.pojo.config.tree.SysConfigTreeVO;
import enterprise.framework.service.config.tree.SysConfigTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/v1/config/tree/")
public class TreeController extends BaseController {


    @Autowired
    private SysConfigTreeService sysConfigTreeService;


    /**
     * 保存字典树
     *
     * @return
     */
    @ResponseBody
    @PostMapping("saveTree")
    public HttpResponse saveTree(@RequestBody SysConfigTreeVO sysConfigTreeVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysConfigTreeVO.setCreatorId(userInfo.getUserId());
        sysConfigTreeVO.setCreatorName(userInfo.getRealName());
        return sysConfigTreeService.saveTree(sysConfigTreeVO);
    }

    /**
     * 更新字典树
     *
     * @param sysConfigTreeVO
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("updateTree")
    public HttpResponse updateTree(@RequestBody SysConfigTreeVO sysConfigTreeVO, HttpServletRequest request) {
        SysAuthUserVO userInfo = currentUserInfo(request);
        sysConfigTreeVO.setModifierId(userInfo.getUserId());
        sysConfigTreeVO.setModifierName(userInfo.getRealName());
        sysConfigTreeVO.setModifyTime(new Date());
        return sysConfigTreeService.updateTree(sysConfigTreeVO);
    }

    /**
     * 删除字典树
     *
     * @return
     */
    @ResponseBody
    @PostMapping("deleteTree")
    public HttpResponse deleteTree(@RequestBody SysConfigTreeVO sysConfigTreeVO) {
        return sysConfigTreeService.deleteTree(sysConfigTreeVO);
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @ResponseBody
    @GetMapping("listAllTree")
    public HttpResponse listAllTree(SysConfigTreeVO sysConfigTreeVO) {
        return sysConfigTreeService.listAllTree();
    }
}
