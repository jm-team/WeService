/**
 * Project Name:WeService File RequestMappingMethodService.java Package
 * Name:com.jumore.we.service.server Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月28日 下午6:36:23
 */
package com.jumore.we.service.server.service;

import java.lang.reflect.Method;
import java.util.Date;

import com.jumore.we.service.WeServiceInfo;

/**
 * 服务元数据。一个服务是以一个method为单位的
 * 
 * @author 乔广
 * @date 2017年7月28日 下午6:36:23
 * @version
 * @see
 */
public class RequestMappingMethodService {
    /**
     * 应用名称
     */
    private String     application;

    /**
     * 当前sdk版本
     */
    private String     sdkVersion;

    /**
     * 服务所在类实现的接口。因为无法确定哪个接口是服务接口，所以实现的所有接口，都会拼接method name注册
     */
    private Class<?>[] containers;

    /**
     * 服务方法
     */
    private Method     service;

    /**
     * 应用域名
     */
    private String     appDomain;

    /**
     * 应用端口号
     */
    private Integer        appPort;

    /**
     * 服务相对访问路径
     */
    private String     servicePath;

    /**
     * 服务创建时间
     */
    private Date       time;

    /**
     * Creates a new instance of ServiceMetadata
     * 
     * @param application
     * @param service
     * @param appDomain
     * @param appPort
     * @param servicePath
     */
    public RequestMappingMethodService(String application, String appDomain, Integer appPort, String servicePath, Method service) {
        this(application, appDomain, appPort, servicePath, service, new Date());
    }

    /**
     * Creates a new instance of ServiceMetadata
     * 
     * @param application
     * @param service
     * @param appDomain
     * @param appPort
     * @param servicePath
     * @param time
     */
    public RequestMappingMethodService(String application, String appDomain, Integer appPort, String servicePath, Method service, Date time) {
        this.sdkVersion = WeServiceInfo.VERSION;
        this.application = application;
        this.appDomain = appDomain;
        this.appPort = appPort;
        this.servicePath = servicePath;
        this.time = time;
        setService(service);
    }

    /**
     * application
     *
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * sdkVersion
     *
     * @return the sdkVersion
     */
    public String getSdkVersion() {
        return sdkVersion;
    }

    /**
     * container
     *
     * @return the container
     */
    public Class<?>[] getContainers() {
        return containers;
    }

    /**
     * service
     *
     * @return the service
     */
    public Method getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Method service) {
        this.service = service;
        this.containers = (Class<?>[]) service.getDeclaringClass().getGenericInterfaces();
    }

    /**
     * appDomain
     *
     * @return the appDomain
     */
    public String getAppDomain() {
        return appDomain;
    }

    /**
     * @param appDomain the appDomain to set
     */
    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    /**
     * appPort
     *
     * @return the appPort
     */
    public Integer getAppPort() {
        return appPort;
    }

    /**
     * @param appPort the appPort to set
     */
    public void setAppPort(Integer appPort) {
        this.appPort = appPort;
    }

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
     * time
     *
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
