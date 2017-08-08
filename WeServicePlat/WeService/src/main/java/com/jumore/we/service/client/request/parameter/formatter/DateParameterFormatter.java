/**
 * Project Name:WeService File Name:DateParameterFormatter.java Package
 * Name:com.jumore.we.service.client.request.parameter.formatter Copyright (c)
 * 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月7日 下午3:58:07
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Function: 时间类型格式化
 * 
 * @author 乔广
 * @date 2017年8月7日 下午3:58:07
 * @version
 * @see
 */
public class DateParameterFormatter extends AbstractRequestParameterFormatter {
    private String           pattern = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat dateFormat;

    /**
     * format
     *
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param format the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public DateParameterFormatter() {
        if (StringUtils.isNotBlank(pattern)) {
            dateFormat = new SimpleDateFormat(pattern);
        }
    }

    @Override
    protected boolean supportForNotNull(Object param) {
        return param instanceof Date;
    }

    @Override
    public List<NameValuePair> format(String name, Object param) {
        String value = null;

        if (dateFormat == null) {
            value = param.toString();
        }

        value = dateFormat.format((Date) param);
        NameValuePair pair = new BasicNameValuePair(name, value);

        return Collections.singletonList(pair);
    }
}
