package com.huaxia.sso.common.exception;

/**
 * @description 限制策略规则
 */
public class RestrictionPolicyException extends CASException {
    public RestrictionPolicyException() {
    }

    public RestrictionPolicyException(String message) {
        super(message);
    }

    public RestrictionPolicyException(Integer code, String message) {
        super(code, message);
    }

    public RestrictionPolicyException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public RestrictionPolicyException(Integer code, Throwable rootCause) {
        super(code, rootCause);
    }

    public RestrictionPolicyException(Integer code, String message, Throwable rootCause) {
        super(code, message, rootCause);
    }

    public RestrictionPolicyException(Throwable rootCause) {
        super(rootCause);
    }
}
