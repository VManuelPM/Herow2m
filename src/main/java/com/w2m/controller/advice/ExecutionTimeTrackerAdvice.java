package com.w2m.controller.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

  final Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

  @Around("@annotation(com.w2m.controller.advice.TrackExecutionTime)")
  public Object trackTime(ProceedingJoinPoint point) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object obj = point.proceed();
    long endTime = System.currentTimeMillis();
    logger.info(
        "Method name {} time taken to execute {} milliseconds",
        point.getSignature(), (endTime - startTime));
    return obj;
  }
}
