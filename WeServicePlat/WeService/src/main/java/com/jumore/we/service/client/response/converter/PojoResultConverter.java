/** 
 * Project Name:WeService 
 * File Name:PojoResultConverter.java 
 * Package Name:com.jumore.we.service.client.response.converter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月8日 下午1:58:27
 */
package com.jumore.we.service.client.response.converter;

import com.alibaba.fastjson.JSONObject;

/**
 * Function: 基础类型，string，date，array和pojo的转换
 * 
 * @author 乔广
 * @date 2017年8月8日 下午1:58:27
 * @version 
 * @see
 */
public class PojoResultConverter implements ResponseResultConverter {

    @Override
    public boolean support(Class<?> clazz) {
        return Object.class.isAssignableFrom(clazz);
    }

    /** 
     * 简单的基于fastjson的转换.
     */
    @Override
    public Object convert(String data, Class<?> clazz) {
        return JSONObject.parseObject(data, clazz);
    }

}
