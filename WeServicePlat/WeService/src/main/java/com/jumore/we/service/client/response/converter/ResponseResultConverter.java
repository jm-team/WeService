/**
 * Project Name:WeService File Name:ResponseResultConverter.java Package
 * Name:com.jumore.we.service.client.response.converter Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午3:16:46
 */
package com.jumore.we.service.client.response.converter;

/**
 * Function: 结果转换器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午3:16:46
 * @version
 * @see
 */
public interface ResponseResultConverter {
    /**
     * support:是否支持返回类型.
     * 
     * @author 乔广
     * @date 2017年8月1日 下午3:21:53
     * @param clazz
     * @return
     */
    public boolean support(Class<?> clazz);

    /**
     * convert:将字符串转换为响应类型的对象.
     * 
     * @author 乔广
     * @date 2017年8月1日 下午3:22:06
     * @param data
     * @param clazz
     * @return
     */
    public Object convert(String data, Class<?> clazz);
}
