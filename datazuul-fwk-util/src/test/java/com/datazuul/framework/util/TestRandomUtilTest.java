package com.datazuul.framework.util;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

public class TestRandomUtilTest {

    @Test
    public void testRandomDate() {
	// test 1
	Date randomDate = RandomUtil.randomDate(1, 2);
	Assert.assertTrue(randomDate.before(new Date()));

	// test 2
	final Calendar oneYearBeforeNow = Calendar.getInstance();
	oneYearBeforeNow.add(Calendar.YEAR, -1);
	final Calendar sixYearsBeforeNow = Calendar.getInstance();
	sixYearsBeforeNow.add(Calendar.YEAR, -6);
	randomDate = RandomUtil.randomDate(2, 5);
	Assert.assertTrue(randomDate.before(oneYearBeforeNow.getTime())
		&& randomDate.after(sixYearsBeforeNow.getTime()));
    }

    @Test
    @Ignore
    public void testRandomEnum() {
	fail("Not yet implemented");
    }

    @Test
    @Ignore
    public void testRandomString() {
	fail("Not yet implemented");
    }

    @Test
    @Ignore
    public void testRandomInt() {
	fail("Not yet implemented");
    }

}
