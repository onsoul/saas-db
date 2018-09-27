package com.hitler.core.ex;

import com.hitler.core.resp.Code;

/**
 * 文件名：cn.bhbapp.ush.common.exception
 * 修改人：liufumin
 * 修改时间：2018/9/13
 */
public class HttpException extends ConfigException {
    private static final long serialVersionUID = 1L;

    public HttpException(Code code) {
        super(code);
    }
}
