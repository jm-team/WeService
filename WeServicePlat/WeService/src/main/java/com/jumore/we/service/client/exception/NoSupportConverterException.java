/**
 * Project Name:WeService File Name:NoSupportConverterException.java
 * Package Name:com.jumore.we.service.client.exception Copyright (c) 2017,
 * JUMORE Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月1日 下午1:07:00
 */
package com.jumore.we.service.client.exception;

/**
 * Function: 没有对应的Converter异常
 * 
 * @author 乔广
 * @date 2017年8月1日 下午1:07:00
 * @version
 * @see
 */
public class NoSupportConverterException extends RuntimeException {
    private static final long serialVersionUID = 6531748281575023181L;

    public NoSupportConverterException() {
        super();
    }

    public NoSupportConverterException(String s) {
        super(s);
    }

    public NoSupportConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSupportConverterException(Throwable cause) {
        super(cause);
    }
}
