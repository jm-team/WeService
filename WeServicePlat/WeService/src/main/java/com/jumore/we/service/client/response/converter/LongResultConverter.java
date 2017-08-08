/**
 * Project Name:WeService File Name:LongResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:33:00
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Long返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:33:00
 * @version
 * @see
 */
public class LongResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Long.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Long.parseLong(data);
    }

}
