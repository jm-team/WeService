/** 
 * Project Name:WeService 
 * File Name:NoSupportParameterFormatterException.java 
 * Package Name:com.jumore.we.service.client.exception
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年8月1日 下午1:07:00
 */
package com.jumore.we.service.client.exception;

/**
 * Function: 没有对应的ParameterFormatter异常
 * 
 * @author 乔广
 * @date 2017年8月1日 下午1:07:00
 * @version 
 * @see
 */
public class NoSupportParameterFormatterException extends RuntimeException {
    private static final long serialVersionUID = 8698421211354103208L;

    public NoSupportParameterFormatterException() {
        super();
    }

    public NoSupportParameterFormatterException(String s) {
        super(s);
    }

    public NoSupportParameterFormatterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSupportParameterFormatterException(Throwable cause) {
        super(cause);
    }
}
