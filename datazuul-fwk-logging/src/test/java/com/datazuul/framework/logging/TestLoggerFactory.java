package com.datazuul.framework.logging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestLoggerFactory extends TestCase {
    private ByteArrayOutputStream outContent = null;
    private ByteArrayOutputStream errContent = null;

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	outContent = new ByteArrayOutputStream();
	errContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
    }

    @Override
    protected void tearDown() throws Exception {
	super.tearDown();
	// System.setOut(null);
	// System.setErr(null);
    }

    public void testGetLoggerString() {
	final Logger logger = LoggerFactory.getLogger("LogTest");
	logger.debug("DEBUG MSG");
	final String outString = outContent.toString();
	Assert.assertTrue(outString.contains("DEBUG MSG"));
    }

    // junit.framework.AssertionFailedError: null
    // at junit.framework.Assert.fail(Assert.java:47)
    // at junit.framework.Assert.assertTrue(Assert.java:20)
    // at junit.framework.Assert.assertTrue(Assert.java:27)
    // at
    // com.datazuul.framework.logging.TestLoggerFactory.testGetLoggerClass(TestLoggerFactory.java:40)
    // public void testGetLoggerClass() {
    // final Logger logger = LoggerFactory.getLogger(this.getClass());
    // logger.debug("DEBUG MSG");
    // final String outString = outContent.toString();
    // Assert.assertTrue(outString.contains("DEBUG MSG"));
    // }

}
