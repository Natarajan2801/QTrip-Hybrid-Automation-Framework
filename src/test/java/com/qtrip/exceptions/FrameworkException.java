package com.qtrip.exceptions;

/**
 * Base framework exception class.
 * All custom framework exceptions extend this.
 *
 * @author Natarajan M
 */
public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}

