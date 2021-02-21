package com.ruoyi.es.bean.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author japhet_jiu
 * @version 1.0
 *
 * indexName = "userInfo"          // es 的索引名称
 * type = "doc"                    //默认的
 *
 * 当我们springboot 启动的时候 ，引入es的依赖之后，会去看一下document的实体，如果没有配置  useServerConfiguration  则会 同步到es里面去
 * useServerConfiguration = true   //使用线上的
 * createIndex = false             // 如果没有配置，回去es删除掉这个索引名称，配置了 项目启动的时候，不会去es里面创建新的index
 *
 */
@Data
@Document(indexName = "userinfo" ,type = "doc",
        useServerConfiguration = true,createIndex = false)
public class EsUserBean {
    @Id
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String password;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private Integer age;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String phone;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String remark;
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date updateTime;
}
