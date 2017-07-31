/** 
 * Project Name:WeServiceClient 
 * File Name:RequestParameterResolver.java 
 * Package Name:com.jumore.we.service.client
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月20日 下午2:51:29
 */
package com.jumore.we.service.client;

import org.apache.http.HttpEntity;

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
     * resolve:请求参数解析成HttpClient请求的HttpEntity.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午2:56:05
     * @param args
     * @return
     */
    public HttpEntity resolveParameter(Object[] args);
}
