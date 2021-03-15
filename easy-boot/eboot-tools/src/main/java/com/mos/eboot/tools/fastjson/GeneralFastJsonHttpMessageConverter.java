package com.mos.eboot.tools.fastjson;

import java.util.Arrays;

import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class GeneralFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter{
	
	public GeneralFastJsonHttpMessageConverter() {
		setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, new MediaType("application", "*+json")));
		FastJsonConfig fastJsonConfig = getFastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat);
	}

}
