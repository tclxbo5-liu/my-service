package com.elong.hotel.hotelmy.aop.aspect;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FuseMethod {

    int lockTime3() default 3;
}
