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
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jumore.we.service.server.register.CompositeServiceRegister;
import com.jumore.we.service.server.register.RequestMappingMethodServiceRegister;
import com.jumore.we.service.server.register.WeServiceRegister;
import com.jumore.we.service.server.service.RequestMappingMethodService;
import com.jumore.we.service.utils.ReflectUtils;

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
        List<RequestMappingMethodService> services = resolveRequestMappingMethodService();
        // 上面只解析了RequestMappingMethod处理器对应的Service，后面还可以添加其他类型的处理器对应的Service，
        // 如果有多类型Service的需求，需要抽象ServiceResolver
        
        WeServiceRegister register = initRegister();
        
        for (RequestMappingMethodService requestMappingMethodService : services) {
            register.registeService(requestMappingMethodService);
        }
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

        if(registerMap == null || registerMap.isEmpty()){
            logger.debug("no WeServiceRegister in config");
            RequestMappingMethodServiceRegister rmmsRegister = new RequestMappingMethodServiceRegister();
            compositeServiceRegister.getRegisters().add(rmmsRegister);
        } else{
            compositeServiceRegister.getRegisters().addAll(registerMap.values());
        }
        
        
        return compositeServiceRegister;
    }

    /**
     * resolveRequestMappingMethodService:解析服务. 
     * 后续可以考虑抽象成 服务解析器ServiceResolver
     * 
     * @author 乔广
     * @date 2017年7月29日 上午6:39:33
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<RequestMappingMethodService> resolveRequestMappingMethodService() {
        List<RequestMappingMethodService> services = new ArrayList<RequestMappingMethodService>();

        try {
            List<HandlerMapping> handlerMappings = (List<HandlerMapping>) ReflectUtils.getFieldValue(DispatcherServlet.class,
                    "handlerMappings", this);

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

                        RequestMappingMethodService service = new RequestMappingMethodService("jbh", "127.0.0.1", 8080, urlEntry.getKey(),
                                handlerMethod.getMethod());
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
