package com.datazuul.framework.logging.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author Ralf Eichinger
 */
public class LoggingInterceptorSpring implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
    private static Logger LOGGER = null;

    /**
     * To be fired after the target method is executed.
     * 
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang
     *      .Object, java.lang.reflect.Method, java.lang.Object[],
     *      java.lang.Object)
     */
    @Override
    public void afterReturning(final Object arg0, final Method arg1, final Object[] arg2, final Object arg3)
	    throws Throwable {
	LOGGER = LoggerFactory.getLogger(arg3.getClass());
	LOGGER.info("Ending method: " + arg1.getName());
    }

    /**
     * The code inside the before method gets executed before the target method
     * is executed
     * 
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method
     *      , java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(final Method arg0, final Object[] arg1, final Object arg2) throws Throwable {
	LOGGER = LoggerFactory.getLogger(arg2.getClass());
	LOGGER.info("Beginning method: " + arg0.getName());
    }

    /**
     * To be fired when the target method throws an exception.
     * 
     * @param m
     *            method
     * @param args
     *            method's arguments
     * @param target
     *            target class
     * @param ex
     *            thrown exception
     */
    public void afterThrowing(final Method m, final Object[] args, final Object target, final Throwable ex) {
	LOGGER = LoggerFactory.getLogger(target.getClass());
	LOGGER.info("Exception in method: " + m.getName() + " Exception is: " + ex.getMessage());
    }
}
