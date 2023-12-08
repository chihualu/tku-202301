package org.tku.web.annotation;

public @interface Logging {
    String value() default "";
    String name() default "";
}
