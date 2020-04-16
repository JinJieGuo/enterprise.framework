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
 *		gl				 2019-12-26		      1.00					新建
 *******************************************************************************/

package enterprise.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import enterprise.framework.business.engine.Components;
import enterprise.framework.business.engine.IScheduler;
import enterprise.framework.core.log.ILogManager;
import enterprise.framework.core.log.LogManager;
import enterprise.framework.erp.ErpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApplication.class)
public class SystemTest {

    @Test
    public void ConfigTest() throws FileNotFoundException {
        File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("file path:" + destFile.getAbsolutePath());
        if (!destFile.exists()) {
            destFile = new File("");
        }
        System.out.println("file path:" + destFile.getAbsolutePath());
        System.out.println(UUID.randomUUID().toString().toUpperCase());
//        File file = new File(ResourceUtils.getURL("classpath:").getPath());
//
////        String path =  ClassUtils.getDefaultClassLoader().getResource("").getPath();
////        String path = ResourceUtils.getURL("classpath:").getPath();
//        String path1 = System.getProperty("user.dir");
//        String path = file.getAbsolutePath();
//        Object property = YmlPropUtils.getInstance().getProperty("spring.datasource.type");
//        System.out.println(property);
    }

    @Test
    public void LogTest(){

        ILogManager logManager = new LogManager();
        logManager.instance().error("测试输出日志");
    }
}
