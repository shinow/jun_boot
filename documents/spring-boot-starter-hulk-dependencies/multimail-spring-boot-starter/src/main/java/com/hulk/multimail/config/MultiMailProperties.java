package com.hulk.multimail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hulk
 * @date: 2020/7/14
 * <p>
 * @Description:
 * @Modified:
 */
@Data
@ConfigurationProperties(prefix = "multi.mail")
public class MultiMailProperties{


    private ArrayList<String> hostList;
    private ArrayList<Integer> portList;
    private ArrayList<String> fromList ;
    private ArrayList<String> usernameList;
    private ArrayList<String> passwordList;

    private String protocol = "smtp";
    private Charset defaultEncoding;
    private Map<String, String> properties;
    private String jndiName;

    public MultiMailProperties() {
        this.defaultEncoding = StandardCharsets.UTF_8;
        this.properties = new HashMap();
    }


}
