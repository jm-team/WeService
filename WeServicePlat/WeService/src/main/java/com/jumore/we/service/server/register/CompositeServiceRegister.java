/**
 * Project Name:WeService File Name:CompositeServiceRegister.java Package
 * Name:com.jumore.we.service.server.register Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月29日 上午7:17:49
 */
package com.jumore.we.service.server.register;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 混合注册器
 * 
 * @author 乔广
 * @date 2017年7月29日 上午7:17:49
 * @version
 * @see
 */
public class CompositeServiceRegister implements WeServiceRegister {
    private static final Logger     logger    = LoggerFactory.getLogger(CompositeServiceRegister.class);
    private List<WeServiceRegister> registers = new ArrayList<WeServiceRegister>();

    /**
     * registers
     *
     * @return the registers
     */
    public List<WeServiceRegister> getRegisters() {
        return registers;
    }

    /**
     * @param registers the registers to set
     */
    public void setRegisters(List<WeServiceRegister> registers) {
        this.registers = registers;
    }

    @Override
    public boolean supportService(Object service) {
        for (WeServiceRegister register : registers) {
            if (register.supportService(service)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void registeService(Object service) {
        for (WeServiceRegister register : registers) {
            if (register == null) {
                IllegalArgumentException e = new IllegalArgumentException("service register connot be null");
                logger.error("service register connot be null", e);
                throw e;
            }

            if (register.supportService(service)) {
                register.registeService(service);
                return;
            }
        }

        // 暂时可以不抛异常
        logger.error("no register support for service type : " + service.getClass().getName());
    }

}
