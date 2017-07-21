/** 
 * Project Name:WeServiceClient 
 * File Name:ServiceProxy.java 
 * Package Name:com.jumore.we.service.client
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月20日 下午3:08:53
 */
package com.jumore.we.service.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;

/**
 * Function: 服务代理
 * 
 * @author 乔广
 * @date 2017年7月20日 下午3:08:53
 * @version 
 * @see
 */
public abstract class ServiceProxy {
    private static ServiceMapping serviceMapping;
    private static HttpRequestResolver requestResolver;
    private static RequestParameterResolver requestParameterResolver;
    private static ResponseResultResolver responseResultResolver;
    
    public static <T> T newProxy(final Class<T> serviceClass){
        if(serviceClass == null){
            throw new NullPointerException("Service class connot be null");
        }
        
        if(!serviceClass.isInterface()){
            throw new IllegalArgumentException("Service class must be a interface");
        }
        
        return (T)Proxy.newProxyInstance(ServiceProxy.class.getClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient client = HttpClients.createDefault();
                HttpRequestBase request = requestResolver.resolveRequest();
                HttpEntity httpEntity = requestParameterResolver.resolveParameter(args);
                HttpResponse httpResponse = client.execute(request);
                Object result = responseResultResolver.resolveResult(httpResponse, method.getReturnType());
                return result;
            }
        });
    }
}
