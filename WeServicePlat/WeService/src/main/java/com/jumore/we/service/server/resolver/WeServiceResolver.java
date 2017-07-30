/**
 * Project Name:WeService File Name:WeServiceResolver.java Package
 * Name:com.jumore.we.service.server.resolver Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月30日 下午8:42:19
 */
package com.jumore.we.service.server.resolver;

import java.util.List;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 服务解析器
 * 
 * @author 乔广
 * @date 2017年7月30日 下午8:42:19
 * @version
 * @see
 */
public interface WeServiceResolver {
    /**
     * resolveService:解析服务.
     * 依赖于spring mvc架构
     * 
     * @author 乔广
     * @date 2017年7月30日 下午8:44:11
     * @param dispatcherServlet 
     * @return
     */
    public List<Object> resolveService(DispatcherServlet dispatcherServlet);
}
