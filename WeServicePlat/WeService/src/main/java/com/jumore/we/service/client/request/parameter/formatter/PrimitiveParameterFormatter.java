/**
 * Project Name:WeService File Name:primitive.java Package
 * Name:com.jumore.we.service.client.request.parameter.formatter Copyright (c)
 * 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午2:10:25
 */
package com.jumore.we.service.client.request.parameter.formatter;

/**
 * Function: 基础类型转换器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午2:10:25
 * @version
 * @see
 */
public class PrimitiveParameterFormatter extends AbstractRequestParameterFormatter {
    @Override
    protected boolean supportForNotNull(Object param) {
        return param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double
                || param instanceof Short || param instanceof Byte || param instanceof Character || param instanceof Boolean;
    }
}
