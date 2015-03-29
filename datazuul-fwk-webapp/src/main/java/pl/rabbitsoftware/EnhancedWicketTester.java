package pl.rabbitsoftware;

import static junit.framework.Assert.*;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;

public class EnhancedWicketTester {

    protected WicketTester tester;

    public EnhancedWicketTester(WicketTester tester) {
        this.tester = tester;
    }

    public void clickLink(String path) {
        tester.assertComponent(path, Link.class);
        tester.clickLink(path);
    }

    public void clickAjaxLink(String path) {
        tester.assertComponent(path, AjaxFallbackLink.class);
        tester.clickLink(path, true);
    }

    public void clickSubmitLink(String formId, String linkPath) {
        tester.assertComponent(formId + ":" + linkPath, SubmitLink.class);
        FormTester formTester = tester.newFormTester(formId);        
        formTester.submit(linkPath);
    }

    public EnhancedFormTester form(String path) {
        return new EnhancedFormTester(tester, path);
    }

    public void assertEnabled(String path) {
        Component component = tester.getLastRenderedPage().get(path);
        assertTrue("Component is not enabled", component.isEnabled());
    }

    public void assertDisabled(String path) {
        Component component = tester.getLastRenderedPage().get(path);
        assertNotNull("Component does not exists", component);
        assertFalse("Component is not disabled", component.isEnabled());
    }



}
