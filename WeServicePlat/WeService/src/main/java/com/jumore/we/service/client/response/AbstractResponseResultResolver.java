/**
 * Project Name:WeService File Name:AbstractResponseResultResolver.java Package
 * Name:com.jumore.we.service.client.response Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午2:48:35
 */
package com.jumore.we.service.client.response;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumore.we.service.client.exception.NoSupportConverterException;
import com.jumore.we.service.client.exception.RequestFailException;
import com.jumore.we.service.client.response.converter.ResponseResultConverter;

/**
 * Function: 返回结果抽象实现
 * 
 * @author 乔广
 * @date 2017年8月1日 下午2:48:35
 * @version
 * @see
 */
public abstract class AbstractResponseResultResolver implements ResponseResultResolver {
    private static final Logger logger = LoggerFactory.getLogger(AbstractResponseResultResolver.class);

    @Override
    public Object resolveResult(HttpResponse response, Class<?> resultClass) {
        if (resultClass.equals(Void.class)) {
            return null;
        }

        HttpEntity entity = response.getEntity();
        String responseStr = null;

        try {
            responseStr = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            RequestFailException e = new RequestFailException(responseStr);
            logger.error(responseStr, e);
            throw e;
        }

        return doResolveResult(responseStr, resultClass);
    }

    private Object doResolveResult(String response, Class<?> resultClass) {
        boolean hasConverter = false;
        Object result = null;

        for (ResponseResultConverter converter : getResponseResultConverters()) {
            if (converter.support(resultClass)) {
                hasConverter = true;
                Object obj = converter.convert(response, resultClass);

                if (obj != null) {
                    result = obj;
                    break;
                }
            }
        }

        if (!hasConverter) {
            NoSupportConverterException e = new NoSupportConverterException(
                    "no convert support for return type[" + resultClass.getName() + "]");
            logger.error(e.getMessage());
            throw e;
        }

        return result;
    }

    public abstract List<ResponseResultConverter> getResponseResultConverters();

}
