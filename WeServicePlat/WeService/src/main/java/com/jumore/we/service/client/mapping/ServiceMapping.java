/**
 * Project Name:WeServiceClient File Name:ServiceMapping.java Package
 * Name:com.jumore.we.service.client Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午2:43:05
 */
package com.jumore.we.service.client.mapping;

import java.lang.reflect.Method;
import java.net.URI;

/**
 * Function: Service路由
 * 
 * @author 乔广
 * @date 2017年7月20日 下午2:43:05
 * @version
 * @see
 */
public interface ServiceMapping {
    public URI getServiceRequestURI(Method service);
}
