package com.hitler.common.model.support;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

/**
 * 主键不需要自增长
 * 
 * @author 
 *
 * @param <PK>
 */
@MappedSuperclass
public abstract class CustomPKPersistableEntity<PK extends Serializable> implements Persistable<PK> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4129264629296054711L;
	
	@Id
	private PK id;
	
	@Override
	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
	@Override
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		CustomPKPersistableEntity<?> that = (CustomPKPersistableEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}
