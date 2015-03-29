package com.datazuul.framework.logging;

import org.slf4j.ILoggerFactory;

/**
 * Decorator class to the slf4j logging framework's LoggerFactory.
 * 
 * @author Ralf Eichinger
 * @see org.slf4j.LoggerFactory
 */
public class LoggerFactory {

	// private constructor prevents instantiation
	private LoggerFactory() {
	}

	/**
	 * Return a logger named according to the name parameter using the
	 * statically bound {@link ILoggerFactory} instance.
	 * 
	 * @param name
	 *            The name of the logger.
	 * @return logger
	 */
	public static Logger getLogger(String name) {
		return new Logger(org.slf4j.LoggerFactory.getLogger(name));
	}

	/**
	 * Return a logger named corresponding to the class passed as parameter,
	 * using the statically bound {@link ILoggerFactory} instance.
	 * 
	 * @param clazz
	 *            the returned logger will be named after clazz
	 * @return logger
	 */
	public static Logger getLogger(Class clazz) {
		return new Logger(org.slf4j.LoggerFactory.getLogger(clazz));
	}
}
