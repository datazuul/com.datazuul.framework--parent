package com.datazuul.framework.webapp.wicket.components.locale;

import java.util.Locale;

/**
 * @author Ralf Eichinger
 */
public class SortableLocale implements Comparable {
    private Locale locale;
    private String label;

    public SortableLocale(Locale locale, String label) {
	this.locale = locale;
	this.label = label;
    }

    @Override
    public int compareTo(Object o) {
	SortableLocale target = (SortableLocale) o;
	int result = getLabel().compareTo(target.getLabel());
	// a negative integer, zero, or a positive integer as
	// this object is less than, equal to, or greater than the specified object
	return result;
    }

    public String getLabel() {
	return this.label;
    }

    public Locale getLocale() {
	return this.locale;
    }
}
