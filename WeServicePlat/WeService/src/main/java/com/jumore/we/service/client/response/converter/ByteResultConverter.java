/** 
 * Project Name:WeService 
 * File Name:ByteResultConverter.java 
 * Package Name:com.jumore.we.service.client.response.converter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:34:36
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: Byte返回结果转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:34:36
 * @version 
 * @see
 */
public class ByteResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Byte.class.equals(clazz);
    }

    @Override
    public Object convert(String data, Class<?> clazz) {
        return Byte.parseByte(data);
    }

}
