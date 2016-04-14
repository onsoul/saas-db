package com.hitler.model.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hitler.common.model.annotation.Checked;
import com.hitler.common.model.support.UserTreeRelationEntity;

/**
 * 记录模型
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:06:33
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_RECORD", indexes = {
		@Index(name = "IDX_USER_ID", columnList = "USER_ID"),
		@Index(name = "IDX_PARENT_ID", columnList = "PARENT_ID") })

public class Record extends UserTreeRelationEntity<Integer> {

	private static final long serialVersionUID = -6378188431615185271L;

	/**
	 * 金额 
	 */
	@Checked
	@Column(name = "AMOUNT", columnDefinition = "DECIMAL(8,3) DEFAULT 0.0 COMMENT '单注金额'", nullable = false)
	private Double Amount;

	/**
	 *  总额 
	 */
	@Checked
	@Column(name = "LINE_TOTAL", columnDefinition = "DECIMAL(10,3) DEFAULT 0.0 COMMENT '投注总额'", nullable = false)
	private Double Total;
	
	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODEIFY_TIME", columnDefinition="TIMESTAMP NULL COMMENT '最后登录时间'")
	private Date lastModifyTime;
	
	public Record() {
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
