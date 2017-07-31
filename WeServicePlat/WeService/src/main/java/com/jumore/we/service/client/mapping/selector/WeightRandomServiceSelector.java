/**
 * Project Name:WeService File Name:WeightRandomServiceSelector.java Package
 * Name:com.jumore.we.service.client.mapping.selector Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午5:24:48
 */
package com.jumore.we.service.client.mapping.selector;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加权随机选择器
 * 
 * @author 乔广
 * @date 2017年7月31日 下午5:24:48
 * @version
 * @see
 */
public class WeightRandomServiceSelector implements ServiceSelector {
    private static final Logger logger         = LoggerFactory.getLogger(WeightRandomServiceSelector.class);
    private static final int    DEFAULT_WEIGHT = 1;

    /**
     * 权重的键
     */
    private String              weightKey      = "weight";

    /**
     * weightKey
     *
     * @return the weightKey
     */
    public String getWeightKey() {
        return weightKey;
    }

    /**
     * @param weightKey the weightKey to set
     */
    public void setWeightKey(String weightKey) {
        this.weightKey = weightKey;
    }

    @Override
    public String select(List<String> services) {
        List<String> serviceList = new ArrayList<String>();

        for (String service : services) {
            int weight = getWeight(service);

            for (int i = 0; i < weight; i++) {
                serviceList.add(service);
            }
        }

        Random random = new Random();
        int index = random.nextInt(serviceList.size());

        return serviceList.get(index);
    }

    /**
     * getWeight:获取权重.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午5:52:08
     * @param service
     * @return
     */
    private int getWeight(String service) {
        String decodedService = null;

        try {
            decodedService = URLDecoder.decode(service, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Unsupported Encoding", e);
            throw new RuntimeException(e);
        }

        if (decodedService.indexOf('?') < 0) {
            return DEFAULT_WEIGHT;
        }

        String params = decodedService.substring(decodedService.indexOf('?') + 1);

        if (StringUtils.isBlank(params)) {
            return DEFAULT_WEIGHT;
        }

        for (String param : params.split("&")) {
            String[] kv = param.split("=");

            if (weightKey.equals(kv[0])) {
                try {
                    return Integer.parseInt(kv[1]);
                } catch (NumberFormatException e) {
                    logger.error("Ignore this error", e);
                }
            }
        }

        return DEFAULT_WEIGHT;
    }

}
