package com.ruoyi.web.controller.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * Logger for this class
     */
    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final class SingletonHolder {
        private static final PropertiesUtil INSTANCE = new PropertiesUtil();
    }

    public static PropertiesUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
    private static ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
    private Properties properties = null;

    /**
     * 按照classpath路径加载 载入多个properties文件
     * 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring
     * Resource格式, loadPropertiesBySource("classpath:redis.properties");
     */
    public static Properties loadPropertiesBySource(String... resourcesPaths) {
        Properties props = new Properties();
        for (String location : resourcesPaths) {
            log.debug("Loading properties file from:" + location);
            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
            } catch (IOException ex) {
                log.error("Could not load properties from classpath:" + location + ": " + ex.getMessage());
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("", e);
                    }
                }
            }
        }
        return props;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public String getProperties(String key, String defaultValue) {
        if (properties != null) {
            return properties.getProperty(key, defaultValue);
        }
        return null;
    }

    public boolean init(String filename) {
        if (filename != null) {
            try {
                properties = loadPropertiesByFile(filename);
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