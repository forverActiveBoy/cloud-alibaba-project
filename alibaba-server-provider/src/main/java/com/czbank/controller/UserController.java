package com.czbank.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foreverActiveBoy
 * 服务实现者
 * @RefreshScope 动态从nacos取值
 */

@RestController
@Slf4j
@RefreshScope
public class UserController {
    /**
     * 从配置中心根绝key取值
     */
    @Value(value = "${uploadPath}")
    private String uploadPath;

    @RequestMapping(value = {"/v1/user/{name}"},method = RequestMethod.GET)

    @SentinelResource
    public String sayHello(@PathVariable(value = "name") String name){
        log.info("服务提供者入参,name:[{}] and uploadPath:[{}]",name,uploadPath);
        return "hello+"+name;
    }
}
