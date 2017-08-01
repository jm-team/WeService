/**
 * Project Name:WeService File Name:RequestParameterSetter.java Package
 * Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午7:40:35
 */
package com.jumore.we.service.client.request.parameter.setter;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Function: 请求参数设置器
 * 
 * @author 乔广
 * @date 2017年7月31日 下午7:40:35
 * @version
 * @see
 */
public interface RequestParameterSetter {
    /**
     * setParameter:给请求设置参数.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午7:42:18
     * @param request
     * @param parameter
     */
    public void setParameter(HttpRequestBase request, List<NameValuePair> parameter);
}
