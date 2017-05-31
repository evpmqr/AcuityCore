package com.acuity.rs.mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Zach on 5/31/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

    String value();

    boolean setter() default false;
}
