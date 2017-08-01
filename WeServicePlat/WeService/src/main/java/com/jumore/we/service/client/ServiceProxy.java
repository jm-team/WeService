/**
 * Project Name:WeServiceClient File Name:ServiceProxy.java Package
 * Name:com.jumore.we.service.client Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月20日 下午3:08:53
 */
package com.jumore.we.service.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.mapping.ServiceMapping;
import com.jumore.we.service.client.mapping.ZookeeperServiceMapping;
import com.jumore.we.service.client.mapping.selector.ServiceSelector;
import com.jumore.we.service.client.mapping.selector.WeightRandomServiceSelector;
import com.jumore.we.service.client.request.parameter.resolver.RequestParameterResolver;
import com.jumore.we.service.client.request.parameter.resolver.SimpleRequestParameterResolver;
import com.jumore.we.service.client.request.parameter.setter.RequestParameterSetter;
import com.jumore.we.service.client.request.parameter.setter.SimpleRequestParameterSetter;
import com.jumore.we.service.client.request.resolver.HttpPostResolver;
import com.jumore.we.service.client.request.resolver.HttpRequestResolver;
import com.jumore.we.service.client.response.ResponseResultResolver;

/**
 * Function: 服务代理
 * 
 * @author 乔广
 * @date 2017年7月20日 下午3:08:53
 * @version
 * @see
 */
public class ServiceProxy {
    private static final Logger      logger = LoggerFactory.getLogger(ServiceProxy.class);
    private ServiceMapping           serviceMapping;
    private HttpRequestResolver      requestResolver;
    private RequestParameterResolver requestParameterResolver;
    private RequestParameterSetter   requestParameterSetter;
    private ResponseResultResolver   responseResultResolver;

    private ServiceProxy() {

    }

    /**
     * @param serviceMapping the serviceMapping to set
     */
    public void setServiceMapping(ServiceMapping serviceMapping) {
        this.serviceMapping = serviceMapping;
    }

    /**
     * @param requestResolver the requestResolver to set
     */
    public void setRequestResolver(HttpRequestResolver requestResolver) {
        this.requestResolver = requestResolver;
    }

    /**
     * @param requestParameterResolver the requestParameterResolver to set
     */
    public void setRequestParameterResolver(RequestParameterResolver requestParameterResolver) {
        this.requestParameterResolver = requestParameterResolver;
    }

    /**
     * @param requestParameterSetter the requestParameterSetter to set
     */
    public void setRequestParameterSetter(RequestParameterSetter requestParameterSetter) {
        this.requestParameterSetter = requestParameterSetter;
    }

    /**
     * @param responseResultResolver the responseResultResolver to set
     */
    public void setResponseResultResolver(ResponseResultResolver responseResultResolver) {
        this.responseResultResolver = responseResultResolver;
    }

    public static ServiceProxy createDefault(String serverstring) {
        ServiceProxy proxy = new ServiceProxy();

        // setting ServiceMapping
        ServiceSelector serviceSelector = new WeightRandomServiceSelector();
        ZookeeperServiceMapping serviceMapping = new ZookeeperServiceMapping();
        serviceMapping.setServiceSelector(serviceSelector);
        serviceMapping.setServerstring(serverstring);
        ZkClient zkClient = new ZkClient(serverstring);
        serviceMapping.setZkClient(zkClient);
        proxy.setServiceMapping(serviceMapping);

        // setting HttpRequestResolver
        HttpRequestResolver requestResolver = new HttpPostResolver();
        proxy.setRequestResolver(requestResolver);

        // setting RequestParameterResolver
        RequestParameterResolver requestParameterResolver = new SimpleRequestParameterResolver();
        proxy.setRequestParameterResolver(requestParameterResolver);

        // setting RequestParameterSetter
        RequestParameterSetter requestParameterSetter = new SimpleRequestParameterSetter();
        proxy.setRequestParameterSetter(requestParameterSetter);

        return proxy;
    }

    @SuppressWarnings("unchecked")
    public <T> T newProxy(final Class<T> serviceClass) {
        if (serviceClass == null) {
            throw new NullPointerException("Service class connot be null");
        }

        if (!serviceClass.isInterface()) {
            throw new IllegalArgumentException("Service class must be a interface");
        }

        return (T) Proxy.newProxyInstance(ServiceProxy.class.getClassLoader(), new Class[] { serviceClass }, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient client = HttpClients.createDefault();
                URI requestURI = serviceMapping.getServiceRequestURI(method);
                HttpRequestBase request = requestResolver.resolveRequest(requestURI, method);
                List<NameValuePair> parameter = requestParameterResolver.resolveParameter(method, args);
                requestParameterSetter.setParameter(request, parameter);
                HttpResponse httpResponse = client.execute(request);
                Object result = responseResultResolver.resolveResult(httpResponse, method.getReturnType());
                return result;
            }
        });
    }
}
