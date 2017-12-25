package com.huaxia.sso.common.exception;


/**
 * @description 票据异常
 */
public class TicketException extends CASException {
    public TicketException() {
    }

    public TicketException(String message) {
        super(message);
    }

    public TicketException(Integer code, String message) {
        super(code, message);
    }

    public TicketException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public TicketException(Integer code, Throwable rootCause) {
        super(code, rootCause);
    }

    public TicketException(Integer code, String message, Throwable rootCause) {
        super(code, message, rootCause);
    }

    public TicketException(Throwable rootCause) {
        super(rootCause);
    }
}
