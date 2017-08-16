/**
 * Project Name:WeService File Name:DirectSelector.java Package
 * Name:com.jumore.we.service.client.mapping.selector Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月16日 下午2:30:08
 */
package com.jumore.we.service.client.mapping.selector;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function: 直连选择器
 * 
 * @author 乔广
 * @date 2017年8月16日 下午2:30:08
 * @version
 * @see
 */
public class DirectSelector implements ServiceSelector {
    private static final Logger logger = LoggerFactory.getLogger(DirectSelector.class);
    private String              serviceDoman;
    private Integer             serviceIP;

    /**
     * serviceDoman
     *
     * @return the serviceDoman
     */
    public String getServiceDoman() {
        return serviceDoman;
    }

    /**
     * @param serviceDoman the serviceDoman to set
     */
    public void setServiceDoman(String serviceDoman) {
        this.serviceDoman = serviceDoman;
    }

    /**
     * serviceIP
     *
     * @return the serviceIP
     */
    public Integer getServiceIP() {
        return serviceIP;
    }

    /**
     * @param serviceIP the serviceIP to set
     */
    public void setServiceIP(Integer serviceIP) {
        this.serviceIP = serviceIP;
    }

    @Override
    public String select(List<String> services) {
        if (StringUtils.isBlank(serviceDoman)) {
            IllegalArgumentException e = new IllegalArgumentException("Service Doman cannot be null or empty or blank");
            logger.error(e.getMessage(), e);
            throw e;
        }

        if (serviceIP == null) {
            serviceIP = 80;
        }

        String patten = serviceDoman + ":" + serviceIP;

        for (String service : services) {
            if (service.indexOf(patten) >= 0) {
                return service;
            }
        }

        return null;
    }

}
