/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.hitler.core.db.mongo;


import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

/**
 * 1.空字符串返回null
 * 2.进行yyyy-MM-dd HH:mm:ss转换，失败抛出异常
 * description: 字符串转换为日期转换器
 * Author	Date	Changes
 * DouPai-LiuYang 2018/8/23 Created
 * @author DouPai-LiuYang
 */
@ReadingConverter
public class StringToDateConverter implements Converter<String, Date> {
    private final Logger log = LoggerFactory.getLogger(StringToDateConverter.class);

    @Override
    public Date convert(String source) {
        if (StrUtil.isBlank(source)) {
            return null;
        }
        log.info("source：{}", source);
        Optional<Date> optional = convertPattern(source);
        return optional.orElseThrow(() -> new ConvertException(source));
    }

    /**
     * 转换为date
     * @param source yyyy-MM-dd HH:mm:ss格式的字符串
     */
    private Optional<Date> convertPattern(String source) {
        try {
            Date date = FastDateFormat.getInstance(DatePattern.NORM_DATETIME_PATTERN).parse(source);
            return Optional.of(date);
        } catch (ParseException e) {
            log.error(String.format("source:%s ,message:%s", source, e.getMessage()), e);
        }
        return Optional.empty();
    }
}
