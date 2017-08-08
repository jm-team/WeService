/**
 * Project Name:web-core File Name:CommonDateConversion.java Package
 * Name:com.company.platform.web.core.conversion Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author joe
 * @date 2017年7月21日 上午10:10:27
 */
package com.jumore.we.service.sample.provider.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;

/**
 * Function: 时间参数绑定转换类型
 * 
 * @author joe
 * @date 2017年7月21日 上午10:10:27
 * @version
 * @see
 */
public class CommonDateConversion implements InitializingBean, Converter<String, Date> {
    private static final Logger           logger          = LoggerFactory.getLogger(CommonDateConversion.class);
    private static final List<String>     defaultPatterns = new ArrayList<String>();
    private static final SimpleDateFormat sdf             = new SimpleDateFormat();
    private List<String>                  patterns;
    private Boolean                       registeDefault  = true;

    static {
        defaultPatterns.add("yyyy-MM-dd HH:mm:ss");
        defaultPatterns.add("yyyy-MM-dd");
        defaultPatterns.add("yyyy/MM/dd HH:mm:ss");
        defaultPatterns.add("yyyy/MM/dd");
    }

    /**
     * @param patterns the patterns to set
     */
    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    /**
     * @param registeDefault the registeDefault to set
     */
    public void setRegisteDefault(Boolean registeDefault) {
        this.registeDefault = registeDefault;
    }

    /**
     * 添加默认.
     */
    public void afterPropertiesSet() throws Exception {
        if (patterns == null) {
            if (registeDefault) {
                logger.debug("pattens is null, use default patterns");
                patterns = defaultPatterns;
            }
        } else {
            if (registeDefault) {
                logger.debug("merge default patterns into patterns");

                for (String pattern : defaultPatterns) {
                    if (!patterns.contains(pattern)) {
                        patterns.add(pattern);
                    }
                }
            }
        }

    }

    /**
     * 时间参数绑定转换类型.
     */
    public Date convert(String source) {
        if (patterns == null || patterns.isEmpty()) {
            return null;
        }

        Date date = null;

        for (String pattern : patterns) {
            sdf.applyPattern(pattern);

            try {
                date = sdf.parse(source);
                break;
            } catch (ParseException e) {
                logger.debug("parse date string:" + source + " failed using patten:" + pattern);
            }
        }

        return date;
    }

}
