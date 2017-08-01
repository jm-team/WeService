/**
 * Project Name:WeService File Name:RequestMappingMethodServiceResolver.java
 * Package Name:com.jumore.we.service.server.resolver Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月30日 下午9:01:07
 */
package com.jumore.we.service.server.resolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jumore.we.service.server.service.RequestMappingMethodService;
import com.jumore.we.service.utils.ReflectUtils;

/**
 * RequestMappingMethodService解析器
 * 
 * @author 乔广
 * @date 2017年7月30日 下午9:01:07
 * @version
 * @see
 */
public class RequestMappingMethodServiceResolver implements WeServiceResolver, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(RequestMappingMethodServiceResolver.class);

    /**
     * 应用名称
     */
    private String              application;

    /**
     * 应用域名
     */
    private String              appDomain;

    /**
     * 应用端口号
     */
    private Integer             appPort;

    /**
     * 权重
     */
    private Integer             weight;

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
     * weight
     *
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (appPort == null) {
            appPort = 80;
        }

        if (weight == null || weight < 1) {
            weight = 1;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> resolveService(DispatcherServlet dispatcherServlet) {
        List<Object> services = new ArrayList<Object>();

        try {
            List<HandlerMapping> handlerMappings = (List<HandlerMapping>) ReflectUtils.getFieldValue(DispatcherServlet.class,
                    "handlerMappings", dispatcherServlet);

            for (HandlerMapping handlerMapping : handlerMappings) {
                if (!(handlerMapping instanceof RequestMappingHandlerMapping)) {
                    continue;
                }

                MultiValueMap<String, RequestMappingInfo> urlMap = (MultiValueMap<String, RequestMappingInfo>) ReflectUtils
                        .getFieldValue(AbstractHandlerMethodMapping.class, "urlMap", handlerMapping);
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = (Map<RequestMappingInfo, HandlerMethod>) ReflectUtils
                        .getFieldValue(AbstractHandlerMethodMapping.class, "handlerMethods", handlerMapping);

                for (Entry<String, List<RequestMappingInfo>> urlEntry : urlMap.entrySet()) {
                    for (RequestMappingInfo requestMappingInfo : urlEntry.getValue()) {
                        HandlerMethod handlerMethod = handlerMethods.get(requestMappingInfo);

                        RequestMappingMethodService service = new RequestMappingMethodService(application, appDomain, appPort,
                                urlEntry.getKey(), weight, handlerMethod.getMethod(), new Date());
                        services.add(service);
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            logger.error("resole service failed", e);
            throw new RuntimeException(e);
        }

        return services;
    }

}
