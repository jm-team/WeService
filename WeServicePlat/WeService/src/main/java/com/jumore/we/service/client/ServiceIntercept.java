package com.jumore.we.service.client;

import com.alibaba.fastjson.JSONObject;
import com.jumore.dove.common.log.LogBuilder;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.we.service.utils.ParameterNameUtils;
import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 用cglib代理远程服务
 * @autor Long Qiong
 * @create 2017/6/8
 */
public class ServiceIntercept {
    private static final LogBuilder LOGGER = LogHelper.getLogger(ServiceIntercept.class).getBuilder();

    /**
     * 用cglib代理远程服务
     * @param cls  代理类
     * @param version 代理类版本
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteService(Class<T> cls, final String version) {
        return (T) Enhancer.create(cls, new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                //代理类
                String className = obj.getClass().getName();
                //代理服务
                String service = StringUtils.split(className, "$$")[0];

                //代理服务包
                String[] servicePackage = StringUtils.split(service, ".");
                //代理服务名称
                String serviceName = servicePackage[servicePackage.length - 1];
                //代理方法
                String methodName = method.getName();

                //根据服务名及版本号获取主机
                String host = getHostOfService(serviceName , version);
                String url = host + serviceName + "/" + methodName;

                args[2].getClass();
                //获取方法参数
                List<NameValuePair> params = getMethodParamValue(method, args);

                //代理的服务采用post请求实现
                HttpResponse response = executeByEncodedForm(url, params);

                //获取post请求结果
                InputStream inStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line);
                inStream.close();

                String result = strber.toString();
                LOGGER.info(result);

                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    LOGGER.info("请求服务" + url + "成功");

                } else {
                    LOGGER.info("请求服务" + url + "失败");
                }

                Class backType = method.getReturnType();
                return JSONObject.parseObject(result, backType);
            }
        });
    }

    public static String getHostOfService(String serviceName , String version){

//        String url = String.format("http://localhost:8844/addService?service=%s&version=%s&host=%s", serviceName, version, "abc") ;
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        try {
//            httpClient.execute(httpGet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        String url2 = String.format("http://localhost:8844/getHost?service=%s&version=%s", serviceName, version) ;
//        httpGet = new HttpGet(url2);
//        try {
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //服务治理获取主机地址 TODO


        //ca
        return "http://localhost:8819";
    }
    /**
     * 获取方法参数
     * @param method
     * @param args
     * @return
     */
    private static List<NameValuePair> getMethodParamValue(Method method, Object[] args) {
        List<NameValuePair> paramList = new ArrayList<>();
        Paranamer paranamer = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));

        String[] parameterNames = paranamer.lookupParameterNames(method, false); // will return null if not found

        Class[] paramTypes = method.getParameterTypes();

        for (int i = 0; i < paramTypes.length; i++) {
            if (isJavaClass(paramTypes[i])) {
                paramList.add(new BasicNameValuePair(parameterNames[i], args[i].toString()));
            } else {
                Field[] fields = paramTypes[i].getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    String fieldName = fields[j].getName();
                    try {
                        Object value = fields[j].get(args[i]);
                        paramList.add(new BasicNameValuePair(fieldName, value.toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return paramList;
    }

    /**
     * 表单提交方式HTTP连接
     *
     * @param url
     * @param formParams
     * @return
     */
    public static HttpResponse executeByEncodedForm(String url, List<NameValuePair> formParams) {
        HttpResponse response = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            post.setEntity(entity);
            response = httpClient.execute(post);
        } catch (IOException e) {
            LOGGER.info("提交请求出错：{}", e.getStackTrace());
        }

        return response;
    }

    /**
     * 判断一个类型是Java本身的类型，还是用户自定义的类型
     *
     * @param clz
     * @return
     */
    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * 基本类型与包装类判断
     *
     * @param clz
     * @return
     */
    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
