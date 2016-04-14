package com.hitler.common.model.support;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.hitler.common.model.annotation.Checked;
import com.hitler.common.model.support.PersistableEntity;
import com.hitler.model.User;

@MappedSuperclass
public abstract class UserTreeRelationEntity<PK extends Serializable>  extends PersistableEntity<PK> {

	private static final long serialVersionUID = 7405522757503732970L;

	/**
	 * 用户ID
	 */
	@Checked
	@Column(name = "USER_ID", columnDefinition="INT COMMENT '用户ID'", nullable = false)
	private Integer userId;
	
	/**
	 * 用户帐号
	 */
	@Checked
	@Column(name = "USER_NAME", columnDefinition="varchar(16) COMMENT '用户帐号'", nullable = false)
	private String userName;
	
	/**
	 * 上级ID
	 */
	@Checked
	@Column(name = "PARENT_ID", columnDefinition="INT COMMENT '上级ID'")
	private Integer parentId;
	
	/**
	 * 上级帐号
	 */
	@Checked
	@Column(name = "PARENT_NAME", columnDefinition="varchar(16) COMMENT '直属上级帐号'")
	private String parentName;

	/** 所在层级. */
	@Checked
	@Column(name = "FLOOR", columnDefinition="TINYINT COMMENT '所在层级'")
    private Short floor;
	
	public UserTreeRelationEntity() {}
	
	public UserTreeRelationEntity(User user) {
		this.userId = user.getId();
		this.userName = user.getUserName();
		this.parentId = user.getParentId();
		this.parentName = user.getParentName();
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public Short getFloor() {
		return floor;
	}

	public void setFloor(Short floor) {
		this.floor = floor;
	}

	public User getUser() {
		User user = new User();
		user.setId(this.getUserId());
		user.setUserName(this.getUserName());
		user.setParentId(this.getParentId());
		user.setParentName(this.getParentName());
		return user;
	}
	
	/*********************************
	 * 枚举
	 *2015-05-08
	 ***********************************/
	public static enum UserTreeRelationEntity_ {
		userId("userId"),
		userName("userName"),
		parentId("parentId"),
		parentName("parentName"),
		floor("floor");

		private final String _name;
		UserTreeRelationEntity_(String _name) {
			this._name = _name;
		}
		public String getName() {
			return _name;
		}
	}
}
