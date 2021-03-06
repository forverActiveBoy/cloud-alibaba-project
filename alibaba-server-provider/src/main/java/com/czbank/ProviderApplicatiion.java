package com.czbank;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author foreverActiveBoy
 * @EnableDiscoveryClient 开启向注册中心注册服务
 * 在当前版本下：
 * 问题一：Sentinel控制台中修改规则：仅存在于服务的内存中，不会修改Nacos中的配置值，重启后恢复原来的值。
 * 问题二：控制器占位符问题
 * 问题三：熔断函数和业务代码没分离
 * 问题四：路由配置，现在是配置在yml文件中的
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.czbank.service"})
@EnableTransactionManagement
@MapperScan(value = {"com.czbank.dao"})
public class ProviderApplicatiion {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicatiion.class,args);
    }
}
