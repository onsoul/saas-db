package com.hitler.core.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一的结果集返回形式
 *
 * @author onsoul 2018年8月2日 上午9:01:23
 */
@ApiModel(description = "统一的结果集返回形式")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 796464608076507729L;
	@ApiModelProperty("响应信息")
	private Object msg;
	@ApiModelProperty("数量-分页场景使用")
	private Long count;
	@ApiModelProperty("响应数据(一般包含entity或者DTO)")
	private List<T> data;
	
	private String sid;

	public Result() {
	}

	public Result(Object msg) { // 链接MsgCode  
		this.msg = msg;
	}

	public Result(Object msg, T t) { // 链接MsgCode
		this.data = new ArrayList<>(); // 如果是单体
		this.data.add(t);
		this.msg = msg;
	}

	public Result(Object msg, List<T> data) { // 链接MsgCode
		this.data = data;
		this.msg = msg;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(T t) {
		if (this.data == null || this.data.isEmpty()) {
			this.data = new ArrayList<>(); // 如果是单体
		}
		this.data.add(t);
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
