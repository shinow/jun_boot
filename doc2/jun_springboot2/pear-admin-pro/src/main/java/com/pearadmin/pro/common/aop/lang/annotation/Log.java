package com.pearadmin.pro.common.aop.lang.annotation;

import com.pearadmin.pro.common.aop.lang.enums.Business;
import java.lang.annotation.*;

/**
 * Describe: 行 为 日 志
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
public @interface Log {

    /**
     * 默认无参输入
     * */
    String value() default "暂无标题";

    /**
     * Title 默认输入
     * */
    String title() default "暂无标题";

    /**
     * Describe 默认输入
     * */
    String describe() default "暂无介绍";

    /**
     * Business 业务类型
     * */
    Business business() default Business.SELECT;
}
