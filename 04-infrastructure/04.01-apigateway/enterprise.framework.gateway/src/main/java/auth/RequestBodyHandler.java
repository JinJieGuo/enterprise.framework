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

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RequestBodyHandler {
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
                        if (userId != null && existSessionId != null && existSessionId != sessionId) {
                            ServerHttpResponse response = exchange.getResponse();
                            //用户异地登录,511错误码
                            response.setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
                            return response.setComplete();
                        }
                        webSession.getAttributes().put(userId, sessionId);
                        return chain.filter(exchange.mutate().request(decorator).build());
                    }).then(Mono.fromRunnable(() -> {

                    }));
                }));
    }
}
