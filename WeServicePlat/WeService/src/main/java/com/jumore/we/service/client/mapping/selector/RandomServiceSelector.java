/** 
 * Project Name:WeService 
 * File Name:RandomServiceSelector.java 
 * Package Name:com.jumore.we.service.client.mapping.selector
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月31日 下午5:23:42
 */
package com.jumore.we.service.client.mapping.selector;

import java.util.List;
import java.util.Random;

/**
 * 随机选择器
 * 
 * @author 乔广
 * @date 2017年7月31日 下午5:23:42
 * @version 
 * @see
 */
public class RandomServiceSelector implements ServiceSelector {

    @Override
    public String select(List<String> services) {
        Random random = new Random();
        int index = random.nextInt(services.size());
        return services.get(index);
    }

}
