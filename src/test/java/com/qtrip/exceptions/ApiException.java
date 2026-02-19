package com.qtrip.exceptions;

/**
 * Custom exception for API-related errors.
 *
 * @author Natarajan M
 */
public class ApiException extends FrameworkException {

    private final int statusCode;
    private final String responseBody;

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = null;
    }

    public ApiException(String message, int statusCode, String responseBody) {
        super(String.format("%s | Status: %d | Response: %s", message, statusCode, responseBody));
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}

