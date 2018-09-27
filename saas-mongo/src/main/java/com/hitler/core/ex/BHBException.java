package com.hitler.core.ex;

import com.hitler.core.resp.Code;


/**
 * 业务级异常
 * 异常信息统一归集至 msg_code.json, 会在应用初始化时加载至hashMap
 * @author onsoul
 * 2018年8月2日 上午9:39:04
 */
public class BHBException extends Exception {

	private static final long serialVersionUID = 3514055481085104305L;
	private Code code = Code.MSG_1; // 默认系统异常
	
	//不需要msg,因为msg工整到msg_code.json文件中,抛出异常时只需要由代码来跟踪

	public BHBException(Code code) {
		this.code = code;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

}
