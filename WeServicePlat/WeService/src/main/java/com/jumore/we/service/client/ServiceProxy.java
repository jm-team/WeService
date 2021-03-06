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
import com.jumore.we.service.client.request.parameter.formatter.CommonParameterFormatter;
import com.jumore.we.service.client.request.parameter.name.resolver.RequestParameterNameResolver;
import com.jumore.we.service.client.request.parameter.name.resolver.ThoughtworksParameterNameResolver;
import com.jumore.we.service.client.request.parameter.resolver.RequestParameterResolver;
import com.jumore.we.service.client.request.parameter.resolver.SimpleRequestParameterResolver;
import com.jumore.we.service.client.request.parameter.setter.RequestParameterSetter;
import com.jumore.we.service.client.request.parameter.setter.SimpleRequestParameterSetter;
import com.jumore.we.service.client.request.resolver.HttpPostResolver;
import com.jumore.we.service.client.request.resolver.HttpRequestResolver;
import com.jumore.we.service.client.response.ResponseResultResolver;
import com.jumore.we.service.client.response.SimpleResponseResultResolver;
import com.jumore.we.service.client.response.converter.BooleanResultConverter;
import com.jumore.we.service.client.response.converter.ByteResultConverter;
import com.jumore.we.service.client.response.converter.CharacterResultConverter;
import com.jumore.we.service.client.response.converter.DateResultConverter;
import com.jumore.we.service.client.response.converter.DoubleResultConverter;
import com.jumore.we.service.client.response.converter.FloatResultConverter;
import com.jumore.we.service.client.response.converter.IntegerResultConverter;
import com.jumore.we.service.client.response.converter.LongResultConverter;
import com.jumore.we.service.client.response.converter.PojoResultConverter;
import com.jumore.we.service.client.response.converter.ShortResultConverter;
import com.jumore.we.service.client.response.converter.StringResultConverter;

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
        SimpleRequestParameterResolver requestParameterResolver = new SimpleRequestParameterResolver();
        RequestParameterNameResolver parameterNameResolver = new ThoughtworksParameterNameResolver();
        requestParameterResolver.setRequestParameterNameResolver(parameterNameResolver);
        requestParameterResolver.addFormatter(new CommonParameterFormatter());
        proxy.setRequestParameterResolver(requestParameterResolver);

        // setting RequestParameterSetter
        RequestParameterSetter requestParameterSetter = new SimpleRequestParameterSetter();
        proxy.setRequestParameterSetter(requestParameterSetter);

        // setting ResponseResultResolver
        SimpleResponseResultResolver responseResultResolver = new SimpleResponseResultResolver();
        responseResultResolver.addConverter(new StringResultConverter());
        responseResultResolver.addConverter(new BooleanResultConverter());
        responseResultResolver.addConverter(new ByteResultConverter());
        responseResultResolver.addConverter(new CharacterResultConverter());
        responseResultResolver.addConverter(new DateResultConverter());
        responseResultResolver.addConverter(new DoubleResultConverter());
        responseResultResolver.addConverter(new FloatResultConverter());
        responseResultResolver.addConverter(new IntegerResultConverter());
        responseResultResolver.addConverter(new LongResultConverter());
        responseResultResolver.addConverter(new ShortResultConverter());
        responseResultResolver.addConverter(new PojoResultConverter());
        proxy.setResponseResultResolver(responseResultResolver);

        return proxy;
    }

    @SuppressWarnings("unchecked")
    public <T> T newProxy(final Class<T> serviceClass) {
        if (serviceClass == null) {
            NullPointerException e = new NullPointerException("Service class connot be null");
            logger.error(e.getMessage(), e);
            throw e;
        }

        if (!serviceClass.isInterface()) {
            IllegalArgumentException e = new IllegalArgumentException("Service class must be a interface");
            logger.error(e.getMessage(), e);
            throw e;
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
