package com.hitler.common.model.security;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 * 
 * @author shujianhui
 * 
 */
public class ShiroUser implements Serializable {
	private static final long serialVersionUID = 1L;
	public Integer id;
	public String username;
	public String password;
	public String salt;

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public String getSalt() {
	    return salt;
	}

	public void setSalt(String salt) {
	    this.salt = salt;
	}

	public ShiroUser(Integer id, String username,String password,String salt) {
		this.id = id;
		this.username = username;
		this.password=password;
		this.salt=salt;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "username");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "username");
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}
	
}
