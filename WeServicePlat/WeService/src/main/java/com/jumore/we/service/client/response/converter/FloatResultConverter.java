/**
 * Project Name:WeService File Name:FloatResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:33:20
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Float返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:33:20
 * @version
 * @see
 */
public class FloatResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Float.class.equals(clazz) || float.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Float.parseFloat(data);
    }

}
