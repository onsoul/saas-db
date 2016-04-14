package com.hitler.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.reflect.MethodUtils;

/*
 * 反射机制--枚举类转换Map
 * @author onsoul by JT 2015-6-24 下午4:32:23
 * */
public class EumnUtil {

	public static String getText(Class<?> ref, Object code) {
		return parseEumn(ref).get(TransformUtils.toString(code));
	}

	// 通过枚举转换成Map(不带数值)
	public static <T> Map<String, String> parseEumn(Class<T> ref) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (ref.isEnum()) {
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				String text = getInvokeValue(t, "getText");
				Enum<?> tempEnum = (Enum<?>) t;
				if (text == null) {
					text = tempEnum.name();
				}
				map.put(text, "");
			}
		}
		return map;
	}

	// 通过枚举转换成Map(带数值)
	public static <T> Map<String, String> parseEumnTextAndValue(Class<T> ref, Object obj) {

		Class clazz = obj.getClass();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (ref.isEnum()) {
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				String text = getInvokeValue(t, "getText");
				Enum<?> tempEnum = (Enum<?>) t;
				if (text == null) {
					text = tempEnum.name();
				}
				try {
					PropertyDescriptor pd = new PropertyDescriptor(text, clazz);
					Method getMethod = pd.getReadMethod();// 获得get方法
					Object value = getMethod.invoke(obj);// 执行get方法返回一个Object
//					System.out.println(value);
					map.put(text, value.toString());
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	static <T> String getInvokeValue(T t, String methodName) {
		try {
			Method method = MethodUtils.getAccessibleMethod(t.getClass(),
					methodName);
			String text = TransformUtils.toString(method.invoke(t));
			return text;
		} catch (Exception e) {
			return null;
		}
	}
}