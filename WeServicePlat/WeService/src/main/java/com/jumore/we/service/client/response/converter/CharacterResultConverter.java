/**
 * Project Name:WeService File Name:CharacterResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:34:54
 */
package com.jumore.we.service.client.response.converter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.exception.IllegalResponseResultException;

/**
 * Function: Character返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:34:54
 * @version
 * @see
 */
public class CharacterResultConverter implements ResponseResultConverter {
    private static final Logger logger = LoggerFactory.getLogger(CharacterResultConverter.class);

    @Override
    public boolean support(Class<?> clazz) {
        return Character.class.equals(clazz) || char.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        if (StringUtils.isNotEmpty(data) && data.length() > 1) {
            IllegalResponseResultException e = new IllegalResponseResultException("Illegal Response Result");
            logger.error(e.getMessage(), e);
            throw e;
        }

        return data.charAt(0);
    }

}
