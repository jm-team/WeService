/** 
 * Project Name:WeService 
 * File Name:ArrayParameterFormatter.java 
 * Package Name:com.jumore.we.service.client.request.parameter.formatter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月7日 下午4:37:49
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Function: Array字符串转换器
 * 
 * @author 乔广
 * @date 2017年8月7日 下午4:37:49
 * @version 
 * @see
 */
public class ArrayParameterFormatter extends AbstractRequestParameterFormatter {
    @Override
    protected boolean supportForNotNull(Object param) {
        return param instanceof Array;
    }

    @Override
    public List<NameValuePair> format(String name, Object param) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        Object[] arr = (Object[]) param;
        
        for (Object obj : arr) {
            NameValuePair pair = new BasicNameValuePair(name, obj.toString());
            pairs.add(pair);
        }
        
        return pairs;
    }
}
