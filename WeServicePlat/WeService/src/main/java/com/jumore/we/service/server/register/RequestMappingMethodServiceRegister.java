/**
 * Project Name:WeService File Name:RequestMappingMethodServiceRegister.java
 * Package Name:com.jumore.we.service.server.register Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月28日 下午7:29:25
 */
package com.jumore.we.service.server.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.server.service.RequestMappingMethodService;

/**
 * RequestMappingMethod为单位的服务的注册器
 * 
 * @author 乔广
 * @date 2017年7月28日 下午7:29:25
 * @version
 * @see
 */
public class RequestMappingMethodServiceRegister implements WeServiceRegister {
    private static final Logger logger = LoggerFactory.getLogger(RequestMappingMethodServiceRegister.class);

    @Override
    public boolean supportService(Object service) {
        return service instanceof RequestMappingMethodService;
    }

    /**
     * 注册服务
     */
    @Override
    public void registeService(Object service) {
        logger.debug("start registe service...");
    }

}
