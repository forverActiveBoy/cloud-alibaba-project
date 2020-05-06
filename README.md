# springcloud alibaba各组件的示例  
##  环境：nacos,dubbo,sentinel,gateway  
##  工程介绍：   
```
cloud-alibaba-project--------父工程，管理依赖     
alibaba-server-api---------------本地接口定义   
alibaba-server-base--------------entity,dao接口，mapper存放的模块   
alibaba-server-common------------公共组件，工具类存放的模块      
alibaba-server-openapi----------与第三方系统交互接口的定义       
alibaba-server-openapi-impl-----与第三方系统交互接口的实现    
alibaba-server-provider----------本地接口实现，服务提供者，发布服务      
alibaba-server-consumer----------服务消费者，订阅服务     
alibaba-server-gateway-----------网关层，前端请求的统一转发，限流，权限设置    
```
##  使用说明
clone下来之后，先到soft-ware目录下，将[nacos](https://nacos.io/zh-cn/)和[sentinel](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)跑起来，然后再运行provider，consumer和gateway
##  补充：更多使用示例参见github   

  [源码](https://github.com/alibaba/spring-cloud-alibaba)  
  [官网示例](https://github.com/alibaba/spring-cloud-alibaba/tree/master/spring-cloud-alibaba-examples)  