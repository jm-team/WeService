/**
 * Project Name:WeService File Name:ShortResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:34:17
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Short返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:34:17
 * @version
 * @see
 */
public class ShortResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Short.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Short.parseShort(data);
    }

}
