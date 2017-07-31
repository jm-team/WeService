/**
 * Project Name:WeService File Name:WeServiceDispatcherServlet.java Package
 * Name:com.jumore.we.service.server.register Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月28日 下午4:44:18
 */
package com.jumore.we.service.server.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import com.jumore.we.service.server.exception.NoRegistrationCenterException;
import com.jumore.we.service.server.exception.NoWeServiceResolverException;
import com.jumore.we.service.server.register.AbstractServiceRegister;
import com.jumore.we.service.server.register.CompositeServiceRegister;
import com.jumore.we.service.server.register.RequestMappingMethodServiceRegister;
import com.jumore.we.service.server.register.WeServiceRegister;
import com.jumore.we.service.server.remote.RegistrationCenter;
import com.jumore.we.service.server.resolver.CompositeServiceResolver;
import com.jumore.we.service.server.resolver.WeServiceResolver;

/**
 * 添加服务注册功能
 * 
 * @author 乔广
 * @date 2017年7月28日 下午4:44:18
 * @version
 * @see
 */
@SuppressWarnings("serial")
public class WeServiceDispatcherServlet extends DispatcherServlet {
    private static final Logger logger = LoggerFactory.getLogger(WeServiceDispatcherServlet.class);

    /**
     * 注册服务.
     */
    @Override
    protected void initFrameworkServlet() throws ServletException {
        super.initFrameworkServlet();
        registeRequestMappingService();
    }

    /**
     * registeRequestMappingService:注册服务.
     * 
     * @author 乔广
     * @date 2017年7月29日 上午6:37:32
     */
    private void registeRequestMappingService() {
        // 这里的返回类型都是composite的实例，可以改成返回list实例。效果一样，但是真正意义上来说，返回集合更好。
        WeServiceResolver resolver = initResolver();
        WeServiceRegister register = initRegister();

        List<Object> services = resolver.resolveService(this);
        for (Object service : services) {
            register.registeService(service);
        }
    }

    /**
     * initResolver:初始化服务解析器.
     * 
     * @author 乔广
     * @date 2017年7月30日 下午9:11:17
     * @return
     */
    private WeServiceResolver initResolver() {
        CompositeServiceResolver compositeServiceResolver = new CompositeServiceResolver();
        Map<String, WeServiceResolver> resolverMap = getWebApplicationContext().getBeansOfType(WeServiceResolver.class);

        if (resolverMap == null || resolverMap.isEmpty()) {
            // 这里规定，服务解析器是需要配置的，因为解析服务的时候可能需要配置一些内容:比如appName,host,port。
            // 这些配置在dispatcherservlet中读取也可以，但不符合规范
            NoWeServiceResolverException e = new NoWeServiceResolverException("no  WeServiceResolver in config");
            logger.error("no  WeServiceResolver in config", e);
            throw e;
        }

        compositeServiceResolver.getResolvers().addAll(resolverMap.values());

        return compositeServiceResolver;
    }

    /**
     * initRegister:初始化注册器.
     * 
     * @author 乔广
     * @date 2017年7月29日 上午7:46:35
     * @return
     */
    private WeServiceRegister initRegister() {
        CompositeServiceRegister compositeServiceRegister = new CompositeServiceRegister();

        Map<String, WeServiceRegister> registerMap = getWebApplicationContext().getBeansOfType(WeServiceRegister.class);

        if (registerMap == null || registerMap.isEmpty()) {
            logger.debug("no WeServiceRegister in config");
            RequestMappingMethodServiceRegister rmmsRegister = new RequestMappingMethodServiceRegister();
            compositeServiceRegister.getRegisters().add(rmmsRegister);
        } else {
            compositeServiceRegister.getRegisters().addAll(registerMap.values());
        }
        
        initRegistrationCenter(compositeServiceRegister);

        return compositeServiceRegister;
    }

    /**
     * initRegistrationCenter:初始化注册中心.
     * 
     * @author 乔广
     * @date 2017年7月31日 上午9:34:22
     * @param serviceRegister
     */
    private void initRegistrationCenter(WeServiceRegister serviceRegister) {
        Map<String, RegistrationCenter> rCentersMap = getWebApplicationContext().getBeansOfType(RegistrationCenter.class);
        
        if (rCentersMap == null || rCentersMap.isEmpty()) {
            String eMsg = "no RegistrationCenter in config";
            NoRegistrationCenterException e = new NoRegistrationCenterException(eMsg);
            logger.error(eMsg, e);
            throw e;
        }
        
        List<RegistrationCenter> rCenters = new ArrayList<RegistrationCenter>(rCentersMap.values());
        
        if(serviceRegister instanceof CompositeServiceRegister){
            for (WeServiceRegister register : ((CompositeServiceRegister) serviceRegister).getRegisters()) {
                if(register instanceof AbstractServiceRegister){
                    ((AbstractServiceRegister) register).setRcenters(rCenters);
                }
            }
        }
        
        if(serviceRegister instanceof AbstractServiceRegister){
            ((AbstractServiceRegister) serviceRegister).setRcenters(rCenters);
        }
    }

}
