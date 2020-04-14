package com.czbank.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启sentinel注解配置类
 * @author foreverActiveBoy
 */
@Configuration
public class SentinelConfig {
    /**
     * 注解支持的配置Bean
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
