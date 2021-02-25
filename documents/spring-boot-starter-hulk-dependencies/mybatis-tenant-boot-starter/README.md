## mybatis-tenant-boot-starter
  
  mybaits springboot多租户插件,(共享数据库，共享 Schema，共享数据表).


#### 使用说明
    
    1.添加依赖
    		<dependency>
    			<groupId>org.mybatis</groupId>
    			<artifactId>mybatis</artifactId>
    			<version>3.5.1</version>
    		</dependency>
    		<dependency>
    			<groupId>com.github.jsqlparser</groupId>
    			<artifactId>jsqlparser</artifactId>
    			<version>1.2</version>
    		</dependency>
    		<dependency>
    			<groupId>org.projectlombok</groupId>
    			<artifactId>lombok</artifactId>
    			<version>1.18.4</version>
    			<scope>provided</scope>
    		</dependency>
    
    2.添加注解
    @EnableTenantConfig
    
    3.添加配置
    mybatis:
      tenant:
        ignoreTenantTables: t_t1,t_t2  // 需要排除的表名称
        tenantIdColumn: tenant_id      // 租户字段的列名称
        tenantContextHolder: com.hulk.mybatis.tenant.holder.TenantContextSessionCookieHolder  
        //需要自己扩展的TenantIdHolder, 需继承com.hulk.mybatis.tenant.holder.TenantContextHolder接口, 可以在 session中设置全局 TenantId, 或者在cookie中设置全局 TenantId。
    