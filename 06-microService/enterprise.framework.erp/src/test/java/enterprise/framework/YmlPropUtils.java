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

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.ResourceUtils;
//import com.ynzhongxi.pay.pojo.system.BootYaml;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

public class YmlPropUtils {
    private LinkedHashMap prop;
    private static YmlPropUtils ymlPropUtils;

    static {
        try {
            ymlPropUtils = new YmlPropUtils();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私有构造，禁止直接创建
     */
    private YmlPropUtils() throws FileNotFoundException {
        BootYaml yaml = new BootYaml();
        yaml.setActive("spring.profiles.active");
        yaml.setInclude("spring.profiles.include");
        yaml.setPrefix("application");
//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config\\";
//        String path = "\\src\\main\\resources\\config\\";
        String path = "\\target\\test-classes\\";
        prop = yaml.loadAs(path + "application.yml");
    }

    /**
     * 获取单例
     *
     * @return YmlPropUtils
     */
    public static YmlPropUtils getInstance() throws FileNotFoundException {
        if (ymlPropUtils == null) {
            ymlPropUtils = new YmlPropUtils();
        }
        return ymlPropUtils;
    }

    /**
     * 根据属性名读取值
     * 先去主配置查询，如果查询不到，就去启用配置查询
     *
     * @param name 名称
     */
    public Object getProperty(String name) {
        LinkedHashMap param = prop;
        List<String> split = StrSpliter.split(name, StrUtil.C_DOT, true, true);
        for (int i = 0; i < split.size(); i++) {
            if (i == split.size() - 1) {
                return param.get(split.get(i));
            }
            param = Convert.convert(LinkedHashMap.class, param.get(split.get(i)));
        }
        return null;
    }
}
