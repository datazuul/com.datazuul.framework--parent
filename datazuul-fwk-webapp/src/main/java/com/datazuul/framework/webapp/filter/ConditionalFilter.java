package com.datazuul.framework.webapp.filter;

import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ConditionalFilter implements Filter {

	// are we to ignore the wrapped filter?
	private boolean _ignore = true;

	// instance of the actual filter being wrapped
	private Filter _wrappedFilter;

	public ConditionalFilter() {
	} // constructor

	/*
	 * looks up the configured 'condition' via JNDI to determine whether or not
	 * the wrapped filter is active
	 */
	private boolean checkCondition(final String conditions) {
		boolean result = false;

		final StringTokenizer st1 = new StringTokenizer(conditions, "|");
		while (st1.hasMoreTokens()) {
			final String condition = st1.nextToken();

			final StringTokenizer st2 = new StringTokenizer(condition, "=");
			final String key = st2.nextToken();
			final String value = st2.nextToken();

			final String propertyValue = System.getProperty(key);
			if (value.equals(propertyValue)) {
				result = true;
				break;
			}
		}
		System.out.println("conditions '" + conditions + "' matched: " + result);
		return result;
	} // checkCondition()

	@Override
	public void destroy() {
		if (_ignore) {
			_wrappedFilter.destroy();
		}
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		if (!_ignore) {
			// the wrapped filter is active so we let it do its work
			_wrappedFilter.doFilter(request, response, filterChain);
		} else {
			// wrapped filter is inactive so simply move on to the next filter
			filterChain.doFilter(request, response);
		}
	}

	private Filter getFilterInstance(final String className) throws ClassNotFoundException, InvalidClassException,
			InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
		// try to create an instance of the wrapped filter with the given class
		// name
		final Class filterClass = Class.forName(className);
		final java.lang.reflect.Constructor constructor = filterClass.getConstructor();
		final Object filter = constructor.newInstance();

		if (!(filter instanceof Filter)) {
			throw new InvalidClassException(String.format("'%s' is not an instance of Filter", className));
		}
		return (Filter) filter;
	} // getFilterInstance()

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		// the 'condition' init param tells us whether or not the wrapped filter
		// is active
		_ignore = !checkCondition(filterConfig.getInitParameter("condition"));

		try {
			if (!_ignore) {
				// the wrapped filter is active so we create an instance of it
				// and initialize it
				_wrappedFilter = getFilterInstance(filterConfig.getInitParameter("wrapped-class"));
				_wrappedFilter.init(filterConfig);
			}
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}

} // class ConditionalFilter
