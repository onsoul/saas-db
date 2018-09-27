/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.hitler.core.ex;

import com.hitler.core.resp.Code;

/**
 * @Author: wanhao
 * @Description
 * @Date: Created in 11:37 2018/8/24
 */
public class OrderHookBHBException extends BHBException {
    public OrderHookBHBException() {
        super(Code.MSG_2010);
    }
}
