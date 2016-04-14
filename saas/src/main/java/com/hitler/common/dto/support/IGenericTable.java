package com.hitler.common.dto.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.hitler.common.repository.AggregateExpression;

/**
 * @author Lane
 * 
 * 通用表格接口
 * 
 * @param <DTO> 数据传输对象类型
 * @param <E>   实体类型
 */
public interface IGenericTable<DTO, E> extends AggregateExpression<E>, Serializable {

	/**
	 * 获取表格数据
	 * @return
	 */
	List<DTO> getData();
	
	/**
	 * 设置表格数据
	 * @param data
	 */
	void setData(List<DTO> data);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	Long getRecordsTotal();
	
	/**
	 * 设置总记录数
	 * @param recordsTotal
	 */
	void setRecordsTotal(Long recordsTotal);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	Long getRecordsFiltered();
	
	/**
	 * 设置总记录数
	 * @param recordsFiltered
	 */
	void setRecordsFiltered(Long recordsFiltered);
	
	/**
	 * 转为Map对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	Map<String, Object> map() throws IllegalAccessException, InvocationTargetException;
	
}
