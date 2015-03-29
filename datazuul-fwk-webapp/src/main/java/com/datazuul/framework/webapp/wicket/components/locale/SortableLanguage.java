package com.datazuul.framework.webapp.wicket.components.locale;

import com.datazuul.framework.domain.Language;

/**
 * @author Ralf Eichinger
 */
public class SortableLanguage implements Comparable {
    private Language language;
    private String label;

    public SortableLanguage(Language language, String label) {
	this.language = language;
	this.label = label;
    }

    @Override
    public int compareTo(Object o) {
	SortableLanguage target = (SortableLanguage) o;
	int result = getLabel().compareTo(target.getLabel());
	// a negative integer, zero, or a positive integer as
	// this object is less than, equal to, or greater than the specified object
	return result;
    }

    public String getLabel() {
	return this.label;
    }

    public Language getLanguage() {
	return this.language;
    }
}
