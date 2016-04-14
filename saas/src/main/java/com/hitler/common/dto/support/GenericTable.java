package com.hitler.common.dto.support;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.google.common.collect.Lists;
import com.hitler.common.util.BeanMapper;

/**
 * @author Lane
 *
 * 通用表格抽象类
 *
 * @param <DTO>
 * @param <E>
 */
public abstract class GenericTable<DTO, E> implements IGenericTable<DTO, E> {

	private static final long serialVersionUID = 6655607942888642862L;
	
	private List<DTO> data;
	
	private Long recordsTotal;
	
	private Long recordsFiltered;

	@Override
	public List<DTO> getData() {
		return data;
	}

	@Override
	public void setData(List<DTO> data) {
		this.data = data;
	}

	@Override
	public Long getRecordsTotal() {
		return recordsTotal;
	}

	@Override
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	@Override
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	@Override
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	@Override
	public Map<String, Object> map() throws IllegalAccessException, InvocationTargetException {
		return BeanMapper.objectToMap(this);
	}

	protected Path<? extends Number> getAttributeName(Root<E> root, String attributeName) {
		return root.get(attributeName);
	}
	
	protected List<Selection<?>> getSelections(Root<E> root, CriteriaBuilder builder, String... attributeNames) {
		List<Selection<?>> columns = Lists.newArrayList();
		for(String attributeName : attributeNames) {
			columns.add(builder.sum(this.getAttributeName(root, attributeName)));
		}
		return columns;
	}
}
