package org.bosco.jdk.java8.annotation;

import java.lang.annotation.*;


@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {

    boolean includeName() default true;

}
