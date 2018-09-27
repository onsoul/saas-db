package com.hitler.core.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * 所有Entity的基础类
 * @author onsoul
 * 2018年7月31日 下午2:09:39
 */
public abstract class BaseDocument<PK extends Serializable> implements Serializable {
	 
	private static final long serialVersionUID = 7540028943332264289L;
	
	@Id
	protected PK id;
	
	@CreatedDate 
	protected Date createdAt;
	
	@LastModifiedDate
	protected Date updatedAt;
	
	@CreatedBy
	protected String createdBy;
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public PK getId() {
		return id;
	}
	
	public void setId(PK id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
