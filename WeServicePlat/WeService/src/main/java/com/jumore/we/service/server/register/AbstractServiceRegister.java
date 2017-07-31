/**
 * Project Name:WeService File Name:AbstractServiceRegister.java Package
 * Name:com.jumore.we.service.server.register Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 上午8:40:21
 */
package com.jumore.we.service.server.register;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.server.exception.NoRegistrationCenterException;
import com.jumore.we.service.server.remote.RegistrationCenter;

/**
 * 抽象服务注册。实现了使用注册中心模块的注册功能。
 * 
 * @author 乔广
 * @date 2017年7月31日 上午8:40:21
 * @version
 * @see
 */
public abstract class AbstractServiceRegister implements WeServiceRegister {
    private static final Logger      logger = LoggerFactory.getLogger(AbstractServiceRegister.class);
    private List<RegistrationCenter> rcenters;

    /**
     * rcenters
     *
     * @return the rcenters
     */
    public List<RegistrationCenter> getRcenters() {
        return rcenters;
    }

    /**
     * @param rcenters the rcenters to set
     */
    public void setRcenters(List<RegistrationCenter> rcenters) {
        this.rcenters = rcenters;
    }

    @Override
    public void registeService(Object service) {
        if (rcenters == null || rcenters.isEmpty()) {
            String eMsg = "Registration Center cannot be null or empty";
            NoRegistrationCenterException e = new NoRegistrationCenterException(eMsg);
            logger.error(eMsg, e);
            throw e;
        }

        for (RegistrationCenter registrationCenter : rcenters) {
            registrationCenter.remoteRegistry(service);
        }
    }

}
