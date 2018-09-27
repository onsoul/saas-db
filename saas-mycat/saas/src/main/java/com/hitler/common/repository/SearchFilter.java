package com.hitler.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Lists;

public class SearchFilter implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(SearchFilter.class);

	private static final long serialVersionUID = 8719997267153871707L;

	public static final String PREFIX = "search_";

	public enum Operator {
		/**
		 * EQ : 等于 = 
		 * NE : 不等于 != 
		 * LIKE : 相似 = like("%" + value + "%") 
		 * PLIKE : like("%" + value) 
		 * ALIKE : like(value + "%") 
		 * GT : 大于 > 
		 * LT : 小于 < 
		 * GTE : 大于等于 >= 
		 * LTE : 小于等于 <= IN : id in (1,2,3)
		 */
		EQ, NE, LIKE, PLIKE, ALIKE, GT, LT, GTE, LTE, IN, OR
	}

	private String fieldName;
	private Object value;
	private Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		logger.debug("###search parameter:"+fieldName + "<[" + operator + "]>" + value);
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

	public Operator getOperator() {
		return operator;
	}

	public static List<SearchFilter> parse(ServletRequest request) {
		return parse(WebUtils.getParametersStartingWith(request, PREFIX));
	}

	public static List<SearchFilter> parse(Map<String, Object> searchParams) {
		List<SearchFilter> filters = Lists.newArrayList();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key
						+ " is not a valid search filter name");
			}
			String fieldName = names[1];
			Operator operator = Operator.valueOf(names[0]);
			SearchFilter filter = new SearchFilter(fieldName, operator, value);
			filters.add(filter);
		}
		return filters;
	}

}
