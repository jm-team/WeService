/** 
 * Project Name:WeService 
 * File Name:StringParameterFormatter.java 
 * Package Name:com.jumore.we.service.client.request.parameter.formatter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月1日 下午1:56:19
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Function: 字符串转换器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午1:56:19
 * @version 
 * @see
 */
public class StringParameterFormatter implements RequestParameterFormatter {
    @Override
    public boolean support(Object param) {
        if(param == null){
            return false;
        }
        
        return param instanceof String;
    }

    @Override
    public List<NameValuePair> format(String name, Object param) {
        NameValuePair pair = new BasicNameValuePair(name, param.toString());
        return Collections.singletonList(pair);
    }

}
