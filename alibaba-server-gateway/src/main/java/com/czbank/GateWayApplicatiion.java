package com.czbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author foreverActiveBoy
 * @EnableDiscoveryClient 开启向注册中心注册服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplicatiion {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplicatiion.class,args);
    }
}
