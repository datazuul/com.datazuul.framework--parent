package com.datazuul.framework.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import com.datazuul.framework.util.XMLResourceBundleControl;

public class LocalizedStrings {
    private static final String BASENAME = LocalizedStrings.class.getName();
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    public static String get(final String key, String baseName, final Locale locale) {
	if (Locale.getDefault() != DEFAULT_LOCALE) {
	    Locale.setDefault(DEFAULT_LOCALE);
	}
	if (baseName == null) {
	    baseName = LocalizedStrings.BASENAME;
	}
	final ResourceBundle messages = ResourceBundle.getBundle(baseName, locale, new XMLResourceBundleControl());
	// final ResourceBundle messages =
	// ResourceBundle.getBundle(LocalizedStrings.BASENAME, locale);
	return messages.getString(key);
    }
}
