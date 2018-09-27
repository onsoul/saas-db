package com.hitler.common.dto.support;

import java.io.Serializable;

/**
 * @author Lane
 *
 * 数据传输对象接口
 *
 */
public interface IGenericDTO<PK extends Serializable> extends Serializable {

	PK getId();
	
	void setId(PK id);
	/**
	 * 构造Create DTO
	 */
//	public void makeCreateDTO();
	
	/**
	 * 构造Update DTO
	 */
//	public void makeUpdateDTO();
}
