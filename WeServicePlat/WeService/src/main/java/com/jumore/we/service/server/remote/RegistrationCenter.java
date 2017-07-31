/**
 * Project Name:WeService File Name:RegistrationCenter.java Package
 * Name:com.jumore.we.service.server.remote Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月30日 下午8:30:24
 */
package com.jumore.we.service.server.remote;

/**
 * 注册中心，服务注册的地方
 * 
 * @author 乔广
 * @date 2017年7月30日 下午8:30:24
 * @version
 * @see
 */
public interface RegistrationCenter {
    /**
     * 
     * support:是否支持.
     * 
     * @author 乔广
     * @date 2017年7月31日 上午10:11:47
     * @param model
     * @return
     */
    public boolean support(Object model);
    
    /**
     * 
     * remoteRegistry:远程注册.
     * 
     * @author 乔广
     * @date 2017年7月31日 上午10:12:02
     * @param model
     */
    public void remoteRegistry(Object model);
}
