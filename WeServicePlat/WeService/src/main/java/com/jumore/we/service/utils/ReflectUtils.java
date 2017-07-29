/**
 * Project Name:WeService File Name:ReflectUtils.java Package
 * Name:com.jumore.we.service.utils Copyright (c) 2017, JUMORE Co.,Ltd. All
 * Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月29日 上午6:23:54
 */
package com.jumore.we.service.utils;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * 
 * @author 乔广
 * @date 2017年7月29日 上午6:23:54
 * @version
 * @see
 */
public abstract class ReflectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * getFieldValue:获取对象某个字段的值.
     * 
     * @author 乔广
     * @date 2017年7月29日 上午6:35:58
     * @param clazz
     * @param fieldName
     * @param target
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Class<?> clazz, String fieldName, Object target)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);

        if (field.isAccessible()) {
            return field.get(target);
        }

        field.setAccessible(true);
        logger.debug(field.getName() + " is not accessible, set it's accessible true");

        Object result = field.get(target);

        field.setAccessible(false);
        logger.debug("reset " + field.getName() + "'s accessible true");

        return result;
    }
}
