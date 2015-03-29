package com.datazuul.framework.logging;

/**
 * Decorator over the Logger interface of org.slf4j.Logger. Implementation
 * steps:
 * <ol>
 * <li>implemented the interface for covering all methods.</li>
 * <li>removed "implements" to be able to change to internal Marker class</li>
 * </ol>
 * 
 * @author Ralf Eichinger
 * @see org.slf4j.Logger
 */
public class Logger {
	private org.slf4j.Logger logger;

	protected Logger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(java.lang.String)
	 */
	public void debug(String msg) {
		logger.debug(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
	 */
	public void debug(String format, Object arg) {
		logger.debug(format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
	 */
	public void debug(String format, Object[] argArray) {
		logger.debug(format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
	 */
	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String)
	 */
	public void debug(Marker marker, String msg) {
		logger.debug(marker, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object,
	 * java.lang.Object)
	 */
	public void debug(String format, Object arg1, Object arg2) {
		logger.debug(format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object)
	 */
	public void debug(Marker marker, String format, Object arg) {
		logger.debug(marker, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object[])
	 */
	public void debug(Marker marker, String format, Object[] argArray) {
		logger.debug(marker, format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void debug(Marker marker, String msg, Throwable t) {
		logger.debug(marker, msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		logger.debug(marker, format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(java.lang.String)
	 */
	public void error(String msg) {
		logger.error(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
	 */
	public void error(String format, Object arg) {
		logger.error(format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
	 */
	public void error(String format, Object[] argArray) {
		logger.error(format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
	 */
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String)
	 */
	public void error(Marker marker, String msg) {
		logger.error(marker, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object,
	 * java.lang.Object)
	 */
	public void error(String format, Object arg1, Object arg2) {
		logger.error(format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object)
	 */
	public void error(Marker marker, String format, Object arg) {
		logger.error(marker, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object[])
	 */
	public void error(Marker marker, String format, Object[] argArray) {
		logger.error(marker, format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void error(Marker marker, String msg, Throwable t) {
		logger.error(marker, msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		logger.error(marker, format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#getName()
	 */
	public String getName() {
		return logger.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(java.lang.String)
	 */
	public void info(String msg) {
		logger.info(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
	 */
	public void info(String format, Object arg) {
		logger.info(format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
	 */
	public void info(String format, Object[] argArray) {
		logger.info(format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
	 */
	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String)
	 */
	public void info(Marker marker, String msg) {
		logger.info(marker, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object,
	 * java.lang.Object)
	 */
	public void info(String format, Object arg1, Object arg2) {
		logger.info(format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object)
	 */
	public void info(Marker marker, String format, Object arg) {
		logger.info(marker, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object[])
	 */
	public void info(Marker marker, String format, Object[] argArray) {
		logger.info(marker, format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void info(Marker marker, String msg, Throwable t) {
		logger.info(marker, msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		logger.info(marker, format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isDebugEnabled(org.slf4j.Marker)
	 */
	public boolean isDebugEnabled(Marker marker) {
		return logger.isDebugEnabled(marker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isErrorEnabled(org.slf4j.Marker)
	 */
	public boolean isErrorEnabled(Marker marker) {
		return logger.isErrorEnabled(marker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isInfoEnabled(org.slf4j.Marker)
	 */
	public boolean isInfoEnabled(Marker marker) {
		return logger.isInfoEnabled(marker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isTraceEnabled(org.slf4j.Marker)
	 */
	public boolean isTraceEnabled(Marker marker) {
		return logger.isTraceEnabled(marker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#isWarnEnabled(org.slf4j.Marker)
	 */
	public boolean isWarnEnabled(Marker marker) {
		return logger.isWarnEnabled(marker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(java.lang.String)
	 */
	public void trace(String msg) {
		logger.trace(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
	 */
	public void trace(String format, Object arg) {
		logger.trace(format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
	 */
	public void trace(String format, Object[] argArray) {
		logger.trace(format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
	 */
	public void trace(String msg, Throwable t) {
		logger.trace(msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String)
	 */
	public void trace(Marker marker, String msg) {
		logger.trace(marker, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object,
	 * java.lang.Object)
	 */
	public void trace(String format, Object arg1, Object arg2) {
		logger.trace(format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object)
	 */
	public void trace(Marker marker, String format, Object arg) {
		logger.trace(marker, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object[])
	 */
	public void trace(Marker marker, String format, Object[] argArray) {
		logger.trace(marker, format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void trace(Marker marker, String msg, Throwable t) {
		logger.trace(marker, msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		logger.trace(marker, format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(java.lang.String)
	 */
	public void warn(String msg) {
		logger.warn(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
	 */
	public void warn(String format, Object arg) {
		logger.warn(format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
	 */
	public void warn(String format, Object[] argArray) {
		logger.warn(format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
	 */
	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String)
	 */
	public void warn(Marker marker, String msg) {
		logger.warn(marker, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object,
	 * java.lang.Object)
	 */
	public void warn(String format, Object arg1, Object arg2) {
		logger.warn(format, arg1, arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object)
	 */
	public void warn(Marker marker, String format, Object arg) {
		logger.warn(marker, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
	 * java.lang.Object[])
	 */
	public void warn(Marker marker, String format, Object[] argArray) {
		logger.warn(marker, format, argArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void warn(Marker marker, String msg, Throwable t) {
		logger.warn(marker, msg, t);
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		logger.warn(marker, format, arg1, arg2);
	}
}
