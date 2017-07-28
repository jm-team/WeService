/**
 * Project Name:WeServiceRegister File Name:WeServiceRegister.java Package
 * Name:com.jumore.we.service.register Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午2:37:33
 */
package com.jumore.we.service.server.register;

import java.util.List;

/**
 * Function: 服务注册接口
 * T表示服务。服务有多种多样的表现形式，顾使用T表示。
 * 
 * @author 乔广
 * @date 2017年7月20日 下午2:37:33
 * @version
 * @see
 */
public interface WeServiceRegister<T> {
    /**
     * 
     * registeService:根据Service接口，注册服务.
     * 
     * @author 乔广
     * @date 2017年7月20日 下午2:40:45
     * @param serviceClazz
     */
    public void registeService(List<T> services);
}
