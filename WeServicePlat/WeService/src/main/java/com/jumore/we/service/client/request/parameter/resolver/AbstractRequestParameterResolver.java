/**
 * Project Name:WeService File Name:AbstractRequestParameterResolver.java
 * Package Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 上午10:56:31
 */
package com.jumore.we.service.client.request.parameter.resolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.exception.NoSupportParameterFormatterException;
import com.jumore.we.service.client.request.parameter.formatter.RequestParameterFormatter;
import com.jumore.we.service.client.request.parameter.name.resolver.RequestParameterNameResolver;

/**
 * Function:抽象请求参数解析器
 * 
 * @author 乔广
 * @date 2017年8月1日 上午10:56:31
 * @version
 * @see
 */
public abstract class AbstractRequestParameterResolver implements RequestParameterResolver {
    private static final Logger logger = LoggerFactory.getLogger(AbstractRequestParameterResolver.class);

    @Override
    public List<NameValuePair> resolveParameter(Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        // 解析参数名集合
        RequestParameterNameResolver parameterNameResolver = getRequestParameterNameResolver();
        String[] parameterNames = parameterNameResolver.resolveParameterName(method);

        if (parameterNames.length != args.length) {
            IllegalArgumentException e = new IllegalArgumentException("Parameter's count does't equals with args's count");
            logger.error(e.getMessage(), e);
            throw e;
        }

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();

        // 遍历每个参数去解析键值对
        for (int i = 0; i < args.length; i++) {
            String parameterName = parameterNames[i];
            Object parameterValue = args[i];

            List<NameValuePair> list = null;

            // 使用支持类型的格式化器，解析参数对应的键值对集合
            for (RequestParameterFormatter parameterFormatter : getRequestParameterFormatters()) {
                if (parameterFormatter.support(parameterValue)) {
                    list = parameterFormatter.format(parameterName, parameterValue);
                    
                    if(list != null){
                        break;
                    }
                }
            }

            // 没有格式化器，抛异常
            if (list == null) {
                NoSupportParameterFormatterException e = new NoSupportParameterFormatterException(
                        "no support RequestParameterFormatter for arg[" + parameterValue.getClass() + "]");
                logger.error(e.getMessage(), e);
                throw e;
            }

            parameters.addAll(list);
        }

        return parameters;
    }

    public abstract RequestParameterNameResolver getRequestParameterNameResolver();

    public abstract List<RequestParameterFormatter> getRequestParameterFormatters();

}
