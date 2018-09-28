package com.lilei.router_anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface RouteNode {
    /**
     * path of one route
     */
    String path() default "default";

    String desc() default "";

    int priority() default -1;
}
