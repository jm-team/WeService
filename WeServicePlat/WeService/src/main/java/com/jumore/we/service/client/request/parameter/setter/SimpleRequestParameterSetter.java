/**
 * Project Name:WeService File Name:SimpleRequestParameterSetter.java Package
 * Name:com.jumore.we.service.client.request Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 下午8:06:41
 */
package com.jumore.we.service.client.request.parameter.setter;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function: 请求参数设置器实现
 * 
 * @author 乔广
 * @date 2017年7月31日 下午8:06:41
 * @version
 * @see
 */
public class SimpleRequestParameterSetter implements RequestParameterSetter {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRequestParameterSetter.class);

    @Override
    public void setParameter(HttpRequestBase request, List<NameValuePair> parameter) {
        // HttpEntityEnclosingRequestBase类型都是通过设置Entity的
        // 包括HttpPost，HttpPatch，HttpPut
        if (request instanceof HttpEntityEnclosingRequestBase) {
            setEntity((HttpEntityEnclosingRequestBase) request, parameter);
            return;
        }

        if (request instanceof HttpGet || request instanceof HttpDelete || request instanceof HttpHead || request instanceof HttpOptions
                || request instanceof HttpTrace) {
            URI uri = request.getURI();
            uri = addParametersToUri(uri, parameter);
            request.setURI(uri);
        }
    }

    /**
     * addParametersToUri:将请求参数拼装到url上.
     * 
     * @author 乔广
     * @date 2017年8月1日 上午9:27:48
     * @param uri
     * @param parameter
     * @return
     */
    private URI addParametersToUri(URI uri, List<NameValuePair> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return uri;
        }

        String query = uri.getQuery();

        if (query == null) {
            query = "";
        }

        for (NameValuePair param : parameter) {
            query += "&" + param.getName() + "=" + param.getValue();
        }

        if (query.startsWith("&")) {
            query = query.substring(1);
        }

        URI newUri = null;
        try {
            newUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), query, uri.getFragment());
            logger.debug(newUri.toString());
        } catch (URISyntaxException e) {
            logger.error("URI Syntax", e);
            throw new RuntimeException(e);
        }

        return newUri;
    }

    /**
     * setPost:post请求参数设置.
     * 
     * @author 乔广
     * @date 2017年7月31日 下午8:07:59
     */
    private void setEntity(HttpEntityEnclosingRequestBase post, List<NameValuePair> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return;
        }

        try {
            HttpEntity entity = new UrlEncodedFormEntity(parameter, "utf-8");
            post.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            logger.error("Unsupported Encoding", e);
            throw new RuntimeException(e);
        }

    }

}
