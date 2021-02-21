package com.ruoyi.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON 序列化 反序列化机制
 * <p>
 * Created by wangming on 2016/10/11.
 */
public class JSON {
	public static final Logger log = LogManager.getLogger(JSON.class);

	/**
	 * 当ObjectMapper 被共享以后, 只要不再对其进行重新配置, 那么共享出来的实例就是线程安全的. 在static block中进行
	 * 初始化和配置.
	 * <p>
	 * TODO 目前只使用一个ObjectMapper实例, 如果出现不同的需求时, 可以再添加其他的ObjectMapper, 进行个性化定制
	 */
	private static final ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(Double.class, new CustomDoubleSerializer());
		module.addSerializer(double.class, new CustomDoubleSerializer());
		module.addSerializer(Float.class, new CustomDoubleSerializer());
		module.addSerializer(float.class, new CustomDoubleSerializer());
		mapper.registerModule(module);

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
	}

	public static JsonNode toJsonNode(File json) {
		if (json == null || !json.exists()) {
			return null;
		}
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			log.error(json, e);
			return null;
		}
	}

	public static JsonNode toJsonNode(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			log.error(json, e);
			return null;
		}
	}

	/**
	 * json转换成java对象
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			log.error(json, e);
			return null;
		}
	}

	public static <T> T toObject(File json, Class<T> clazz) {
		if (json == null || !json.exists()) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			log.error(json, e);
			return null;
		}
	}

	/**
	 * 将java对象转换成json
	 *
	 * @param t
	 * @return
	 */
	public static String toJson(Object t) {
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			log.error("", e);
			return null;
		}
	}

	/**
	 * 将json转换成list
	 * <p>
	 * 默认地使用ArrayList 作为List实现
	 *
	 * @param jsonStr
	 * @param elementClass
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(String jsonStr, Class<T> elementClass) {
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, elementClass);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			log.error(jsonStr, e);
			return null;
		}
	}

	/**
	 * 将json转换成map 默认地使用HashMap 作为Map实现
	 *
	 * @param jsonStr
	 * @param keyClass
	 * @param valueClass
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> Map<K, V> toMap(String jsonStr, Class<K> keyClass, Class<V> valueClass) {
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}

		JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			log.error(jsonStr, e);
			return null;
		}
	}

	public static <K, V> Map<K, V> toMap(File jsonStr, Class<K> keyClass, Class<V> valueClass) {
		JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			log.error(jsonStr, e);
			return null;
		}
	}

	public static class CustomDoubleSerializer extends JsonSerializer<Number> {

		@Override
		public void serialize(Number value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			if (null == value) {
				jgen.writeNull();
			} else if (value instanceof Double || value instanceof Float) {
				final String pattern = ".##";
				final DecimalFormat myFormatter = new DecimalFormat(pattern);
				final String output = myFormatter.format(value);
				jgen.writeNumber(output);
			}
		}
	}
}
