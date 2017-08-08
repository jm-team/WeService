/** 
 * Project Name:WeService 
 * File Name:BooleanResultConverter.java 
 * Package Name:com.jumore.we.service.client.response.converter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:35:11
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Boolean返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:35:11
 * @version 
 * @see
 */
public class BooleanResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Boolean.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Boolean.parseBoolean(data);
    }

}
