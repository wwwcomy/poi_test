package com.iteye.wwwcomy.poi.util;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iteye.wwwcomy.authservice.exception.SysInternalException;

public class ObjectMapperUtil {

	public static ObjectMapper objectMapper = new ObjectMapper();

	private static Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);

	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String writeValueAsString(Object o) {
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new SysInternalException("Failed to convertObjectToJsonString for Object " + String.valueOf(o), e);
		}
	}

	public static <T> T readValue(String jsonString, TypeReference<T> tr) {
		if (StringUtils.isBlank(jsonString)) {
			if (null == jsonString) {
				return null;
			}
			// if the TypeReference is an interface, calling any method of
			// that instance will return null
			logger.warn("The input value is empty, trying to generate an new instance.");
			try {
				return instantiate(tr);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
		try {
			return objectMapper.readValue(jsonString, tr);
		} catch (IOException e) {
			throw new SysInternalException("Failed to convertStringToObject for String " + jsonString, e);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T instantiate(TypeReference<T> tr) {
		Class<T> trClass = convert(tr);
		if (trClass.isInterface()) {
			return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new java.lang.Class[] { trClass }, new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws java.lang.Throwable {
							return null;
						}
					});
		} else if (Modifier.isAbstract(trClass.getModifiers())) {
			// Did not find good solutions for abstract class, returning null directly
			return null;
		} else {
			return BeanUtils.instantiate(trClass);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> Class<T> convert(TypeReference<T> ref) {
		if (ref.getType() instanceof Class) {
			return (Class<T>) ref.getType();
		}
		return (Class<T>) ((ParameterizedType) ref.getType()).getRawType();
	}

}
