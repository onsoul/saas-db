package com.hitler.core.db.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 此类以集合类型的方式声明持久类到Mongo Context,以提供高度自由的查询结果装载 简单说，可以使用它装载所有MongoDB
 * Doc(DBCollection=>DBObject)
 * 
 */
@JsonIgnoreProperties
public class Docs extends LinkedHashMap<String, Object> implements Map<String, Object> {

	private static final long serialVersionUID = -5701882427325141906L;
}