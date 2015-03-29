package com.datazuul.framework.logging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

import com.datazuul.framework.logging.Logger;
import com.datazuul.framework.logging.LoggerFactory;

/**
 * This class represents the Logging-Aspect for logging different aspects like
 * method entry/exit and runtime duration of methods.
 * 
 * @author Ralf Eichinger
 */
public class LoggingInterceptor {
	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	/**
	 * Logs duration of method in milliseconds. Log-level = "INFO".
	 * 
	 * @param call
	 *            called method
	 * @return
	 * @throws Throwable
	 *             thrown if called method throws exception
	 */
	public Object logMethodDuration(final ProceedingJoinPoint call) throws Throwable {
		final StopWatch clock = new StopWatch();
		try {
			clock.start(call.toShortString());
			return call.proceed();
		} finally {
			clock.stop();
			final String name = call.getStaticPart().getSignature().toLongString();
			logger.info("{} duration [ms]: [{}]", name, "" + clock.getTotalTimeMillis());
		}
	}

	/**
	 * Logs name of method and argument values when method entered. Log-level =
	 * "INFO".
	 * 
	 * @param joinPoint
	 *            point of interception
	 */
	public void logMethodEntry(final JoinPoint joinPoint) {
		final Object[] args = joinPoint.getArgs();
		final String name = joinPoint.getSignature().toLongString();
		final StringBuffer sb = new StringBuffer(name + " called with: [");
		for (int i = 0; i < args.length; i++) {
			final Object o = args[i];
			sb.append(o);
			sb.append((i == args.length - 1) ? "]" : ", ");
		}
		logger.info(sb.toString());
	}

	/**
	 * Logs name of method and thrown exception when an exception is thrown of
	 * method. Log-Level = "INFO".
	 * 
	 * @param staticPart
	 *            used to get method signature
	 * @param exc
	 *            thrown exception
	 */
	public void logMethodException(final StaticPart staticPart, final Throwable exc) {
		final String name = staticPart.getSignature().toLongString();
		logger.info("{} throwing exception: [{}]", name, exc.getMessage());
	}

	/**
	 * Logs name of method and returned result when method left. Log-level =
	 * "INFO".
	 * 
	 * @param staticPart
	 *            used to get method signature
	 * @param result
	 *            returned method value
	 */
	public void logMethodExit(final StaticPart staticPart, final Object result) {
		final String name = staticPart.getSignature().toLongString();
		logger.info("{} returning: [{}]", name, result);
	}

}
