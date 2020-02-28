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
 *		gl				 2019-12-19		        1.00					新建
 *******************************************************************************/

package cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.WebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Configuration
public class CorsConfig {
    private static final String MAX_AGE = "18000L";
//    @Autowired
//    private HttpServletRequest httpServletRequest;

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
                HttpHeaders headers = response.getHeaders();

                String temp = requestHeaders.getOrigin();
//                String temp1 = httpServletRequest.getSession().getId();
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
                if (requestMethod != null) {
                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, OPTIONS");

                }
                String temp1 = requestHeaders.getFirst("Cookie");
                if (temp1 != null) {
                    headers.add(HttpHeaders.COOKIE, temp1);
                }
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }

            }
            return chain.filter(ctx);
        };
    }

//    /**
//     * 配置跨域
//     *
//     * @return
//     */
//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        // cookie跨域
//        config.setAllowCredentials(Boolean.TRUE);
//        config.addAllowedMethod("*");
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        // 配置前端js允许访问的自定义响应头
//        config.addExposedHeader("id");
//        config.addExposedHeader("token_info");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return new DefaultServerCodecConfigurer();
    }
}
