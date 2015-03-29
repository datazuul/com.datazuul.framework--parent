package com.datazuul.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * JDK 6's {@link ResourceBundle.Control} subclass that allows loading of
 * bundles in XML format. The bundles are searched first as Java classes, then
 * as properties files (these two methods are the standard search mechanism of
 * ResourceBundle), then as XML properties files. The filename extension of the
 * XML properties files is assumed to be *.properties.xml
 * 
 * see http://bordet.blogspot.de/2007/01/utf-8-handling-for-resourcebundle-and.
 * html
 * 
 * @deprecated use XMLResourceBundleControl instead
 */
@Deprecated
public class ExtendedResourceBundleControl extends ResourceBundle.Control {
    private static final String FORMAT_XML_SUFFIX = "properties.xml";
    private static final String FORMAT_XML = "java." + FORMAT_XML_SUFFIX;
    private static final List<String> FORMATS;
    static {
	final List<String> formats = new ArrayList<String>(FORMAT_DEFAULT);
	formats.add(FORMAT_XML);
	FORMATS = Collections.unmodifiableList(formats);
    }

    @Override
    public List<String> getFormats(final String baseName) {
	return FORMATS;
    }

    @Override
    public ResourceBundle newBundle(final String baseName, final Locale locale, final String format,
	    final ClassLoader loader, final boolean reload) throws IllegalAccessException, InstantiationException,
	    IOException {
	if (!FORMAT_XML.equals(format))
	    return super.newBundle(baseName, locale, format, loader, reload);

	final String bundleName = toBundleName(baseName, locale);
	final String resourceName = toResourceName(bundleName, FORMAT_XML_SUFFIX);
	final URL resourceURL = loader.getResource(resourceName);
	if (resourceURL == null)
	    return null;

	final InputStream stream = getResourceInputStream(resourceURL, reload);

	try {
	    final PropertyXMLResourceBundle result = new PropertyXMLResourceBundle();
	    result.load(stream);
	    return result;
	} finally {
	    stream.close();
	}
    }

    private InputStream getResourceInputStream(final URL resourceURL, final boolean reload) throws IOException {
	if (!reload)
	    return resourceURL.openStream();

	try {
	    // This permission has already been checked by
	    // ClassLoader.getResource(String), which will return null
	    // in case the code has not enough privileges.
	    return AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
		@Override
		public InputStream run() throws IOException {
		    final URLConnection connection = resourceURL.openConnection();
		    connection.setUseCaches(false);
		    return connection.getInputStream();
		}
	    });
	} catch (final PrivilegedActionException x) {
	    throw (IOException) x.getCause();
	}
    }

    /**
     * ResourceBundle that loads definitions from an XML properties file.
     */
    public static class PropertyXMLResourceBundle extends ResourceBundle {
	private final Properties properties = new Properties();

	public void load(final InputStream stream) throws IOException {
	    properties.loadFromXML(stream);
	}

	@Override
	protected Object handleGetObject(final String key) {
	    return properties.getProperty(key);
	}

	@Override
	public Enumeration<String> getKeys() {
	    final Enumeration<Object> keys = properties.keys();
	    return new Enumeration<String>() {
		@Override
		public boolean hasMoreElements() {
		    return keys.hasMoreElements();
		}

		@Override
		public String nextElement() {
		    return (String) keys.nextElement();
		}
	    };
	}
    }
}