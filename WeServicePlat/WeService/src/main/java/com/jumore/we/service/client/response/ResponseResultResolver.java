/**
 * Project Name:WeServiceClient File Name:ResponseResultResolver.java Package
 * Name:com.jumore.we.service.client Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午3:05:38
 */
package com.jumore.we.service.client.response;

import org.apache.http.HttpResponse;

/**
 * Function: 解析响应，得到请求结果
 * 
 * @author 乔广
 * @date 2017年7月20日 下午3:05:38
 * @version
 * @see
 */
public interface ResponseResultResolver {
    /**
     * 
     * resolveResult:解析响应，得到请求结果.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午3:07:26
     * @param response
     * @return
     */
    public Object resolveResult(HttpResponse response, Class<?> resultClass);
}
