package org.springrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 主入口,排除@Controller注解,主要为了Controller指定命名规则. 这个类所在的包,就是默认扫描的根包.
 * 注意:只能有一个@SpringBootApplication入口文件,如果其他模块引用了这个模块,就需要把类上的注解都注释掉
 *
 * @author caomei
 */
@SpringBootApplication
@ComponentScan(basePackages = {"${springrain.basepackagepath}"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class SpringrainApplication {

    public static void main(String[] args) {


        //添加用户默认有的路径权限
        // GlobalStatic.userDefaultUrl.add("/upload/**");
        //添加不拦截的URL地址
        // GlobalStatic.excludePathPatterns.add("/upload/**");

        SpringApplication.run(SpringrainApplication.class, args);
    }

}
