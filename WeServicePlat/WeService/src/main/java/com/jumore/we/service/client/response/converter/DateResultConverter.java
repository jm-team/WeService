/**
 * Project Name:WeService File Name:DateResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:35:33
 */
package com.jumore.we.service.client.response.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.exception.IllegalResponseResultException;

/**
 * Function: Date返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:35:33
 * @version
 * @see
 */
public class DateResultConverter implements ResponseResultConverter {
    private static final Logger logger     = LoggerFactory.getLogger(DateResultConverter.class);
    private String              pattern    = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat    dateFormat = new SimpleDateFormat(pattern);

    /**
     * pattern
     *
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
        this.dateFormat.applyPattern(pattern);
    }

    @Override
    public boolean support(Class<?> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        try {
            return dateFormat.parse(data);
        } catch (ParseException e) {
            IllegalResponseResultException ex = new IllegalResponseResultException("Illegal Response Result");
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

}
