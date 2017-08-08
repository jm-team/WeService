/**
 * Project Name:WeService File Name:IllegalResponseResultException.java Package
 * Name:com.jumore.we.service.client.exception Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午2:49:58
 */
package com.jumore.we.service.client.exception;

/**
 * Function: 非法响应结果异常
 * 
 * @author 乔广
 * @date 2017年8月8日 下午2:49:58
 * @version
 * @see
 */
public class IllegalResponseResultException extends IllegalArgumentException {
    private static final long serialVersionUID = 6864078460480259660L;

    public IllegalResponseResultException() {
        super();
    }

    public IllegalResponseResultException(String s) {
        super(s);
    }

    public IllegalResponseResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalResponseResultException(Throwable cause) {
        super(cause);
    }

}
