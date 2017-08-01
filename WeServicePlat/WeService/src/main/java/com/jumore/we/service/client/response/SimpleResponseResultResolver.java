/**
 * Project Name:WeService File Name:SimpleResponseResultResolver.java Package
 * Name:com.jumore.we.service.client.response Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午4:35:02
 */
package com.jumore.we.service.client.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.response.converter.ResponseResultConverter;

/**
 * Function: TODO (这里用一句话描述这个类的作用)
 * 
 * @author 乔广
 * @date 2017年8月1日 下午4:35:02
 * @version
 * @see
 */
public class SimpleResponseResultResolver extends AbstractResponseResultResolver {
    private static final Logger           logger     = LoggerFactory.getLogger(SimpleResponseResultResolver.class);
    private List<ResponseResultConverter> converters = new ArrayList<ResponseResultConverter>();

    @Override
    public List<ResponseResultConverter> getResponseResultConverters() {
        // TODO Auto-generated method stub
        return converters;
    }

    public void addConverter(ResponseResultConverter converter) {
        this.converters.add(converter);
    }

    public void addConverters(Collection<ResponseResultConverter> converters) {
        this.converters.addAll(converters);
    }
}
