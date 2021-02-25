
接口防刷

#### 使用说明
    
    1.首先配置spring-boot-starter-data-redis 
    
    2.设置全局限流配置
        limitb.enabled=true
        limitb.replenish=2
        limitb.capacity=5
        limitb.LimitbType=CUSTOMER
        limitb.LimitbStrategy=REDIS
    
    3.在方法上添加@Limitb 注解启用接口方法防刷
    
    4.在方法参数上添加@LimitbKey注解，指定本字段为防刷关键字。
      并设置防刷策略。  
    
    5.用@ControllerAdvice拦截com.hulk.limitb.exception.LimitbException异常

#### 备注
     防刷是对某个条件进行的，粒度比限流更加细致，比如根据条件为登录用户，或者下游流水号，的高频查询。
   