/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:enterprise.framework.gateway
 *		2.ClassName:AuthGlobalFilter.java
 *		3.FunctionDescription:企业级网关 — 全局权限过滤器
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

package auth;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.core.token.TokenInfo;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import pojo.ParametersModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业级网关 — 全局权限过滤器
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 缓存post requestBody或get 请求中的queryString
     */
    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            Class inClass = String.class;
            ServerRequest serverRequest = new DefaultServerRequest(exchange);
            ServerHttpRequest request = exchange.getRequest();

            //不合法的请求
            String schema = request.getURI().getScheme();
            if ((!"http".equals(schema) && !"https".equals(schema))) {
                return chain.filter(exchange);
            }

            switch (request.getMethod().name()) {
                case "GET":
                    if (!request.getQueryParams().isEmpty()) {
                        Map<String, String> map = new HashMap<>();
                        request.getQueryParams().toSingleValueMap().forEach((key, val) -> map.put(key, getURLDecoder(val)));
                        String param = new Gson().toJson(map);
                        exchange.getAttributes().put(CACHE_REQUEST_BODY_OBJECT_KEY, param);
                    }
//                    return chain.filter(exchange);
                case "POST":
                    String contentType = request.getHeaders().getFirst("Content-Type");
                    String upload = request.getHeaders().getFirst("upload");
                    //没有内容类型不读取body
                    if (contentType == null || contentType.length() == 0) {
                        return chain.filter(exchange);
                    }
                    //文件上传不读取body
                    if ("true".equals(upload)) {
                        return chain.filter(exchange);
                    }

                    //获取requestBody
                    Mono<?> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(o -> {
                        exchange.getAttributes().put(CACHE_REQUEST_BODY_OBJECT_KEY, o);
                        return Mono.justOrEmpty(o);
                    });

                    RequestBodyHandler requestBodyHandler = new RequestBodyHandler();
                    return requestBodyHandler.overWriteRequestBody(exchange, chain, modifiedBody, inClass);
            }
            return exchange.getSession().flatMap(webSession -> {
                String userId = exchange.getRequest().getHeaders().getFirst("id");
                String sessionId = webSession.getId();
                String existSessionId = webSession.getAttribute(userId);
                if (userId != null && existSessionId != null && existSessionId != sessionId) {
                    ServerHttpResponse response = exchange.getResponse();
                    //用户异地登录,511错误码
                    response.setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
                    return response.setComplete();
                }
                return chain.filter(exchange);
            }).then(Mono.fromRunnable(() -> {

            }));
        } catch (Exception error) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            error.printStackTrace();
            return response.setComplete();
        }
    }


    /**
     * 重写执行顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -4;
    }


    /**
     * URL 转码
     *
     * @param val
     * @return
     */
    private String getURLDecoder(String val) {
        try {
            return URLDecoder.decode(val, "utf-8");
        } catch (Exception e) {
//            log.error("getURLDecoder error",e);
        }
        return val;
    }

}
