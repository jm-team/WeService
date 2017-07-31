/** 
 * Project Name:WeService 
 * File Name:HttpPostResolver.java 
 * Package Name:com.jumore.we.service.client.request
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月31日 下午7:23:58
 */
package com.jumore.we.service.client.request;

import java.lang.reflect.Method;
import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Function: post请求。
 * 目前只提供post请求
 * 
 * @author 乔广
 * @date 2017年7月31日 下午7:23:58
 * @version 
 * @see
 */
public class HttpPostResolver implements HttpRequestResolver {

    @Override
    public HttpRequestBase resolveRequest(URI uri, Method service) {
        HttpPost post = new HttpPost(uri);
        return post;
    }

}
