package com.hitler.common.model.support;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@EntityListeners(CheckingEntityListener.class)
public abstract class CheckableEntity<PK extends Serializable> extends AuditableEntity<PK> {

	private static final long serialVersionUID = 2469813719494517116L;
}
