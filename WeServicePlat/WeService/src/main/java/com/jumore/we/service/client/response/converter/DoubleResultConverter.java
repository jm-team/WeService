/** 
 * Project Name:WeService 
 * File Name:DoubleResultConverter.java 
 * Package Name:com.jumore.we.service.client.response.converter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:33:58
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Double返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:33:58
 * @version 
 * @see
 */
public class DoubleResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Double.class.equals(clazz) || double.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Double.parseDouble(data);
    }

}
