/**
 * Project Name:WeService File Name:NoRegistrationCenterException.java Package
 * Name:com.jumore.we.service.server.exception Copyright (c) 2017, JUMORE
 * Co.,Ltd. All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年7月31日 上午8:53:05
 */
package com.jumore.we.service.server.exception;

/**
 * 没有RegistrationCenter异常
 * 
 * @author 乔广
 * @date 2017年7月31日 上午8:53:05
 * @version
 * @see
 */
public class NoRegistrationCenterException extends RuntimeException {
    private static final long serialVersionUID = 5370789342963128131L;

    public NoRegistrationCenterException() {
        super();
    }

    public NoRegistrationCenterException(String s) {
        super(s);
    }

    public NoRegistrationCenterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRegistrationCenterException(Throwable cause) {
        super(cause);
    }
}
