/** 
 * Project Name:WeServiceClient 
 * File Name:RequestParameterResolver.java 
 * Package Name:com.jumore.we.service.client
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月20日 下午2:51:29
 */
package com.jumore.we.service.client.request;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.http.NameValuePair;

/**
 * Function: 请求参数封装
 * 
 * @author 乔广
 * @date 2017年7月20日 下午2:51:29
 * @version 
 * @see
 */
public interface RequestParameterResolver {
    /**
     * 
     * resolveParameter:解析请求参数.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午2:56:05
     * @param args
     * @return
     */
    public List<NameValuePair> resolveParameter(Method method, Object[] args);
}
