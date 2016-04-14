package com.hitler.common.model.support;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hitler.common.model.annotation.Checked;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<PK extends Serializable> extends PersistableEntity<PK> {

	private static final long serialVersionUID = 9150700774816395865L;
	
	/**
	 * 创建者
	 */
	@Checked
	/*@CreatedBy*/
	@Column(name="CREATED_BY", columnDefinition="varchar(16) DEFAULT '' COMMENT '创建者'")
	private String createdBy;
	
	/**
	 * 创建时间
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	private Date createdDate;
	
	/**
	 * 操作者
	 */
	@Checked
	/*@LastModifiedBy*/
	@Column(name="LAST_MODIFIED_BY", columnDefinition="varchar(16) COMMENT '操作者'")
	private String lastModifiedBy;
	
	/**
	 * 操作时间
	 */
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFIED_DATE", columnDefinition="TIMESTAMP NULL COMMENT '操作时间'")
	private Date lastModifiedDate;

	@PrePersist
	public void prePresist() {
		this.createdBy = this.lastModifiedBy;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	

}
