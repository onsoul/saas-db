package com.hitler.service.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.LockModeType;

import com.hitler.common.repository.AggregateExpression;
import com.hitler.common.repository.IPageResults;
import com.hitler.common.repository.IPageable;
import com.hitler.common.repository.SearchFilter;
import com.hitler.common.repository.Sorter;

/**
 * DAO 通用接口，该接口定义DAO的通用方法
 * 
 * @author 2015-04-25
 */
public interface IGenericService<T, ID extends Serializable> {

	/**
	 * 持久化实体对象到数据库
	 * 
	 * @param entity
	 * @return
	 */
	boolean save(T entity);

	/**
	 * 持久化实体对象列表到数据库
	 * 
	 * @param entity
	 * @return
	 */
	int save(Iterable<T> entities);

	/**
	 * 修改数据库的持久化对象
	 * 
	 * @param entity
	 * @return
	 */
	boolean update(T entity);

	/**
	 * 查找指定主键的对象是否存在数据库
	 * 
	 * @param entity
	 * @return
	 */
	boolean exists(ID id);

	/**
	 * 查找指定主键的对象并返回
	 * 
	 * @param entity
	 * @return
	 */
	T findOne(ID id);

	/**
	 * 查找指定主键的对象并返回
	 * 
	 * @param entity
	 * @return
	 */
	T findOne(ID id, LockModeType lockMode);

	T findOne(Collection<SearchFilter> filters);

	/**
	 * 采用锁模式查找指定主键的对象并返回
	 * 
	 * @param entity
	 * @return
	 */
	T findOne(Collection<SearchFilter> filters, LockModeType lockMode);

	List<T> findAll();

	List<T> findAll(Iterable<ID> ids);

	void delete(ID id);

	void delete(T entity);

	void delete(Iterable<T> entities);

	void deleteAll();

	long count();

	long count(Collection<SearchFilter> filters);
	
	<S> S sum(Class<S> resultClass, Collection<SearchFilter> filters,
			String... properties);

	<S> S sum(Class<S> resultClass, Collection<SearchFilter> filters,
			List<String> properties);
	
	<S> S aggregate(Class<S> resultClass, Collection<SearchFilter> filters,
			AggregateExpression<T> expression);
	
/*	public <E> GenericTable<T> findTable(Collection<SearchFilter> filters, IPageable pageable) throws Exception;*/
}
