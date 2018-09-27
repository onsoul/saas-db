package com.hitler.service.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hitler.common.repository.AggregateExpression;
import com.hitler.common.repository.SearchFilter;
import com.hitler.repository.support.DynamicSpecifications;
import com.hitler.repository.support.GenericRepository;

/**
 * 服务层基类,实现一些通用功能方法
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:31:06
 * @param <T>  
 * @param <ID> 
 */
public abstract class GenericService<T extends Persistable<ID>, ID extends Serializable>
		implements IGenericService<T, ID> {
	
	@PersistenceContext
	protected EntityManager em;
	
	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
	protected abstract GenericRepository<T, ID> getRepository();

	public GenericService() {
	}

	public GenericService(Class<?> class1) {

	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long count() {
		return getRepository().count();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long count(Collection<SearchFilter> filters) {
		return getRepository().count(
				DynamicSpecifications.<T> bySearchFilter(filters));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(T entity) {
		return getRepository().save(entity) != null;
	}

	@Override
	public int save(Iterable<T> entities) {
		return getRepository().save(entities).size();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(T entity) {
		return getRepository().update(entity) != null;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOne(ID id) {
		return getRepository().findOne(id);
	}

	@Override
	public T findOne(ID id, LockModeType lockMode) {
		return getRepository().findOne(id, lockMode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOne(Collection<SearchFilter> filters) {
		return getRepository().findOne(
				DynamicSpecifications.<T> bySearchFilter(filters));
	}

	@Override
	public T findOne(Collection<SearchFilter> filters, LockModeType lockMode) {
		return getRepository().findOne(
				DynamicSpecifications.<T> bySearchFilter(filters), lockMode);
	}
 
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll(Iterable<ID> ids) {
		return getRepository().findAll(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(ID id) {
		getRepository().delete(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void delete(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	public <S> S sum(Class<S> resultClass, Collection<SearchFilter> filters,
			String... properties) {
		return getRepository().sum(resultClass,
				DynamicSpecifications.<T> bySearchFilter(filters), properties);
	}

	@Override
	public <S> S sum(Class<S> resultClass, Collection<SearchFilter> filters,
			List<String> properties) {
		return getRepository().sum(resultClass,
				DynamicSpecifications.<T> bySearchFilter(filters), properties);
	}

	@Override
	public <S> S aggregate(Class<S> resultClass,
			Collection<SearchFilter> filters, AggregateExpression<T> expression) {
		return getRepository().aggregate(resultClass,
				DynamicSpecifications.<T> bySearchFilter(filters), expression);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}