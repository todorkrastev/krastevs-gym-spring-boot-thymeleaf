package com.todorkrastev.krastevsgym.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegisterLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterLoggingAspect.class);

    @Pointcut("execution(* com.todorkrastev.krastevsgym..*(..)) && @annotation(LogRegisterExecution)")
    public void logRegisterExecution() {
    }

    @Around("logRegisterExecution()")
    public Object logRegisterStartAndEnd(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        LOGGER.info("===================Starting Register method: {}===================", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        LOGGER.info("===================Register method {} completed in {}ms===================", joinPoint.getSignature(), (endTime - startTime));

        return result;
    }
}