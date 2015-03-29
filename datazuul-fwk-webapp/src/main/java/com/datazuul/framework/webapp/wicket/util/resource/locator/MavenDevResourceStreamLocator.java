package com.datazuul.framework.webapp.wicket.util.resource.locator;

import org.apache.wicket.core.util.resource.locator.ResourceStreamLocator;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Locator for development: always uses actual (HTML-)resources, no need to redeploy!
 * (gets resources from "src" directory and not from "target)
 * 
 * Usage: in Application
 * if (DEVELOPMENT.equalsIgnoreCase(configurationType))
 * {
 *   getResourceSettings().setResourceStreamLocator(new MavenDevResourceStreamLocator());
 * }
 *
 * @author Michael Mosmann (michael@mosmann.de)
 */
public class MavenDevResourceStreamLocator extends ResourceStreamLocator {

    private static final Logger _logger = LoggerFactory
            .getLogger(MavenDevResourceStreamLocator.class);
    String _prefix = "src/main/resources/";

    @Override
    public IResourceStream locate(Class<?> clazz, String path) {
        IResourceStream located = getFileSysResourceStream(path);
        if (located != null) {
            return located;
        }
        return super.locate(clazz, path);
    }

    private IResourceStream getFileSysResourceStream(String path) {
        File f = new File(_prefix + path);
        if ((f.exists()) && (f.isFile())) {
            _logger.info("Read File: {}", f);
            return new FileResourceStream(f);
            // } else {
            // _logger.info("Missing File: {}", f);
        }

        return null;
    }
}