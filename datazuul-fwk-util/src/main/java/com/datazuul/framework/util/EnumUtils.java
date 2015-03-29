package com.datazuul.framework.util;

public class EnumUtils {
    public final static String getEnumKey(final Enum en) {
	if (en == null) {
	    return "null";
	}
	return en.getClass().getSimpleName() + "." + en.toString();
    }
}
