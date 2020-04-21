package com.czbank.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.czbank.handle.HelloBlockHandler;
import com.czbank.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author foreverActiveBoy
 */
@Slf4j
@Service(interfaceClass = HelloService.class,version = "1.0")
public class HelloServiceImpl implements HelloService {

    /**
     * blockHandler  限流函数
     * blockHandlerClass 限流类
     * fallback 熔断函数
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello",blockHandler = "sayHelloBlockHandler",blockHandlerClass = {HelloBlockHandler.class},fallback = "sayHelloFallbackHandler")
    @Override
    public String sayHello(String name) {
        return name+"+hello!";
    }

    /**
     * 熔断函数
     * @param name
     * @param
     * @return
     */
    public static String sayHelloFallbackHandler(String name){
        log.error("请求sayHello方法被熔断，name:[{}]",name);
        return name;
    }
}
