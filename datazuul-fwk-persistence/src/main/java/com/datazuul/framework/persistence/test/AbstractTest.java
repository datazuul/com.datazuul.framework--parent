package com.datazuul.framework.persistence.test;

/**
 * @author Ralf Eichinger
 */

import java.util.ArrayList;
import java.util.Map;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public abstract class AbstractTest extends AbstractJUnit4SpringContextTests {

    protected <T> T getBean(String name, Class<T> requiredType) {
	return (T) applicationContext.getBean(name, requiredType);
    }

    protected <T> T getBean(Class<T> requiredType) {
	Map<String, T> beansOfType = applicationContext.getBeansOfType(requiredType);
	if (beansOfType.size() == 1) {
	    return (T) new ArrayList<T>(beansOfType.values()).get(0);
	}
	return null;
    }
}
