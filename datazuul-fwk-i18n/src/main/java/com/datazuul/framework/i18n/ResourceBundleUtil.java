package com.datazuul.framework.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.LocalizedResourceHelper;

/**
 * @author Ralf Eichinger
 */
public class ResourceBundleUtil {
    /**
     * Get the classpath for a localized resource using the lookup algorithm of
     * {@link ResourceBundle}.
     * 
     * @param basename
     *            e.g. "com/datazuul/framework/i18n/hello-i18n"
     * @param extension
     *            e.g. ".txt"
     * @param locale
     *            the locale to be used for looking up the localized resource
     * @return the classpath to the localized resource
     */
    public static String getResourceClassPath(final String basename, final String extension, final Locale locale) {
	final LocalizedResourceHelper localizedResourceHelper = new LocalizedResourceHelper();
	final ClassPathResource localizedResource = (ClassPathResource) localizedResourceHelper.findLocalizedResource(
		basename, extension, locale);
	String resourcePath = basename + extension;
	resourcePath = localizedResource.getPath();

	return resourcePath;
    }
}
