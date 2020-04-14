package com.czbank.controller;

import com.czbank.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foreverActiveBoy
 */
@RestController
@Slf4j
public class HelloController {
    /**
     * 引用远程服务对象
     */
    @Reference(application = "alibaba-server-provider",interfaceClass = HelloService.class,version = "1.0")
    private HelloService helloService;

    @RequestMapping(value = {"/say/{name}"},method = RequestMethod.GET)
    public String sayHello(@PathVariable(value = "name") String name){
        log.info("服务消费者入参，name:[{}]",name);
        return helloService.sayHello(name);
    }
}
