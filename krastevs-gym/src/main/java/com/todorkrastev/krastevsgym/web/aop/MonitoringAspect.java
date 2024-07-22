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
public class MonitoringAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    @Pointcut("@annotation(WarnWhenExecutionExceeds)")
    public void onWarnWhenExecutionExceeds() {
    }

    @Around("onWarnWhenExecutionExceeds() && @annotation(warnWhenExecutionExceeds)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, WarnWhenExecutionExceeds warnWhenExecutionExceeds) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        long threshold = warnWhenExecutionExceeds.timeInMilliseconds();
        if (executionTime >= threshold) {
            LOGGER.warn("===================The method {} was executed in {}ms, which is more than the acceptable threshold of {}ms. Threshold exceeded by {}ms.===================",
                    joinPoint.getSignature(), executionTime, threshold, executionTime - threshold);
        }
        return proceed;
    }
}