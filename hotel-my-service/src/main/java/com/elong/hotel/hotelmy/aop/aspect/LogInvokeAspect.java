package com.elong.hotel.hotelmy.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author bobo
 * @date 2024/11/20 14:17
 **/
@Aspect
@Component
public class LogInvokeAspect {

    public static final Logger LOGGER= LoggerFactory.getLogger("LogInvokeAspect");

    @Before("execution(* com.elong.hotel.hotelmy.bll.SupplierBLL.*(..))")
    @Order(99)
    public void logInvoker(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        Class declaringType = signature.getDeclaringType();
        String declaringTypeName = signature.getDeclaringTypeName();
        LOGGER.info("before joinPoint");
    }

    @Around("@annotation(com.elong.hotel.hotelmy.aop.aspect.LogInvoke)")
    @Order(1)
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("--------------->start process around");
        Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
        Signature signature = proceedingJoinPoint.getSignature();
        Method methodBySignature = getMethodBySignature(proceedingJoinPoint);
        LogInvoke annotation1 = methodBySignature.getAnnotation(LogInvoke.class);
        System.out.println(annotation1.isController());
        Object proceed = proceedingJoinPoint.proceed();
        LOGGER.info("process around");
        return proceed;
    }

    @Before("execution(* com.elong.hotel.hotelmy.bll.SupplierBLL.*(..))")
    @Order(99)
    public void logInvoker2(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//        String name = signature.getName();
//        Class declaringType = signature.getDeclaringType();
//        String declaringTypeName = signature.getDeclaringTypeName();
//        LOGGER.info("before joinPoint order99");
    }


    public Method getMethodBySignature(ProceedingJoinPoint joinPoint){
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())){
                return method;
            }
        }
        return null;
    }
}
