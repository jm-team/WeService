/** 
 * Project Name:WeService 
 * File Name:NoProviderException.java 
 * Package Name:com.jumore.we.service.client.exception
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 乔广
 * @date 2017年7月31日 下午5:08:43
 */
package com.jumore.we.service.client.exception;

/**
 * 无提供者异常
 * 
 * @author 乔广
 * @date 2017年7月31日 下午5:08:43
 * @version 
 * @see
 */
public class NoProviderException extends RuntimeException {
    private static final long serialVersionUID = -5288371767664248784L;

    public NoProviderException() {
        super();
    }

    public NoProviderException(String s) {
        super(s);
    }

    public NoProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProviderException(Throwable cause) {
        super(cause);
    }
}
