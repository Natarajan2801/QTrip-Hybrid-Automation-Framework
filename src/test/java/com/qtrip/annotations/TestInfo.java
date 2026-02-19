package com.qtrip.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to mark test methods with metadata.
 * Useful for reporting, filtering, and documentation.
 *
 * @author Natarajan M
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

    /**
     * Test case ID from test management tool.
     */
    String testId() default "";

    /**
     * Test description.
     */
    String description() default "";

    /**
     * Test author.
     */
    String author() default "";

    /**
     * Test category (Smoke, Regression, Sanity).
     */
    String category() default "Regression";

    /**
     * Priority (P0, P1, P2, P3).
     */
    String priority() default "P2";

    /**
     * Related JIRA ticket.
     */
    String jiraTicket() default "";

    /**
     * Feature being tested.
     */
    String feature() default "";

    /**
     * Whether test is automated.
     */
    boolean automated() default true;
}

