/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:enterprise.framework.gateway
 *		2.ClassName:AuthGatewayFilterFactory.cs
 *		3.FunctionDescription:企业级框架 — 网关
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
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.core.token.TokenInfo;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import pojo.ParametersModel;
import reactor.core.publisher.Mono;

/**
 * 企业级框架 — 网关
 *
 * @author gl
 * @date 2019年12月19日21:53:09
 */
@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

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

    //region "构造函数"

    public AuthGatewayFilterFactory() {
        super(Config.class);
    }

    //endregion

    //region "重写全局过滤器"

    /**
     * 重写网关过滤器
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {
                String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";
                Class inClass = String.class;
                ServerRequest serverRequest = new DefaultServerRequest(exchange);
                RequestBodyHandler requestBodyHandler = new RequestBodyHandler();


                Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
                if (requestBody == null) {
                    return chain.filter(exchange);
                }

                String uri = exchange.getRequest().getURI().toString().toLowerCase();
                if (uri.contains("register") || uri.contains("login")) {
//                    ParametersModel parametersModel = JSON.parseObject((String) requestBody, ParametersModel.class);
//                    String bodyStr = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(parametersModel.getParameters()), tokenInfo.getPrivate_key()));
//                    //获取requestBody
//                    Mono<?> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(o -> {
//                        exchange.getAttributes().put(CACHE_REQUEST_BODY_OBJECT_KEY, bodyStr);
//                        return Mono.justOrEmpty(bodyStr);
//                    });
//                    return requestBodyHandler.overWriteRequestBody(exchange, chain, modifiedBody, inClass);
                    return chain.filter(exchange);
                }

                String user_id = exchange.getRequest().getHeaders().getFirst("id");
                String token = exchange.getRequest().getHeaders().getFirst("token_info");
                ServerHttpResponse response = exchange.getResponse();
                if (token.equals("-. --- - .. --. -. --- .-. .")) {
                    return chain.filter(exchange);
                }

                if (user_id.equals("")) {
                    //用户id为空,返回203
                    response.setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
                    return response.setComplete();
                }

                if (token.equals("")) {
                    //token为空,返回401
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                RedisHandler redisHandler = new RedisHandler(redisTemplate);
                StrHandler strHandler = new StrHandler();
                //token不为空时,先获取redis中的token字节,转为json字符串,并反序列化
                TokenInfo tokenInfo = JSON.parseObject(strHandler.binaryToStr((String) redisHandler.get("token_info:" + user_id)), TokenInfo.class);

                if (!token.equals(tokenInfo.getToken_str())) {
                    //token不与缓存中的token相同,返回405
                    response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
                    return response.setComplete();
                }

                String[] tokenArray = token.split("\\.");
                String jwtHeader = tokenArray[0];
                String jwtPayload = tokenArray[1];
                String signStr = tokenArray[2];

                boolean verifyResult = RSAUtils.verify((jwtHeader + "." + jwtPayload).getBytes(), tokenInfo.getPublic_key(), new String(Base64Utils.decode(signStr)));

                if (!verifyResult) {
                    //token验证失败返回503,服务不可用
                    response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
                    return response.setComplete();
                } else {
                    ParametersModel parametersModel = JSON.parseObject((String) requestBody, ParametersModel.class);
                    String bodyStr = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(parametersModel.getParameters()), tokenInfo.getPrivate_key()));
                    //获取requestBody
                    Mono<?> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(o -> {
                        exchange.getAttributes().put(CACHE_REQUEST_BODY_OBJECT_KEY, bodyStr);
                        return Mono.justOrEmpty(bodyStr);
                    });
                    return requestBodyHandler.overWriteRequestBody(exchange, chain, modifiedBody, inClass);
                }

//                return chain.filter(exchange);
            } catch (Exception error) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                error.printStackTrace();
                return response.setComplete();
            }
        };
    }

    //endregion


    static class Config {
        static String secret = "";
    }
}
