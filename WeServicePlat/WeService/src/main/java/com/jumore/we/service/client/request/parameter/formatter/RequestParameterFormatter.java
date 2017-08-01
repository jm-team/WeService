/**
 * Project Name:WeService File Name:RequestParameterFormatter.java Package
 * Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 上午10:51:52
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.util.List;

import org.apache.http.NameValuePair;

/**
 * Function: 将请求参数转换为请求字符串
 * 
 * @author 乔广
 * @date 2017年8月1日 上午10:51:52
 * @version
 * @see
 */
public interface RequestParameterFormatter {
    /**
     * support:是否支持.
     * 
     * @author 乔广
     * @date 2017年8月1日 上午10:53:31
     * @param param
     * @return
     */
    public boolean support(Object param);

    
    /**
     * format:格式化.
     * 
     * @author 乔广
     * @date 2017年8月1日 下午12:34:53
     * @param name
     * @param param
     * @return
     */
    public List<NameValuePair> format(String name, Object param);
}
