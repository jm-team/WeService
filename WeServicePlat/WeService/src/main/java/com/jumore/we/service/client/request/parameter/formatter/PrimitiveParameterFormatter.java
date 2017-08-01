/**
 * Project Name:WeService File Name:primitive.java Package
 * Name:com.jumore.we.service.client.request.parameter.formatter Copyright (c)
 * 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午2:10:25
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Function: 基础类型转换器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午2:10:25
 * @version
 * @see
 */
public class PrimitiveParameterFormatter implements RequestParameterFormatter {

    @Override
    public boolean support(Object param) {
        if (param == null) {
            return false;
        }

        return param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double
                || param instanceof Short || param instanceof Byte || param instanceof Character || param instanceof Boolean;
    }

    @Override
    public List<NameValuePair> format(String name, Object param) {
        NameValuePair pair = new BasicNameValuePair(name, String.valueOf(param));
        return Collections.singletonList(pair);
    }

}
