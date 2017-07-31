/**
 * Project Name:WeService File Name:RegistryModel.java Package
 * Name:com.jumore.we.service.server.remote Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 上午10:03:56
 */
package com.jumore.we.service.server.remote.model;

/**
 * 远程注册对象
 * 
 * @author 乔广
 * @date 2017年7月31日 上午10:03:56
 * @version
 * @see
 */
public class ZookeeperRegistryModel {
    /**
     * 服务路径
     */
    private String servicePath;

    /**
     * 服务数据
     */
    private Object serviceData;

    /**
     * 提供者路径
     */
    private String serviceProviderPath;

    /**
     * 提供者数据
     */
    private Object serviceProviderData;

    /**
     * 服务信息路径
     */
    private String serviceInfoPath;

    /**
     * 服务信息数据
     */
    private Object serviceInfo;

    /**
     * servicePath
     *
     * @return the servicePath
     */
    public String getServicePath() {
        return servicePath;
    }

    /**
     * @param servicePath the servicePath to set
     */
    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    /**
     * serviceData
     *
     * @return the serviceData
     */
    public Object getServiceData() {
        return serviceData;
    }

    /**
     * @param serviceData the serviceData to set
     */
    public void setServiceData(Object serviceData) {
        this.serviceData = serviceData;
    }

    /**
     * serviceProviderPath
     *
     * @return the serviceProviderPath
     */
    public String getServiceProviderPath() {
        return serviceProviderPath;
    }

    /**
     * @param serviceProviderPath the serviceProviderPath to set
     */
    public void setServiceProviderPath(String serviceProviderPath) {
        this.serviceProviderPath = serviceProviderPath;
    }

    /**
     * serviceProviderData
     *
     * @return the serviceProviderData
     */
    public Object getServiceProviderData() {
        return serviceProviderData;
    }

    /**
     * @param serviceProviderData the serviceProviderData to set
     */
    public void setServiceProviderData(Object serviceProviderData) {
        this.serviceProviderData = serviceProviderData;
    }

    /**
     * serviceInfoPath
     *
     * @return the serviceInfoPath
     */
    public String getServiceInfoPath() {
        return serviceInfoPath;
    }

    /**
     * @param serviceInfoPath the serviceInfoPath to set
     */
    public void setServiceInfoPath(String serviceInfoPath) {
        this.serviceInfoPath = serviceInfoPath;
    }

    /**
     * serviceInfo
     *
     * @return the serviceInfo
     */
    public Object getServiceInfo() {
        return serviceInfo;
    }

    /**
     * @param serviceInfo the serviceInfo to set
     */
    public void setServiceInfo(Object serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
