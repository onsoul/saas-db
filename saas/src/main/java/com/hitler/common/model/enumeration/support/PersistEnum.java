package com.hitler.common.model.enumeration.support;

import java.util.Map;

/**
 * 需要持久化的enum类，都需要实现的接口
 *
 * @author Kylin
 * 2015-7-23 下午4:11:04
 * @param <E>
 */
public interface PersistEnum<E extends Enum<?>> {
	/**
     * 获取被持久化字段的值
     * 
     * @return 被持久化字段的值
     */
    String getPersistedValue();
     
    /**
     * 由被持久化的字段的值获取枚举类型
     * 
     * @param persistedValue
     * @return
     */
    E returnEnum(String persistedValue);
     
    /**
     * 获取枚举的所有枚举项
     * 
     * @return map
     */
    Map<String, E> getAllValueMap();
}
