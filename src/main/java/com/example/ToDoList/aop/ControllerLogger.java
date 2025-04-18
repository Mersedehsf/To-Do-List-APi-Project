package com.example.ToDoList.aop;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogger.class);

    private ObjectMapper objectMapper = new ObjectMapper();


    @Pointcut("execution(* com.example.ToDoList.controller.AbstractController+.*(..))")
    public void controllerMethods() {

    }

    @Around("controllerMethods()")
    public Object executeRestMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LOGGER.info("Method " + proceedingJoinPoint.getSignature() + " gets called with paramethers " + objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed();
            LOGGER.info("Method " + signature.getName() + " executed with return object " + objectMapper.writeValueAsString(proceed));
            return proceed;

        } catch (Exception e) {
            LOGGER.error("Exception occurred in method " + signature.getName() + " and stack trace is " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }
}
