/**
 * Project Name:WeService File Name:NoProviderException.java Package
 * Name:com.jumore.we.service.client.exception Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
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
public class RequestFailException extends RuntimeException {
    private static final long serialVersionUID = 8300686293312493193L;

    public RequestFailException() {
        super();
    }

    public RequestFailException(String s) {
        super(s);
    }

    public RequestFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestFailException(Throwable cause) {
        super(cause);
    }
}
