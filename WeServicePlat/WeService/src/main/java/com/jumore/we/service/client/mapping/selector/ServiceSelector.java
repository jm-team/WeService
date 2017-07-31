/**
 * Project Name:WeService File Name:ServiceSelector.java Package
 * Name:com.jumore.we.service.client.mapping Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午5:21:17
 */
package com.jumore.we.service.client.mapping.selector;

import java.util.List;

/**
 * 负载选择器
 * 
 * @author 乔广
 * @date 2017年7月31日 下午5:21:17
 * @version
 * @see
 */
public interface ServiceSelector {
    /**
     * select:选择.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午5:22:09
     * @param services
     * @return
     */
    public String select(List<String> services);
}
