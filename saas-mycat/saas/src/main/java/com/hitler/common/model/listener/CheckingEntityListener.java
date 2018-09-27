package com.hitler.common.model.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.util.ReflectionUtils.AnnotationFieldFilter;
import org.springframework.util.ReflectionUtils;

import com.hitler.common.model.annotation.Checkable;
import com.hitler.common.util.MD5Utils;

public class CheckingEntityListener {
	
	private static final Log LOGGER = LogFactory.getLog(CheckingEntityListener.class);
	
	private static final String MD5_KEY = "19880704";
	
	@PrePersist
	@PreUpdate
	public void encode(Object target) {
		AnnotationCheckingMetadata metadata = AnnotationCheckingMetadata.getMetadata(target.getClass());
		if (metadata.isCheckable()) {
			StringBuilder sb = new StringBuilder();
			for (Field field : metadata.getCheckedFields()) {
				ReflectionUtils.makeAccessible(field);
				Object value = ReflectionUtils.getField(field, target);
				if (value instanceof Date) {
					throw new RuntimeException("不支持时间类型字段加密！");
				}
				sb.append(value).append(" - ");
			}
			sb.append(MD5_KEY);
			LOGGER.debug("加密数据：" + sb);
			String hex = MD5Utils.encode(sb.toString());
			Field checksumField = metadata.getCheckableField();
			ReflectionUtils.makeAccessible(checksumField);
			ReflectionUtils.setField(checksumField, target, hex);
		}
	}
	
	@PostLoad
	public void checking(Object target) {
		AnnotationCheckingMetadata metadata = AnnotationCheckingMetadata.getMetadata(target.getClass());
		if (metadata.isCheckable()) {
			StringBuilder sb = new StringBuilder();
			for (Field field : metadata.getCheckedFields()) {
				ReflectionUtils.makeAccessible(field);
				Object value = ReflectionUtils.getField(field, target);
				if (value instanceof Date) {
					throw new RuntimeException("不支持时间类型字段解密！");
				}
				sb.append(value).append(" - ");
			}
			sb.append(MD5_KEY);
			LOGGER.debug("验证数据：" + sb);
			String hex = MD5Utils.encode(sb.toString());
			Field checksumField = metadata.getCheckableField();
			ReflectionUtils.makeAccessible(checksumField);
			String checksum = (String) ReflectionUtils.getField(checksumField, target);
			if (!checksum.equals(hex)) {
				//throw new RuntimeException("数据验证失败！");
			}
		}
	}

}

class AnnotationCheckingMetadata {
	
	private static final Map<Class<?>, AnnotationCheckingMetadata> METADATA_CACHE = new ConcurrentHashMap<Class<?>, AnnotationCheckingMetadata>();
	
	private static final AnnotationFieldFilter CHECKABLE_FILTER = new AnnotationFieldFilter(Checkable.class);
	private static final AnnotationFieldFilter CHECKED_FILTER = new AnnotationFieldFilter(Checkable.class);
	
	private Field checkableField;
	private List<Field> checkedFields = new ArrayList<Field>();
	
	public AnnotationCheckingMetadata(Class<?> type) {
		while (type != Object.class) {
			for (Field field : type.getDeclaredFields()) {
				if (CHECKABLE_FILTER.matches(field)) {
					this.checkableField = field;
				} else if (CHECKED_FILTER.matches(field)) {
					this.checkedFields.add(field);
				}
			}
			type = type.getSuperclass();
		}
	}
	
	public static AnnotationCheckingMetadata getMetadata(Class<?> type) {

		if (METADATA_CACHE.containsKey(type)) {
			return METADATA_CACHE.get(type);
		}

		AnnotationCheckingMetadata metadata = new AnnotationCheckingMetadata(type);
		METADATA_CACHE.put(type, metadata);
		return metadata;
	}
	
	public boolean isCheckable() {
		if (checkableField == null || checkedFields.size() == 0) {
			return false;
		}
		return true;
	}

	public Field getCheckableField() {
		return checkableField;
	}

	public List<Field> getCheckedFields() {
		return checkedFields;
	}
	
	
}
