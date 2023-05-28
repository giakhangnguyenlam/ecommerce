package com.khangnlg.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class SpringAOPConfig {

    @Before("execution(* com.khangnlg..*.*(..))")
    public void preHandleMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("Go to method "+signature.getName() + "in class "+signature.getClass());
    }

}
