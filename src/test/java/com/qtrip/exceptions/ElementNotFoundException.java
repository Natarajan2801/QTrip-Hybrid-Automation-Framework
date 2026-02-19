package com.qtrip.exceptions;

/**
 * Custom framework exception for element not found scenarios.
 * Provides better error messages than standard Selenium exceptions.
 *
 * @author Natarajan M
 */
public class ElementNotFoundException extends FrameworkException {

    public ElementNotFoundException(String elementName) {
        super("Element not found: " + elementName);
    }

    public ElementNotFoundException(String elementName, String locator) {
        super(String.format("Element '%s' not found with locator: %s", elementName, locator));
    }

    public ElementNotFoundException(String elementName, Throwable cause) {
        super("Element not found: " + elementName, cause);
    }
}

