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
public class ActivityLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLoggingAspect.class);

    @Pointcut("execution(* com.todorkrastev.krastevsgym.service.ActivityService.*(..)) && @annotation(LogActivityExecution)")
    public void logActivityExecution() {
    }

    @Around("logActivityExecution()")
    public Object logActivityStartAndEnd(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        LOGGER.info("===================Starting ActivityService method: {}===================", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        LOGGER.info("===================ActivityService method {} completed in {}ms===================", joinPoint.getSignature(), (endTime - startTime));

        return result;
    }
}