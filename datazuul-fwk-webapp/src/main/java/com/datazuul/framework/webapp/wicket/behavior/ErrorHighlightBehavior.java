package com.datazuul.framework.webapp.wicket.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

public class ErrorHighlightBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void onComponentTag(final Component c, final ComponentTag tag) {
        if (c instanceof FormComponent) {
            final FormComponent fc = (FormComponent) c;
            if (!fc.isValid()) {
                final String attribute = tag.getAttribute("class");
                if (attribute != null) {
                    tag.put("class", attribute + " error");
                } else {
                    tag.put("class", "error");
                }
            }
        }
    }
}
