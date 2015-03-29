package com.datazuul.framework.domain;

import java.util.Locale;

/** 
 * @author Ralf Eichinger
 */
public enum Language {
    ANY(null), CZECH(null), DUTCH(new Locale("nl")), ENGLISH(Locale.UK), FRENCH(Locale.FRENCH), GERMAN(Locale.GERMAN), ITALIAN(
	    Locale.ITALIAN), POLISH(new Locale("pl")), PORTUGUESE(null), RUSSIAN(null), SPANISH(new Locale("es")), OTHER(
	    null), DONT_SAY(null);

    private Locale locale;

    private Language(final Locale locale) {
	this.locale = locale;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
	return locale;
    }

    public static Language getLanguage(Locale locale) {
	Language[] languages = values();
	for (Language language : languages) {
	    if (language.getLocale() != null) {
		if (language.getLocale().equals(locale)) {
		    return language;
		}
	    }
	}
	return null;
    }

    public static Language getLanguage(String languageCode) {
	if (languageCode == null) {
	    return null;
	}
	Language[] languages = values();
	for (Language language : languages) {
	    if (language.getLocale() != null) {
		if (language.getLocale().getLanguage().equalsIgnoreCase(languageCode)) {
		    return language;
		}
	    }
	}
	return null;
    }
}
