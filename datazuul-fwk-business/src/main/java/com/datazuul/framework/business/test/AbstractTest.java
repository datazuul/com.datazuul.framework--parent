package com.datazuul.framework.business.test;

/**
 * @author Ralf Eichinger
 */

import java.util.ArrayList;
import java.util.Map;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public abstract class AbstractTest extends
		AbstractDependencyInjectionSpringContextTests {

	protected <T> T getBean(String name, Class<T> requiredType) {
		return (T) getApplicationContext().getBean(name, requiredType);
	}

	protected <T> T getBean(Class<T> requiredType) {
		Map beansOfType = getApplicationContext().getBeansOfType(requiredType);
		if (beansOfType.size() == 1) {
			return (T) new ArrayList(beansOfType.values()).get(0);
		}
		return null;
	}
}
