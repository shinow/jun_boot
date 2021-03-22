
##发号器

  基于 雪花算法，redis和db 实现发号器。

#### 使用说明

##### 雪花算法
    
      使用com.hulk.sequence.util.IdWorker
    
##### redis
    
    1.首先配置spring-boot-starter-data-redis 
    2.配置yml
      xsequence:
        enable: true
        name: redis
        redis:
          bizName: seq
    3. @Autowired
       private  Sequence redisSequence;
      
##### db
  
    参考redis
    
    
    
    
    
   
