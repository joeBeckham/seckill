package com.xsjt.core.exception;

import java.io.Serializable;

/**
 * 限流异常类
 */
public class LimitExcetion extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2317458063483409184L;

    public LimitExcetion() {
        super();
    }

    public LimitExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitExcetion(Throwable cause) {
        super(cause);
    }

    public LimitExcetion(String message) {
        super(message);
    }
}
