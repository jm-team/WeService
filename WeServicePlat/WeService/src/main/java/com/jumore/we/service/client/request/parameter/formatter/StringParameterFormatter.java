/**
 * Project Name:WeService File Name:StringParameterFormatter.java Package
 * Name:com.jumore.we.service.client.request.parameter.formatter Copyright (c)
 * 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午1:56:19
 */
package com.jumore.we.service.client.request.parameter.formatter;

/**
 * Function: 字符串转换器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午1:56:19
 * @version
 * @see
 */
public class StringParameterFormatter extends AbstractRequestParameterFormatter {

    @Override
    protected boolean supportForNotNull(Object param) {
        return param instanceof String;
    }

}
