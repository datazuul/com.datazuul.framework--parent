package com.datazuul.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Ralf Eichinger
 */
public class RandomUtil {
    /**
     * Creates a random string with specified length. Characters will be chosen
     * from the set of alpha-numeric characters. Generated string will include
     * alphabetic characters and numeric characters.
     * 
     * @param length
     *            specified length of random String
     * @return the random string
     */
    public static String randomPassword(final int length) {
	return RandomStringUtils.random(length, true, true);
    }

    public static Date randomDate(final Integer minAgeInYears, final Integer maxAgeInYears) {
	// code to generate random timestamp between morning 6 to evening 8 pm

	// Calendar cdr = Calendar.getInstance();
	// cdr.set(Calendar.HOUR_OF_DAY, 6);
	// cdr.set(Calendar.MINUTE, 0);
	// cdr.set(Calendar.SECOND, 0);
	// long val1 = cdr.getTimeInMillis();
	//
	// cdr.set(Calendar.HOUR_OF_DAY, 20);
	// cdr.set(Calendar.MINUTE, 0);
	// cdr.set(Calendar.SECOND, 0);
	// long val2 = cdr.getTimeInMillis();
	//
	// Random r = new Random();
	// long randomTS = (long) (r.nextDouble() * (val2 - val1)) + val1;
	// Date d = new Date(randomTS);
	// System.out.println(d.toString());

	final int age = randomInt(minAgeInYears, maxAgeInYears);
	final Calendar now = Calendar.getInstance();
	now.add(Calendar.YEAR, (-1) * age);
	return now.getTime();
    }

    public static <T extends Enum<T>> T randomEnum(final Class<T> en, final Enum<T>... exclusions)
	    throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
	    InvocationTargetException {
	final Method m = en.getMethod("values", null);
	final Enum<T>[] allValues = (Enum<T>[]) m.invoke(null, null);

	List<Enum<T>> values = null;
	if (exclusions == null) {
	    values = Arrays.asList(allValues);
	} else {
	    values = new ArrayList<Enum<T>>();
	    final List<Enum<T>> exclusionsList = Arrays.asList(exclusions);
	    // remove exclusions
	    for (int i = 0; i < allValues.length; i++) {
		final Enum<T> value = allValues[i];
		if (exclusionsList == null || exclusionsList.size() == 0 || !exclusionsList.contains(value)) {
		    values.add(value);
		}
	    }
	}

	final int i = randomInt(0, values.size() - 1);
	final Enum<T> enumVal = values.get(i);
	return Enum.valueOf(en, enumVal.name());
    }

    public static String randomString(final String[] choices) {
	return choices[randomInt(0, choices.length - 1)];
    }

    public static int randomInt(final int min, final int max) {
	return (int) Math.round(Math.random() * (max - min) + min);
    }

    public static long randomLong(final long min, final long max) {
	return (long) Math.round(Math.random() * (max - min) + min);
    }
}
