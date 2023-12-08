package org.tku.web.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    /** Logging before Logging annotation
     * @param joinPoint
     */
    @Before("@annotation(org.tku.web.annotation.Logging)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName());
    }


    /** AOP around controller method
     */
     @Around("execution(* org.tku.web.controller.*.*(..))")
     public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
         log.info("Around: " + joinPoint.getSignature().getName());
         return joinPoint.proceed();
     }
}
