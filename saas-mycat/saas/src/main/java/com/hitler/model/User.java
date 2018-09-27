package com.hitler.model;

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
import com.hitler.common.model.support.CheckableEntity;
import com.hitler.common.util.StringUtils;

/**
 * 用户
 * @author
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_USER", indexes = { 
	@Index(name = "IDX_PARENT_ID", columnList = "PARENT_ID")
})
public class User extends CheckableEntity<Integer> {

	private static final long serialVersionUID = 3589877365682748474L;
	
	/**
	 * 用户帐号
	 */
	@Checked
	@Column(name = "USER_NAME", columnDefinition="varchar(16) COMMENT '用户帐号'", unique = true, nullable = false)
	private String userName;

	/**
	 * 昵称
	 */
	@Column(name = "NICK_NAME", columnDefinition="varchar(12) COMMENT '昵称'", nullable = false)
	private String nickName = "";
	
	/**
	 * 登录密码
	 */
	@Checked
	@Column(name = "PWD_LOGIN", columnDefinition="varchar(32) COMMENT '登录密码'", nullable = false)
	private String loginPassword;

	/**
	 * 资金密码
	 */
	@Checked
	@Column(name = "PWD_FUNDS", columnDefinition="varchar(32) COMMENT '资金密码'")
	private String fundsPassword;

	/**
	 * 加盐
	 */
	@Checked
	@Column(name = "PWD_SALT", columnDefinition="varchar(32) COMMENT '加盐'", nullable = false)
	private String passwordSalt;
	
	/**
	 * 分层锁定状态
	 */
	@Checked
	@Column(name = "LAYER_LOCKED", columnDefinition="TINYINT(2) DEFAULT 0 COMMENT '分层锁定状态'")
	private Boolean layerLocked = Boolean.FALSE;
 
	/**
	 * 账户余额
	 */
	@Checked
	@Column(name = "BALANCE_ACCOUNT", columnDefinition="DECIMAL(10,2) DEFAULT 0.0 COMMENT '账户余额'")
	private Double accountBalance = 0D;
	 
	/**
	 * QQ号码
	 */
	@Column(name = "QQ", columnDefinition="varchar(15) COMMENT 'QQ号码'")
	private String qq = "";

	/**
	 * 邮箱
	 */
	@Column(name = "EMAIL", columnDefinition="varchar(40) COMMENT '邮箱'")
	private String email = "";

	/**
	 * 手机号码
	 */
	@Column(name = "MOBILE", columnDefinition="varchar(20) COMMENT '手机号码'")
	private String mobile = "";
	
 
	/**
	 * 登录冻结状态
	 */
	@Checked
	@Column(name = "LOGIN_LOCKED", columnDefinition="TINYINT(2) DEFAULT 0 COMMENT '登录冻结状态'")
	private Boolean loginLocked = Boolean.FALSE;
 
	/**
	 * 用户银行锁定状态
	 */
	@Checked
	@Column(name = "BANK_CARD_LOCKED", columnDefinition="TINYINT(2) DEFAULT 0 COMMENT '用户银行锁定状态'")
	private Boolean bankCardLocked = Boolean.FALSE;
 
	/**
	 * 最后提款时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_WITHDRAW_TIME", columnDefinition="TIMESTAMP NULL COMMENT '最后提款时间'")
	private Date lastWithdrawTime;
	
	/**
	 * 最后登录时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_TIME", columnDefinition="TIMESTAMP NULL COMMENT '最后登录时间'")
	private Date lastLoginTime;
	
	/**
	 * 最后登录IP地址
	 */
	@Column(name = "LAST_LOGIN_ADDR", columnDefinition="varchar(20) COMMENT '最后登录IP地址'")
	private String lastLoginAddress = "";
	
	/**
	 * 登录失败次数
	 */
	@Checked
	@Column(name = "LOGIN_FAILURE_TIMES", columnDefinition="INT DEFAULT 0 COMMENT '登录失败次数'")
	private Integer loginFailureTimes = 0;
	
	/**
	 * 登录锁定解冻时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_LOCKED_DUE_TIME", columnDefinition="TIMESTAMP NULL COMMENT '登录锁定解冻时间'")
	private Date loginLockedDueTime;
	
	/**
	 * 登录密码最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_PWD_LAST_MODIFIED_DATE", columnDefinition="TIMESTAMP NULL COMMENT '登录密码最后修改时间'")
	private Date loginPasswordLastModifiedDate;

	/**
	 * 资金密码最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FUNDS_PWD_LAST_MODIFIED_DATE", columnDefinition="TIMESTAMP NULL COMMENT '资金密码最后修改时间'")
	private Date fundsPasswordLastModifiedDate;

	/**
	 * 直属上级ID
	 */
	@Checked
	@Column(name = "PARENT_ID", columnDefinition="INT COMMENT '直属上级ID'")
	private Integer parentId;
	/**
	 * 直属上级帐号
	 */
	@Checked
	@Column(name = "PARENT_NAME", columnDefinition="varchar(16) COMMENT '直属上级帐号'")
	private String parentName;
	
	/**
	 * 邮箱激活码
	 */
	@Checked
	@Column(name = "EMAIL_CODE", columnDefinition="varchar(50) COMMENT '邮箱激活码'")
	private String emailCode;
	
	/**
	 * 是否激活
	 */
	@Checked
	@Column(name = "IS_EMAIL_ACTIVE", columnDefinition="TINYINT(2) DEFAULT 0 COMMENT '邮箱是否激活'")
	private Boolean isEmailActive= Boolean.FALSE;
	//====================== 未持久化字段 begin ======================
	
	public User() {

	}

	public User(Integer id, String userName) {
		this.setId(id);
		this.setUserName(userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	 
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getFundsPassword() {
		return fundsPassword;
	}

	public void setFundsPassword(String fundsPassword) {
		this.fundsPassword = fundsPassword;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	 

	public Boolean getLoginLocked() {
		return loginLocked;
	}

	public void setLoginLocked(Boolean loginLocked) {
		this.loginLocked = loginLocked;
	}

	public Boolean getBankCardLocked() {
		return bankCardLocked;
	}

	public void setBankCardLocked(Boolean bankCardLocked) {
		this.bankCardLocked = bankCardLocked;
	}

	public Boolean getLayerLocked() {
		return layerLocked;
	}

	public void setLayerLocked(Boolean layerLocked) {
		this.layerLocked = layerLocked;
	}

	public Date getLoginPasswordLastModifiedDate() {
		return loginPasswordLastModifiedDate;
	}

	public void setLoginPasswordLastModifiedDate(
			Date loginPasswordLastModifiedDate) {
		this.loginPasswordLastModifiedDate = loginPasswordLastModifiedDate;
	}

	public Date getFundsPasswordLastModifiedDate() {
		return fundsPasswordLastModifiedDate;
	}

	public void setFundsPasswordLastModifiedDate(
			Date fundsPasswordLastModifiedDate) {
		this.fundsPasswordLastModifiedDate = fundsPasswordLastModifiedDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastWithdrawTime() {
		return lastWithdrawTime;
	}

	public void setLastWithdrawTime(Date lastWithdrawTime) {
		this.lastWithdrawTime = lastWithdrawTime;
	}

	public String getLastLoginAddress() {
		return lastLoginAddress;
	}

	public void setLastLoginAddress(String lastLoginAddress) {
		this.lastLoginAddress = lastLoginAddress;
	}

	public Integer getLoginFailureTimes() {
		return loginFailureTimes;
	}

	public void setLoginFailureTimes(Integer loginFailureTimes) {
		this.loginFailureTimes = loginFailureTimes;
	}

	public Date getLoginLockedDueTime() {
		return loginLockedDueTime;
	}

	public void setLoginLockedDueTime(Date loginLockedDueTime) {
		this.loginLockedDueTime = loginLockedDueTime;
	}

	 

	 

	 

	public Boolean hasParent() {
		return StringUtils.isNotBlank(getParentName());
	}
	
	public Boolean validateInformation(String regex) {
		if (userName != null && userName.matches(regex)) {
			return Boolean.FALSE;
		}
		if (nickName != null && nickName.matches(regex)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public Boolean getIsEmailActive() {
		return isEmailActive;
	}

	public void setIsEmailActive(Boolean isEmailActive) {
		this.isEmailActive = isEmailActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	 
}
