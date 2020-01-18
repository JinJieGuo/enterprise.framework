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
 *		gl				 2019-12-20		        1.00					新建
 *******************************************************************************/

package auth;

import com.alibaba.fastjson.JSON;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestBodyHandler {
    @Autowired(required = false)
    private static RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    public Mono<Void> overWriteRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, Mono<?> modifiedBody, Class inClass) {
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, inClass);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());

        //重写body时,必须重写header
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {

                        @Override
                        public HttpHeaders getHeaders() {
                            long contentLength = headers.getContentLength();
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                // TODO: this causes a 'HTTP/1.1 411 Length Required' on httpbin.org
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            return outputMessage.getBody();
                        }
                    };
                    return exchange.getSession().flatMap(webSession -> {
                        String userId = exchange.getRequest().getHeaders().getFirst("id");
                        String sessionId = webSession.getId();
                        String existSessionId = webSession.getAttribute(userId);
                        RedisHandler redisHandler = new RedisHandler(redisTemplate);
                        if (userId != null) {
                            webSession.getAttributes().put(userId, sessionId);
                            HttpResponse userRedis = redisHandler.get("user_info:" + userId);
                            if (userRedis.status == enterprise.framework.core.http.HttpStatus.SUCCESS.value() && userRedis.content != null) {
                                StrHandler strHandler = new StrHandler();
                                strHandler.toBinary(JSON.toJSONString(userRedis.content));
                                webSession.getAttributes().put("currentUser", strHandler.binaryToStr(userRedis.content.toString()));
                            }
                            if (existSessionId != null && !existSessionId.equals(sessionId)) {
                                ServerHttpResponse response = exchange.getResponse();
                                //用户异地登录,511错误码
                                response.setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
                                return response.setComplete();
                            }
                        }
                        return chain.filter(exchange.mutate().request(decorator).build());
                    }).then(Mono.fromRunnable(() -> {

                    }));
                }));
    }
}
