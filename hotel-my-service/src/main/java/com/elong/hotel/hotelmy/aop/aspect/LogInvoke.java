package com.elong.hotel.hotelmy.aop.aspect;

import java.lang.annotation.*;

/**
 * @author bobo
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInvoke {
    boolean isController();
}
