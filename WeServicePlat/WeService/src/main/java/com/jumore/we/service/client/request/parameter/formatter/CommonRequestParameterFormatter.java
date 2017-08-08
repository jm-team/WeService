/**
 * Project Name:WeService File Name:CommonRequestParameterFormatter.java Package
 * Name:com.jumore.we.service.client.request.parameter.formatter Copyright (c)
 * 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月7日 下午4:34:51
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;

/**
 * Function: 基础类型，string，date，array和pojo的格式化
 * 
 * @author 乔广
 * @date 2017年8月7日 下午4:34:51
 * @version
 * @see
 */
public class CommonRequestParameterFormatter extends AbstractRequestParameterFormatter {
    private static final Logger logger     = LoggerFactory.getLogger(CommonRequestParameterFormatter.class);
    private String              pattern    = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat    dateFormat = new SimpleDateFormat(pattern);

    /**
     * pattern
     *
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
        this.dateFormat.applyPattern(pattern);
    }

    @Override
    protected boolean supportForNotNull(Object param) {
        return true;
    }

    @Override
    public List<NameValuePair> format(String name, Object param) {
        return doFormat(StringUtils.EMPTY, name, param);
    }

    /**
     * doFormat:格式化.
     * 
     * @author 乔广
     * @date 2017年8月8日 下午1:19:31
     * @param prefix
     * @param name
     * @param param
     * @return
     * @throws Exception
     */
    public List<NameValuePair> doFormat(String prefix, String name, Object param) {
        if (param == null) {
            return null;
        }

        if (isSimpleType(param.getClass())) {
            return doFormatSimpleType(prefix, name, param);
        }

        if (isArray(param.getClass())) {
            return doFormatArrayType(prefix, name, param);
        }

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(param.getClass());

        for (PropertyDescriptor getter : getters) {
            List<NameValuePair> list = null;
            Object value = null;

            try {
                value = getter.getReadMethod().invoke(param, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                logger.error("Ignore this error", e);
            }

            if (isSimpleType(getter.getPropertyType()) || getter.getPropertyType().isArray()) {
                list = doFormat(prefix, getter.getDisplayName(), value);
            } else {
                list = doFormat(getName(prefix, getter.getDisplayName()), StringUtils.EMPTY, value);
            }

            if (CollectionUtils.isNotEmpty(list)) {
                pairs.addAll(list);
            }
        }

        return pairs;
    }

    /**
     * doFormatSimpleType:格式化简单对象.
     * 
     * @author 乔广
     * @date 2017年8月8日 下午1:18:38
     * @param prefix
     * @param name
     * @param param
     * @return
     */
    private List<NameValuePair> doFormatSimpleType(String prefix, String name, Object param) {
        String value = null;

        if (param instanceof Date) {
            value = dateFormat.format((Date) param);
        } else {
            value = param.toString();
        }
        NameValuePair pair = new BasicNameValuePair(getName(prefix, name), value);
        return Collections.singletonList(pair);
    }

    /**
     * doFormatArrayType:格式化数组.
     * 
     * @author 乔广
     * @date 2017年8月8日 下午1:18:52
     * @param prefix
     * @param name
     * @param param
     * @return
     */
    private List<NameValuePair> doFormatArrayType(String prefix, String name, Object param) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        Object[] arr = (Object[]) param;

        for (Object obj : arr) {
            NameValuePair pair = new BasicNameValuePair(getName(prefix, name), obj.toString());
            pairs.add(pair);
        }

        return pairs;
    }

    /**
     * isSimpleType:判断是否为简单类型.
     * 
     * @author 乔广
     * @date 2017年8月8日 下午1:04:21
     * @param type
     * @return
     */
    private boolean isSimpleType(Class<?> type) {
        return Integer.class.equals(type) || Long.class.equals(type) || Float.class.equals(type) || Double.class.equals(type)
                || Short.class.equals(type) || Byte.class.equals(type) || Character.class.equals(type) || Boolean.class.equals(type)
                || String.class.equals(type) || Date.class.equals(type);
    }

    /**
     * isArray:判断是否 为数组.
     * 
     * @author 乔广
     * @date 2017年8月8日 下午1:04:40
     * @param type
     * @return
     */
    private boolean isArray(Class<?> type) {
        return type.isArray();
    }

    public String getName(String prefix, String name) {
        if (StringUtils.isEmpty(prefix)) {
            return name;
        }

        return prefix + "." + name;
    }
}
