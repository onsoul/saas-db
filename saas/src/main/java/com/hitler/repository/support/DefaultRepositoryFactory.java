package com.hitler.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;

public class DefaultRepositoryFactory extends JpaRepositoryFactory {
	
	public DefaultRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new GenericRepositoryImpl(entityInformation, entityManager);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return GenericRepositoryImpl.class;
	}
}
