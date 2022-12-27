package com.codegym.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAOP {
    @Pointcut("execution(* com.codegym.controller.BookController.showList(..))")
    public void callMethod(){}

    @Before("callMethod()")
    public void afterMethod(JoinPoint joinPoint){
        System.out.println("Bạn đang vào phương thức: " + joinPoint.getSignature().getName());
    }
}
