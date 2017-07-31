/**
 * Project Name:WeServiceClient File Name:HttpUriRequestResolver.java Package
 * Name:com.jumore.we.service.client Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午3:03:23
 */
package com.jumore.we.service.client.request;

import org.apache.http.client.methods.HttpRequestBase;

import java.lang.reflect.Method;
import java.net.URI;

/**
 * Function: 解析请求方法
 * 
 * @author 乔广
 * @date 2017年7月20日 下午3:03:23
 * @version
 * @see
 */
public interface HttpRequestResolver {
    /**
     * 
     * resolve:解析请求方法.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午3:04:02
     * @return
     */
    public HttpRequestBase resolveRequest(URI uri, Method service);
}
