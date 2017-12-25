package com.huaxia.sso.common.exception;

public class AuthenticationException extends CASException {


    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Integer code, String message) {
        super(code, message);
    }

    public AuthenticationException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public AuthenticationException(Integer code, Throwable rootCause) {
        super(code, rootCause);
    }

    public AuthenticationException(Integer code, String message, Throwable rootCause) {
        super(code, message, rootCause);
    }

    public AuthenticationException(Throwable rootCause) {
        super(rootCause);
    }
}
