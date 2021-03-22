package com.pearadmin.pro.common.config;

import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;

/**
 * Describe: Swagger 接口文档
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Pear Admin 系统")
                .description("企业级 Spring Boot 开发平台")
                .termsOfServiceUrl("http://www.pearadmin.com")
                .contact("就眠仪式")
                .version("1.0")
                .build();
    }
}
