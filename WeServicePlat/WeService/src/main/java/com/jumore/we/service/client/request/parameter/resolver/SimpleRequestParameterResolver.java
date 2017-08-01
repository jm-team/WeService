/**
 * Project Name:WeService File Name:SimpleRequestParameterResolver.java Package
 * Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午7:55:30
 */
package com.jumore.we.service.client.request.parameter.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.request.parameter.formatter.RequestParameterFormatter;
import com.jumore.we.service.client.request.parameter.name.resolver.RequestParameterNameResolver;

/**
 * Function: 请求参数解析器实现
 * 
 * @author 乔广
 * @date 2017年7月31日 下午7:55:30
 * @version
 * @see
 */
public class SimpleRequestParameterResolver extends AbstractRequestParameterResolver {
    private static final Logger             logger                     = LoggerFactory.getLogger(SimpleRequestParameterResolver.class);
    private RequestParameterNameResolver    requestParameterNameResolver;
    private List<RequestParameterFormatter> requestParameterFormatters = new ArrayList<RequestParameterFormatter>();

    @Override
    public RequestParameterNameResolver getRequestParameterNameResolver() {
        return requestParameterNameResolver;
    }

    @Override
    public List<RequestParameterFormatter> getRequestParameterFormatters() {
        return requestParameterFormatters;
    }

    /**
     * @param requestParameterNameResolver the requestParameterNameResolver to
     *            set
     */
    public void setRequestParameterNameResolver(RequestParameterNameResolver requestParameterNameResolver) {
        this.requestParameterNameResolver = requestParameterNameResolver;
    }

    public void addFormatter(RequestParameterFormatter requestParameterFormatter) {
        requestParameterFormatters.add(requestParameterFormatter);
    }

    public void addFormatters(Collection<RequestParameterFormatter> formatters) {
        requestParameterFormatters.addAll(formatters);
    }
}
