MDC实现全链路调用日志跟踪。

#### 使用说明
    
   
    
    1.添加启用注解
      @EnableFeignMdcConfig 启动 feign拦截器 实现feign调用的链路日志追踪。
    
    2.HttpClientMdcWrapper.newMdcInstance() 实现HttpClient调用的链路日志追踪。
    
    3.OkHttpMdcWrapper.newMdcInstance() 实现OkHttp调用的链路日志追踪。
    
    4.RestTemplateMdcWrapper.newMdcInstance() 实现RestTemplate调用的链路日志追踪。
    
    5.ThreadPoolExecutorMdcWrapper  代替ThreadPoolExecutor线程池链路日志追踪。
    
    6.SpringMvcInterceptor  注册自定义拦截器，添加拦截路径和排除拦截路径
      registry.addInterceptor(new SpringMvcInterceptor()).addPathPatterns("/**");
    
##  日志配置
    <property name="pattern">[TRACEID:%X{traceId}] %d{HH:mm:ss.SSS} %-5level %class{-1}.%M()/%L - %msg%xEx%n</property>
    重点是%X{traceId}
    
#### TODO
     1.消息中间件如MQ traceId跟踪
     2.定时任务 traceId跟踪
    
   
