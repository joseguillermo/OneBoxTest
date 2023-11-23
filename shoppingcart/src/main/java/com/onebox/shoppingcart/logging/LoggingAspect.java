package com.onebox.shoppingcart.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

   private final Logger log = LoggerFactory.getLogger(this.getClass());

   @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
   public void restController() {}

   @Around("restController()")
   public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("Request: {}", joinPoint.getSignature().getName());
      Object result = joinPoint.proceed();
      if (result != null) {
         log.info("Response: {}", result.toString());
      } else {
         log.info("Response: null");
      }
      return result;
    }
}
