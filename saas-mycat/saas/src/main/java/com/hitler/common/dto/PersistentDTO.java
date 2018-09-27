package com.hitler.common.dto;

import java.io.Serializable;

import com.hitler.common.dto.support.GenericDTO;


/**
 * 数据持久对象
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:20:13
 * @param <PK> 主键
 */
public abstract class PersistentDTO<PK extends Serializable> extends GenericDTO<PK> {

	private static final long serialVersionUID = -7604988709496150282L;

}
