/**
 * Project Name:WeServiceRegister File Name:WeServiceRegister.java Package
 * Name:com.jumore.we.service.register Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午2:37:33
 */
package com.jumore.we.service.server.register;

/**
 * Function: 服务注册接口
 * 
 * @author 乔广
 * @date 2017年7月20日 下午2:37:33
 * @version
 * @see
 */
public interface WeServiceRegister {
    /**
     * 
     * registeService:根据Service接口，注册服务.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午2:40:45
     * @param serviceClazz
     */
    public void registeService(Class<?> serviceClazz);
}
