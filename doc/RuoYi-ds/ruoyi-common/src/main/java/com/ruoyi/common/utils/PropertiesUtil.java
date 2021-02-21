/**
 * Copyright  2013-6-17 第七大道-技术支持部-网站开发组
 * 自主运营平台WEB 下午2:44:04
 * 版本号： v1.0
 */

package com.ruoyi.common.utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Properties工具类
 */
public class PropertiesUtil {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
	public static Properties properties = null;
	static{
		properties = new Properties();
	}

	/**
	 * 按照classpath路径加载 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring
	 * Resource格式, loadPropertiesBySource("classpath:redis.properties");
	 */
	public static Properties loadPropertiesBySource(String... resourcesPaths) {
		Properties props = new Properties();
		for (String location : resourcesPaths) {
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return props;
	}

	public Properties getProperties() {
		return this.properties;
	}
	public static String getProp(String key) {
		if (properties != null) {
			return properties.getProperty(key);
		}
		return null;
	}

	public static String getProp(String key, String defaultValue) {
		if (properties != null) {
			return properties.getProperty(key, defaultValue);
		}
		return null;
	}

	public static boolean init(String filename) {
		if (filename != null) {
			try {
				properties = loadPropertiesBySource(filename);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 按文件路径加载 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring
	 * Resource格式, loadPropertiesByFile(
	 * "D:/work/SOC/Server/code/Commons/configs/config.properties");
	 */
	private Properties loadPropertiesByFile(String... files) throws IOException {
		Properties props = new Properties();
		for (String file : files) {
			FileInputStream stream = new FileInputStream(file);
			propertiesPersister.load(props, stream);
			stream.close();
		}
		return props;
	}
}