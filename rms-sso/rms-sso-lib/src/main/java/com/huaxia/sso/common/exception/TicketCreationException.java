package com.huaxia.sso.common.exception;

/**
 * @description 票据创建异常
 */
public class TicketCreationException extends TicketException {
    public TicketCreationException() {
    }

    public TicketCreationException(String message) {
        super(message);
    }

    public TicketCreationException(Integer code, String message) {
        super(code, message);
    }

    public TicketCreationException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public TicketCreationException(Integer code, Throwable rootCause) {
        super(code, rootCause);
    }

    public TicketCreationException(Integer code, String message, Throwable rootCause) {
        super(code, message, rootCause);
    }

    public TicketCreationException(Throwable rootCause) {
        super(rootCause);
    }
}
