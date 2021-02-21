package com.ruoyi;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.IpUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * 启动程序
 * , MongoAutoConfiguration.class
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext context = SpringApplication.run(RuoYiApplication.class, args);
        printKeyLoadMessage(context);
    }

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        // 项目端口
        String port = environment.getProperty("server.port");
        // 项目路径
        String contextPath = environment.getProperty("server.servlet.context-path");
        // 项目名称
        // String projectFinalName = environment.getProperty("server.servlet.context-path");

        String homeUrl = "http://" + IpUtils.getHostIp() + ":" + port + contextPath;
        String swaggerUrl = "http://" + IpUtils.getHostIp() + ":" + port + contextPath+"/swagger-ui.html";
        //http://127.0.0.1:8888/silence/swagger-ui.html
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n            (♥◠‿◠)ﾉﾞ  Ruoyi启动成功   ლ(´ڡ`ლ)ﾞ  \r\n");
        sb.append("\r\n    欢迎使用 " + Global.getName() + "  - Ruoyi.Version : "+Global.getVersion()+".RELEASE\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        System.out.println("\thomeUrl:    " + homeUrl);
        System.out.println("\tswaggerUrl: " + swaggerUrl);
        System.out.println("\r");
        return true;
    }


    public static void printInitBean(ConfigurableApplicationContext context){
        // 打印所有的Bean
        // String[] beanNames = context.getBeanDefinitionNames();
        // 打印所有添加该注解的bean
        String[] beanNames = context.getBeanNamesForAnnotation(Component.class);
        System.out.println("bean总数:{}"+context.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            System.out.println("{},beanName:{}"+(++i)+str);
        }
    }
}