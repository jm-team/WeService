/** 
 * Project Name:WeService 
 * File Name:CompositeParameterFormatter.java 
 * Package Name:com.jumore.we.service.client.request.parameter.formatter
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月7日 下午5:01:14
 */
package com.jumore.we.service.client.request.parameter.formatter;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.NameValuePair;

/**
 * Function: TODO (这里用一句话描述这个类的作用)
 * 
 * @author 乔广
 * @date 2017年8月7日 下午5:01:14
 * @version 
 * @see
 */
public class CompositeParameterFormatter extends AbstractRequestParameterFormatter {
    private List<RequestParameterFormatter> formatters;
    
    /**
     * formatters
     *
     * @return the formatters
     */
    public List<RequestParameterFormatter> getFormatters() {
        return formatters;
    }
    
    /**
     * @param formatters the formatters to set
     */
    public void setFormatters(List<RequestParameterFormatter> formatters) {
        this.formatters = formatters;
    }


    @Override
    public List<NameValuePair> format(String name, Object param) {
        return null;
    }

    @Override
    protected boolean supportForNotNull(Object param) {
        if(CollectionUtils.isEmpty(formatters)){
            return false;
        }
        
        for (RequestParameterFormatter formatter : formatters) {
            if(formatter.support(param)){
                return true;
            }
        }
        
        return false;
    }

}
