package com.elong.hotel.hotelmy.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author bobo
 * @date 2024/12/19 19:35
 **/
@Aspect
@Component
public class FuseAspect {


    @Around("@annotation(com.elong.hotel.hotelmy.aop.aspect.FuseMethod)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer){
                
            }
        }
        System.out.println("FuseAspect invoke");
        return joinPoint.proceed();
    }
}
