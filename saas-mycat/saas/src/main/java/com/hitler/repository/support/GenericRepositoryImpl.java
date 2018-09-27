package com.hitler.repository.support;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.Session;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.google.common.collect.Lists;
import com.hitler.common.repository.AggregateExpression;

public class GenericRepositoryImpl<E extends Persistable<PK>, PK extends Serializable> extends SimpleJpaRepository<E, PK> implements GenericRepository<E, PK> {

	protected EntityManager em;
	
	public GenericRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	public GenericRepositoryImpl(Class<E> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getMetadata(domainClass, em), em);
	}
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public E findOne(PK id, LockModeType lockMode) {
		return em.find(getDomainClass(), id, lockMode);
	}
	
	@Override
	public E findOne(Specification<E> spec, LockModeType lockMode) {
		try {
			return getQuery(spec, (Sort) null).setLockMode(lockMode).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<E> findTop(int top, Specification<E> spec, Sort sort) {
		return findTop(top, spec, sort, LockModeType.NONE);
	}
	
	@Override
	public List<E> findTop(int top, Specification<E> spec, Sort sort, LockModeType lockMode) {
		return getQuery(spec, sort).setLockMode(lockMode).setMaxResults(top).getResultList();
	}

	@Override
	public <S extends E> S update(S entity) {
		Session session = em.unwrap(Session.class);
		session.update(entity); //使用update方法提高效率
		return entity;
	}
	
	private <S> S aggregate(CriteriaBuilder builder, CriteriaQuery<S> query, Root<E> root, Specification<E> spec, List<Selection<?>> selectionList, LockModeType lockMode) {
		if (selectionList != null) {
			Predicate predicate = spec.toPredicate(root, query, builder);
			if (predicate != null) {
				query.where(predicate);
			}
			query.multiselect(selectionList);
			return (S) em.createQuery(query).setLockMode(lockMode).getSingleResult();
		}
		return null;
	}
	
	@Override
	public <S> S aggregate(Class<S> resultClass, Specification<E> spec, AggregateExpression<E> expression, LockModeType lockMode) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<S> query = builder.createQuery(resultClass);
		Root<E> root = query.from(getDomainClass());
		List<Selection<?>> selectionList = expression.buildExpression(root, query, builder);
		return aggregate(builder, query, root, spec, selectionList, lockMode);
	}

	@Override
	public <S> S aggregate(Class<S> resultClass, Specification<E> spec, AggregateExpression<E> expression) {
		return aggregate(resultClass, spec, expression, LockModeType.NONE);
	}
	
	@Override
	public Path<? extends Number> getAttributeName(Root<E> root, String attributeName) {
		return root.get(attributeName);
	}
	
	@Override
	public <S> S sum(Class<S> resultClass, Specification<E> spec, LockModeType lockMode, List<String> properties) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<S> query = builder.createQuery(resultClass);
		Root<E> root = query.from(getDomainClass());
		List<Selection<?>> selectionList = Lists.newArrayList();
		for (String property : properties) {
			selectionList.add(builder.sum(getAttributeName(root, property)));
		}
		return aggregate(builder, query, root, spec, selectionList, lockMode);
	}
	
	@Override
	public <S> S sum(Class<S> resultClass, Specification<E> spec, List<String> properties) {
		return sum(resultClass, spec, LockModeType.NONE, properties);
	}

	@Override
	public <S> S sum(Class<S> resultClass, Specification<E> spec, String... properties) {
		return sum(resultClass, spec, Arrays.asList(properties));
	}

	@Override
	public <S> S sum(Class<S> resultClass, Specification<E> spec, LockModeType lockMode, String... properties) {
		return sum(resultClass, spec, lockMode, Arrays.asList(properties));
	}

	@Override
	public int updateByHQL(String hql, Map<String, Object> parameters) {
		Query query = em.createQuery(hql);
		if(parameters != null) {
			Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, Object> entry = it.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.executeUpdate();
	}

	@Override
	public <S> List<S> groupBy(Class<S> resultClass, Specification<E> spec,
			AggregateExpression<E> expression) {
		// TODO Auto-generated method stub
		return null;
	}

}
