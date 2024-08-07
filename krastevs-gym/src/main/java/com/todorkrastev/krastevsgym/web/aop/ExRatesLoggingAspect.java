package com.todorkrastev.krastevsgym.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExRatesLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExRatesLoggingAspect.class);

    @Pointcut("execution(* com.todorkrastev.krastevsgym.scheduler.ExchangeRateScheduler.*(..)) && @annotation(LogExRatesScheduler)")
    public void logExRatesService() {
    }

    @Before("logExRatesService()")
    public void logExRatesServiceBefore(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        StringBuilder sb = new StringBuilder(className);
        sb.append(".");
        sb.append(methodName);

        LOGGER.info("===================Starting scheduler: {}===================", sb);
    }
}
