/**
 * Project Name:WeService File Name:ZookeeperRegistrationCenter.java Package
 * Name:com.jumore.we.service.server.remote Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 上午8:36:10
 */
package com.jumore.we.service.server.remote;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.jumore.we.service.server.remote.model.ZookeeperRegistryModel;

/**
 * zookeeper注册中心
 * 
 * @author 乔广
 * @date 2017年7月31日 上午8:36:10
 * @version
 * @see
 */
public class ZookeeperRequestMappingMethodRegistrationCenter implements RegistrationCenter, InitializingBean {
    private static final Logger logger                = LoggerFactory.getLogger(ZookeeperRequestMappingMethodRegistrationCenter.class);
    public static final String  SERVICE_ZK_ROOT_PATH  = "/jumore.we.service";
    public static final String  SERVICE_PROVIDER_PATH = "/provider";

    /**
     * zookeeper集群地址
     */
    private String              serverstring;

    private ZkClient            zkClient;

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

    @Override
    public boolean support(Object model) {
        return model instanceof ZookeeperRegistryModel;
    }

    @Override
    public void remoteRegistry(Object model) {
        ZookeeperRegistryModel m = (ZookeeperRegistryModel) model;

        // 注册provider目录，同时创建父级目录。
        try {
            zkClient.createPersistent(m.getServiceProviderPath(), true);
        } catch (ZkNodeExistsException e) {
            logger.info("ignore this exception", e);
        }

        // 注册服务信息
        zkClient.createEphemeral(m.getServiceInfoPath(), m.getServiceInfo());
        logger.debug("service [" + m.getServiceInfoPath() + "] has been registed");
    }
}
