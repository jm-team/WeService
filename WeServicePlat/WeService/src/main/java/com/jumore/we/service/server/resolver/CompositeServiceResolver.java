/**
 * Project Name:WeService File Name:CompositeServiceResolver.java Package
 * Name:com.jumore.we.service.server.resolver Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月30日 下午8:46:57
 */
package com.jumore.we.service.server.resolver;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 服务解析器集合
 * 
 * @author 乔广
 * @date 2017年7月30日 下午8:46:57
 * @version
 * @see
 */
public class CompositeServiceResolver implements WeServiceResolver {
    private static final Logger     logger    = LoggerFactory.getLogger(CompositeServiceResolver.class);
    private List<WeServiceResolver> resolvers = new ArrayList<WeServiceResolver>();

    /**
     * resolvers
     *
     * @return the resolvers
     */
    public List<WeServiceResolver> getResolvers() {
        return resolvers;
    }

    /**
     * @param resolvers the resolvers to set
     */
    public void setResolvers(List<WeServiceResolver> resolvers) {
        this.resolvers = resolvers;
    }

    @Override
    public List<Object> resolveService(DispatcherServlet dispatcherServlet) {
        if (resolvers == null) {
            NullPointerException e = new NullPointerException("resolvers connot be null");
            logger.error("resolvers connot be null", e);
            throw e;
        }

        if (resolvers.isEmpty()) {
            IllegalArgumentException e = new IllegalArgumentException("resolvers connot be empty");
            logger.error("resolvers connot be empty", e);
            throw e;
        }

        List<Object> serviceList = new ArrayList<Object>();

        for (WeServiceResolver resolver : resolvers) {
            if (resolver == null) {
                NullPointerException e = new NullPointerException("service resolver connot be null");
                logger.error("service resolver connot be null", e);
                throw e;
            }

            List<Object> services = resolver.resolveService(dispatcherServlet);

            if (services != null && !services.isEmpty()) {
                serviceList.addAll(services);
            }
        }

        return serviceList;
    }

}
