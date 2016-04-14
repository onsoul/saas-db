package com.hitler.common.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


/**
 * @author Lane
 *
 * 合计条件接口
 *
 * @param <E> 实体类型
 */
public interface AggregateExpression<E> {
	
	List<Selection<?>> buildExpression(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder);

}
