package com.hitler.repository.support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.hitler.common.repository.AggregateExpression;


@NoRepositoryBean
public interface GenericRepository<E extends Persistable<PK>, PK extends Serializable> extends JpaRepository<E, PK>, JpaSpecificationExecutor<E> {

	EntityManager getEntityManager();
	
	E findOne(PK id, LockModeType lockMode);
	E findOne(Specification<E> spec, LockModeType lockMode);
	
	List<E> findTop(int top, Specification<E> spec, Sort sort);
	List<E> findTop(int top, Specification<E> spec, Sort sort, LockModeType lockMode);
	
	<S extends E> S update(S entity);
	
	Path<? extends Number> getAttributeName(Root<E> root, String attributeName);

	 
	<S> S sum(Class<S> resultClass, Specification<E> spec, String... properties);
	<S> S sum(Class<S> resultClass, Specification<E> spec, List<String> properties);
	 
	<S> S sum(Class<S> resultClass, Specification<E> spec, LockModeType lockMode, String... properties);
	<S> S sum(Class<S> resultClass, Specification<E> spec, LockModeType lockMode, List<String> properties); 
	<S> S aggregate(Class<S> resultClass, Specification<E> spec, AggregateExpression<E> expression);
	<S> S aggregate(Class<S> resultClass, Specification<E> spec, AggregateExpression<E> expression, LockModeType lockMode);
	
	<S> List<S> groupBy(Class<S> resultClass,Specification<E> spec, AggregateExpression<E> expression);
	
	int updateByHQL(String hql, Map<String, Object> parameters);
}
