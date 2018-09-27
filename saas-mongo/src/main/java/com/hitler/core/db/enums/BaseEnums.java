package com.hitler.core.db.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wanhao
 * @Description
 * @Date: Created in 10:56 2018/8/3
 */
public interface BaseEnums {
    Integer getCode();

    String getMsg();

    /**
     * key code,value msg
     */
    static Map<Integer, String> toMap(BaseEnums[] enums) {
        return Arrays.stream(enums).collect(Collectors.toMap(BaseEnums::getCode, BaseEnums::getMsg));
    }
}
