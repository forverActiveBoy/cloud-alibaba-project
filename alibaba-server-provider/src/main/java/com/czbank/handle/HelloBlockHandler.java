package com.czbank.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author foreverActiveBoy
 * 限流处理类
 */
@Slf4j
public class HelloBlockHandler {
    /**
     * 处理sayHello方法的限流
     * @return
     */
    public static String sayHelloBlockHandler(String name, BlockException e){
        log.warn("请求sayHello方法被限流，name:[{}] and e:[{}]",name,e.getMessage());
        return name;
    }
}
