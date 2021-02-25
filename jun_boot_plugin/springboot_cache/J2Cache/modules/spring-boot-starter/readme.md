如下即可使用j2cache缓存方法
```
@Autowired
private CacheChannel cacheChannel;
```
在application.properties中支持指定j2cache配置文件，让你开发环境和生产环境分离
```
j2cache.config-location=/j2cache-${spring.profiles.active}.properties
```
如下两项配置在application.properties,可以开启对spring cahce的支持
```
j2cache.open-spring-cache=true  
spring.cache.type=GENERIC
```
如下两项配置在application.properties,可以设置spring cache是否缓存null值，默认是true
```
j2cache.allow-null-values=true
```

如下配置在application.properties,可以选择缓存清除的模式   
* 缓存清除模式
* active:主动清除，二级缓存过期主动通知各节点清除，优点在于所有节点可以同时收到缓存清除
* passive:被动清除，一级缓存过期进行通知各节点清除一二级缓存
* blend:两种模式一起运作，对于各个节点缓存准确性以及及时性要求高的可以使用（推荐使用前面两种模式中一种） 
```
j2cache.cache-clean-mode=passive
```
在j2cache.properties中配置,可以使用springRedis进行广播通知缓失效
```
j2cache.broadcast = net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
```
在j2cache.properties中配置,使用springRedis替换二级缓存
```
j2cache.L2.provider_class = net.oschina.j2cache.cache.support.redis.SpringRedisProvider
j2cache.L2.config_section = redis
```


