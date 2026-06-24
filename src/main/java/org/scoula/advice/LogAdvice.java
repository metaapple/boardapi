package org.scoula.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //관점클래스로 지정(로그인/로그아웃/예외처리같은 공통적인 기능을 모아놓은 클래스)
@Component
class LogAdvice {

    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore(){
        System.out.println("=======================================");
    }

    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String))&& args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        System.out.println("str1:" + str1);
        System.out.println("str2:" + str2);
    }

    @AfterThrowing(pointcut= "execution(* org.scoula.sample.service.SampleService*.*(..))", throwing="exception")
    public void logException(Exception exception) {
        System.out.println("Exception...!!!!");
        System.out.println("exception: " + exception);
    }

    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        System.out.println("Target: " + pjp.getTarget());
        System.out.println("Param: " + Arrays.toString(pjp.getArgs()));
        Object result = null;
        try {
            result = pjp.proceed(); // 실제 메서드 호출
        } catch(Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("TIME: " + (end - start));
        return result;
    }
}
