package com.mos.eboot.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 巩瑞启
 * @date 2018/5/9 15:16
 * @description
 */
@ConfigurationProperties(prefix = "custom")
public class MyPropsConstants {

	private String uploadPath;

	private String imageViewPath;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getImageViewPath() {
		return imageViewPath;
	}

	public void setImageViewPath(String imageViewPath) {
		this.imageViewPath = imageViewPath;
	}
}
