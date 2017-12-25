
package com.huaxia.sso.common.exception;

public class InvalidTicketException extends TicketException {

    public InvalidTicketException() {
    }

    public InvalidTicketException(String message) {
        super(message);
    }

    public InvalidTicketException(Integer code, String message) {
        super(code, message);
    }

    public InvalidTicketException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public InvalidTicketException(Integer code, Throwable rootCause) {
        super(code, rootCause);
    }

    public InvalidTicketException(Integer code, String message, Throwable rootCause) {
        super(code, message, rootCause);
    }

    public InvalidTicketException(Throwable rootCause) {
        super(rootCause);
    }
}
