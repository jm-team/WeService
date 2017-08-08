/**
 * Project Name:WeService File Name:AbstractRequestParameterFormatter.java
 * Package Name:com.jumore.we.service.client.request.parameter.formatter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月7日 下午4:16:23
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Function: RequestParameterFormatter的抽象实现
 * 
 * @author 乔广
 * @date 2017年8月7日 下午4:16:23
 * @version
 * @see
 */
public abstract class AbstractRequestParameterFormatter implements RequestParameterFormatter {

    @Override
    public boolean support(Object param) {
        if (param == null) {
            return false;
        }

        return supportForNotNull(param);
    }

    /**
     * supportForNotNull:只验证非null值.
     * 
     * @author 乔广
     * @date 2017年8月7日 下午4:17:33
     * @param param
     * @return
     */
    protected abstract boolean supportForNotNull(Object param);

    @Override
    public List<NameValuePair> format(String name, Object param) {
        NameValuePair pair = new BasicNameValuePair(name, param.toString());
        return Collections.singletonList(pair);
    }

}
