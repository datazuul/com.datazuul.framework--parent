package com.datazuul.framework.webapp.wicket.components.form;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.border.BorderBehavior;
import org.apache.wicket.markup.html.form.FormComponent;

public class RequiredComponentBorder extends BorderBehavior {
    private static final long serialVersionUID = 1L;

    public void renderAfter(final Component component) {
	if (component instanceof FormComponent) {
	    final FormComponent fc = (FormComponent) component;
	    if (fc.isRequired()) {
		super.afterRender(component);
	    }
	}
    }
}
