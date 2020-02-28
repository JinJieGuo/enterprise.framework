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

package enterprise.framework;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.domain.auth.SysAuthMenu;
import enterprise.framework.erp.ErpApplication;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.service.auth.menu.SysAuthMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApplication.class)
public class InitDataTest {

    @Autowired
    private SysAuthMenuService sysAuthMenuService;

    @Test
    public void initData() {
//        SysAuthMenuVO sysAuthMenuVO = new SysAuthMenuVO();
//        sysAuthMenuVO.setMenuName("根菜单");
//        sysAuthMenuVO.setMenuCode("root");
//        sysAuthMenuVO.setIsMenu(0);
//        HttpResponse httpResponse = sysAuthMenuService.saveMenu(sysAuthMenuVO);
    }
}
