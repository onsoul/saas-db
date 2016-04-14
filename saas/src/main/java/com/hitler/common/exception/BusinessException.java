package com.hitler.common.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 5654145024170831545L;
	
	private String code;
	
	public BusinessException(String code) {
		this.code = code;
	}
	
	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	@Override
    public final String toString() {
        String msg = getCode();
        if (getMessage() != null && getMessage().trim().length() > 0)
            msg = ":" + getMessage();
        return msg;
    }

}
