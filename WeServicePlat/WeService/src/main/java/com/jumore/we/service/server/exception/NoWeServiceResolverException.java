/**
 * Project Name:WeService File Name:NoWeServiceResolverException.java Package
 * Name:com.jumore.we.service.server.exception Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月30日 下午9:29:12
 */
package com.jumore.we.service.server.exception;

/**
 * Function: 无WeServiceResolver异常
 * 
 * @author 乔广
 * @date 2017年7月30日 下午9:29:12
 * @version
 * @see
 */
public class NoWeServiceResolverException extends RuntimeException {
    private static final long serialVersionUID = -8757286428106119782L;

    public NoWeServiceResolverException() {
        super();
    }

    public NoWeServiceResolverException(String s) {
        super(s);
    }

    public NoWeServiceResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoWeServiceResolverException(Throwable cause) {
        super(cause);
    }

}
