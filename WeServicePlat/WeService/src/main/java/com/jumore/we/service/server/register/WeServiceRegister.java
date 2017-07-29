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
 * 用一个对象表示Service，支持多注册器。
 * 
 * @author 乔广
 * @date 2017年7月20日 下午2:37:33
 * @version
 * @see
 */
public interface WeServiceRegister {
    /**
     * 
     * supportService:是否能处理此Service.
     * 
     * @author 乔广
     * @date 2017年7月29日 上午7:11:42
     * @param service
     */
    public boolean supportService(Object service);
    
    /**
     * 
     * registeService:根据Service接口，注册服务.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午2:40:45
     * @param service
     */
    public void registeService(Object service);
}
