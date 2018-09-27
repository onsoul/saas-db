package com.hitler.core.ex;

import com.hitler.core.resp.Code;

/**
 * 文件名：cn.bhbapp.push.common.exception
 * 修改人：liufumin
 * 修改时间：2018/9/13
 */
public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Code code;

    public ConfigException(Code code) {
        this.code = code;
    }

    public ConfigException() {
        this.code = Code.MSG_1;
    }

    public Code getCode() {
        return this.code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
