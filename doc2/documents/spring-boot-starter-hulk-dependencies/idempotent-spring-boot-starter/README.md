
幂等验证，防止重复提交

#### 使用说明
    
    1.首先配置spring-boot-starter-data-redis 
    
    2.添加注解启用幂验证
    @EnableIdempotentConfig
    
    3.使用@IdempotentGenerator 创建幂
    
    4.使用@IdempotentBarrier 验证幂
    
    5.用@ControllerAdvice拦截com.hulk.idempotent.exception.IdempotentException异常
    
   