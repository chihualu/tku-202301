package org.tku.web.aspect;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    /**
     * Logging for all controller
     */
    @Before("execution(* org.tku.web.controller.*.*(..))")
    public void logController() {
        log.info("before access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
    }


    @After("execution(* org.tku.web.controller.*.*(..))")
    public void logControllerAfter() {
        log.info("after access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
    }

    @Around("execution(* org.tku.web.controller.*.*(..))")
    public Object logControllerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("1 around access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
        Object result = proceedingJoinPoint.proceed();
        log.info("2 around access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
        log.info( "around access time => {}", System.currentTimeMillis() - start);
        return result;
    }
}
