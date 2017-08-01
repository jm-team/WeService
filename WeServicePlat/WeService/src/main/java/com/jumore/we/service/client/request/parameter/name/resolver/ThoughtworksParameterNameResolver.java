/**
 * Project Name:WeService File Name:ThoughtworksParameterNameResolver.java
 * Package Name:com.jumore.we.service.client.request.parameter.name.resolver
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午1:45:32
 */
package com.jumore.we.service.client.request.parameter.name.resolver;

import java.lang.reflect.Method;

import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;

/**
 * Function: 借助于Thoughtworks框架的名称解析器
 * 
 * @author 乔广
 * @date 2017年8月1日 下午1:45:32
 * @version
 * @see
 */
public class ThoughtworksParameterNameResolver implements RequestParameterNameResolver {
    private CachingParanamer cachingParanamer = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));

    /**
     * cachingParanamer
     *
     * @return the cachingParanamer
     */
    public CachingParanamer getCachingParanamer() {
        return cachingParanamer;
    }

    /**
     * @param cachingParanamer the cachingParanamer to set
     */
    public void setCachingParanamer(CachingParanamer cachingParanamer) {
        this.cachingParanamer = cachingParanamer;
    }

    @Override
    public String[] resolveParameterName(Method service) {
        String[] names = cachingParanamer.lookupParameterNames(service, false);
        return names;
    }

}
