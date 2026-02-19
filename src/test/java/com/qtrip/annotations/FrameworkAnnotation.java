package com.qtrip.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for framework-level method tracking.
 * Automatically logs method entry, exit, and execution time.
 *
 * @author Natarajan M
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {

    /**
     * Description of the action.
     */
    String description() default "";

    /**
     * Whether to log execution.
     */
    boolean logExecution() default true;

    /**
     * Whether to take screenshot after action.
     */
    boolean screenshot() default false;
}

