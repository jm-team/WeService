/**
 * Project Name:WeService File Name:IntegerResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:31:59
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Integer返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:31:59
 * @version
 * @see
 */
public class IntegerResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Integer.class.equals(clazz) || int.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Integer.parseInt(data);
    }

}
