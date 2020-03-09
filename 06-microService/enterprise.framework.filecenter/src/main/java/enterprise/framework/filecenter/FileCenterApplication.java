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
 *		gl				 2020-03-09		      1.00					新建
 *******************************************************************************/

package enterprise.framework.filecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;

@tk.mybatis.spring.annotation.MapperScan("enterprise.framework.mapper")
//@ComponentScan(basePackages = {"controller", "enterprise.framework.business", "enterprise.framework.service", "enterprise.framework.core", "common.datasource"})
@ComponentScan(basePackages = {"controller", "enterprise.framework.core", "common.datasource"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 禁用springBoot自动配置的数据源，这样才能自定义数据源
public class FileCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }
//    @Bean
//    public FilterRegistrationBean httpServletRequestReplacedRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new HttpServletRequestReplacedFilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("httpServletRequestReplacedFilter");
//        registration.setOrder(1);
//        return registration;
//    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("500MB");
        factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }
}
