/**
 * Project Name:WeService File Name:RequestMappingMethodServiceRegister.java
 * Package Name:com.jumore.we.service.server.register Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月28日 下午7:29:25
 */
package com.jumore.we.service.server.register;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.server.remote.ZookeeperRequestMappingMethodRegistrationCenter;
import com.jumore.we.service.server.remote.model.ZookeeperRegistryModel;
import com.jumore.we.service.server.service.RequestMappingMethodService;

/**
 * RequestMappingMethod为单位的服务的注册器.
 * 
 * @author 乔广
 * @date 2017年7月28日 下午7:29:25
 * @version
 * @see
 */
public class RequestMappingMethodServiceRegister extends AbstractServiceRegister {
    private static final Logger logger         = LoggerFactory.getLogger(RequestMappingMethodServiceRegister.class);
    private static final String PATH_SEPARATOR = "/";

    @Override
    public boolean supportService(Object service) {
        return service instanceof RequestMappingMethodService;
    }

    @Override
    protected List<Object> resolveRegistryModel(Object service) {
        List<Object> models = new ArrayList<Object>();
        RequestMappingMethodService serv = (RequestMappingMethodService) service;

        for (Class<?> container : serv.getContainers()) {
            ZookeeperRegistryModel model = new ZookeeperRegistryModel();

            String servicePath = ZookeeperRequestMappingMethodRegistrationCenter.SERVICE_ZK_ROOT_PATH + PATH_SEPARATOR + container.getName()
                    + "." + serv.getService().getName();
            model.setServicePath(servicePath);

            String providerPath = servicePath + ZookeeperRequestMappingMethodRegistrationCenter.SERVICE_PROVIDER_PATH;
            model.setServiceProviderPath(providerPath);

            String serviceInfoPath = doGetServiceInfoPath(providerPath, serv);
            model.setServiceInfoPath(serviceInfoPath);

            models.add(model);
            logger.debug("a service registry model[servicePath:" + servicePath + ",providerPath:" + providerPath + ",serviceInfoPath:"
                    + serviceInfoPath + "] has been created");
        }

        return models;
    }

    private String doGetServiceInfoPath(String providerPath, RequestMappingMethodService serv) {
        String serviceInfo = serv.getAppDomain();

        if (serv.getAppPort().intValue() != 80) {
            serviceInfo += ":" + serv.getAppPort();
        }

        if (!serv.getServicePath().startsWith(PATH_SEPARATOR)) {
            serviceInfo += PATH_SEPARATOR;
        }

        serviceInfo += serv.getServicePath();

        serviceInfo += "?timestamp=" + serv.getTime().getTime() + "&sdk=" + serv.getSdkVersion();

        return providerPath + PATH_SEPARATOR + URLEncoder.encode(serviceInfo);
    }
}
