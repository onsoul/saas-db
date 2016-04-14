package com.hitler.common.exception;

import com.hitler.common.exception.BusinessException;

/**
 * 记录不存在
 * @author User
 *
 */
public class EntityNotExistsException extends BusinessException {

	private static final long serialVersionUID = -563592502981718470L;
	
	private static final String CODE = EntityNotExistsException.class.getName();
	
	public static final EntityNotExistsException ERROR = new EntityNotExistsException();
	
	public EntityNotExistsException() {
		super(CODE);
	}

	public EntityNotExistsException(String code) {
		super(code);
	}

}
