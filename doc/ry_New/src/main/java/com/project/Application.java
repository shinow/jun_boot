package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.project.*.mapper")
public class Application {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-----.            __        \n" +
                " |       |       | \\   \\   /  /    \n" +
                " |       |       |  '       \n" +
                "  -----、|       | (_ o _)'          \n" +
                "       | |       |  ||   |(_,_)'         \n" +
                "       | \\     /    /|   `-'  /           \n" +
                " ------'   -----    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}