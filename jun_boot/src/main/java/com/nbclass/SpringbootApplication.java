package com.nbclass;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.nbclass.modules.*.mapper")
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringbootApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}