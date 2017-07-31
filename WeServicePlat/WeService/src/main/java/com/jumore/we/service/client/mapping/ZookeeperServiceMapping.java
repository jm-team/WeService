/**
 * Project Name:WeService File Name:ZookeeperServiceMapping.java Package
 * Name:com.jumore.we.service.client.mapping Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午4:39:03
 */
package com.jumore.we.service.client.mapping;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.jumore.we.service.client.exception.NoProviderException;
import com.jumore.we.service.client.mapping.selector.ServiceSelector;

/**
 * Zookeeper服务路由
 * 
 * @author 乔广
 * @date 2017年7月31日 下午4:39:03
 * @version
 * @see
 */
public class ZookeeperServiceMapping implements ServiceMapping, InitializingBean {
    private static final Logger logger                = LoggerFactory.getLogger(ZookeeperServiceMapping.class);
    public static final String  SERVICE_ZK_ROOT_PATH  = "/jumore.we.service";
    public static final String  SERVICE_PROVIDER_PATH = "/provider";

    /**
     * zookeeper集群地址
     */
    private String              serverstring;

    private ZkClient            zkClient;

    /**
     * 服务选择器
     */
    private ServiceSelector     serviceSelector;

    /**
     * serverstring
     *
     * @return the serverstring
     */
    public String getServerstring() {
        return serverstring;
    }

    /**
     * @param serverstring the serverstring to set
     */
    public void setServerstring(String serverstring) {
        this.serverstring = serverstring;
    }

    /**
     * zkClient
     *
     * @return the zkClient
     */
    public ZkClient getZkClient() {
        return zkClient;
    }

    /**
     * @param zkClient the zkClient to set
     */
    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * serviceSelector
     *
     * @return the serviceSelector
     */
    public ServiceSelector getServiceSelector() {
        return serviceSelector;
    }

    /**
     * @param serviceSelector the serviceSelector to set
     */
    public void setServiceSelector(ServiceSelector serviceSelector) {
        this.serviceSelector = serviceSelector;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(serverstring)) {
            String eMsg = "zookeeper serverstring cannot be null or empty or blank";
            IllegalArgumentException e = new IllegalArgumentException(eMsg);
            logger.error(eMsg, e);
            throw e;
        }

        if (zkClient == null) {
            zkClient = new ZkClient(serverstring);
        }
    }

    public URI getServiceRequestURI(Method service) {
        List<String> providers = getServiceProviders(service);
        String provider = getServiceSelector().select(providers);

        try {
            return new URI(URLDecoder.decode(provider, "utf-8"));
        } catch (URISyntaxException e) {
            logger.error("URI Syntax failed", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            logger.error("Unsupported Encoding", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * getServiceProviders:获取服务的提供者路径.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午4:58:25
     * @param service
     * @return
     */
    private List<String> getServiceProviders(Method service) {
        String serviceProviderPath = SERVICE_ZK_ROOT_PATH + "/" + getServiceName(service) + SERVICE_PROVIDER_PATH;
        List<String> providers = zkClient.getChildren(serviceProviderPath);

        if (providers == null || providers.isEmpty()) {
            String eMsg = "no providers for service[" + getServiceName(service) + "]";
            NoProviderException e = new NoProviderException(eMsg);
            logger.error(eMsg, e);
            throw e;
        }

        logger.debug("service[" + getServiceName(service) + "] has providers as " + providers);

        return providers;
    }

    /**
     * 
     * getServiceName:获取服务名称.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午5:14:20
     * @param service
     * @return
     */
    private String getServiceName(Method service) {
        return service.getDeclaringClass().getName() + "." + service.getName();
    }
}
