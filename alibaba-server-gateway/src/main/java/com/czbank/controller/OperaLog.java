package com.czbank.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * 全局日志记录器
 * @author foreverActiveBoy
 */
@Slf4j
@Component
public class OperaLog implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        URI uri =  serverHttpRequest.getURI();
        log.info("访问的uri:[{}]",uri);
        return chain.filter(exchange);
    }
}
