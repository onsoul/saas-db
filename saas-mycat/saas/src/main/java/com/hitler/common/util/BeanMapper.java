package com.hitler.common.util;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class BeanMapper {
	
	private static final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static void map(Object source, Object destination) {
		mapper.map(source, destination);
	}
	
	public static <D> D map(Object source, Class<D> destinationClass) {
		return mapper.map(source, destinationClass);
	}
	
	public static <S, D> List<D> map(List<S> source, Class<D> destinationClass) {
		List<D> dest = Lists.newArrayList();
		for (S element : source) {
			dest.add(mapper.map(element, destinationClass));
		}
		return dest;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object obj) {
		return objectMapper.convertValue(obj, Map.class);
	}

}
