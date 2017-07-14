一.调用链路从zuul-user-order-city，想了半天场景，
这个demo就算是根据userId获取用户信息，用户信息里
面有订单信息，需要调用外部订单服务，订单服务里面有
个城市信息，需要再次调用城市服务。

二.本周二演示时的demo的几个问题已经解决：

<1>zuul微服务的再次调用，当时怀疑是zuul的超时重试，
其实不是，是http的默认超时30s，超时之后会走"error"
类型的filter：SendErrorFilter这里会foward请求，
重新交给dispatcherServlet处理一个error的请求，
最后会交由BasicErrorController.error处理，这个是
springmvc的处理逻辑，所以会走mvcInterceptor，但是
正常的由zuul通过ribbon调用其他服务是不走mvcInterc-
eptor，debug时可以通过禁用SendErrorFilter防止干扰。

<2>多配置文件的配置已经完成，其实就是server.port不同，
同时maven中加入对应plugin去解析start-class，本demo
中alpha、beta、prod三套环境。
利用：java -jar XXX.jar --spring.profiles.active=XX

<3>core包已经更新，命名以及header采用threadlocal处理。
重要：core包是其他所有微服务一定要引用进去使用的！

<4>在3个service处增加log，可在console稍微更加直观的查看
调用的实例port，更利于测试通过core包注入的restTemplate
结合ribbon中自定义的rule去匹配metadata.zone并负载均衡
的分发请求。

<5>周二提到了label为空，其实处理很简单，自定义的Rule里面
增加逻辑即可，Rule的可扩展性很大，也比较灵活。

三.启动顺序:
    config---eureka---others

