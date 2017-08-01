/**
 * Project Name:WeService File Name:RequestParameterNameResolver.java Package
 * Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 上午10:23:17
 */
package com.jumore.we.service.client.request.parameter.name.resolver;

import java.lang.reflect.Method;

/**
 * Function: 参数名称解析器
 * 
 * @author 乔广
 * @date 2017年8月1日 上午10:23:17
 * @version
 * @see
 */
public interface RequestParameterNameResolver {
    /**
     * resolveParameterName:解析参数名称.
     * 
     * @author 乔广
     * @date 2017年8月1日 上午10:24:54
     * @param service
     * @return
     */
    public String[] resolveParameterName(Method service);
}
