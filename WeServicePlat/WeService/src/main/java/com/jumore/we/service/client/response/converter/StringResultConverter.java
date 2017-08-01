/**
 * Project Name:WeService File Name:StringResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午4:39:37
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: String返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月1日 下午4:39:37
 * @version
 * @see
 */
public class StringResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return clazz.equals(String.class);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return data;
    }

}
