package org.tku.web.aspect;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.tku.web.annotation.Logging;

import java.lang.reflect.ParameterizedType;

@Aspect
@Component
@Log4j2
public class LoggingAnnotationAspect {

    /**
     * Logging for all @Logging annotation
     */
    @Around("@annotation(org.tku.web.annotation.Logging)")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before annotation access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));
        // use joinpoint to get method annotation

        Object result = joinPoint.proceed();
        log.info("after annotation access date time => {}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS"));

//        log.info("logging name => {}", logging.name());
//        log.info("logging value => {}", logging.value());
        return result;
    }
}
