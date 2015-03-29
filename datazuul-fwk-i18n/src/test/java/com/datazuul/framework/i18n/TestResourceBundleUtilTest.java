package com.datazuul.framework.i18n;

import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class TestResourceBundleUtilTest {

    @Test
    public final void testGetResourcePath() {

	final String basename = "com/datazuul/framework/i18n/hello-i18n";
	final String extension = ".txt";
	final Locale locale = new Locale("de", "DE");
	final String resourcePath = ResourceBundleUtil.getResourceClassPath(basename, extension, locale);

	Assert.assertEquals("com/datazuul/framework/i18n/hello-i18n_de.txt", resourcePath);
    }

}
