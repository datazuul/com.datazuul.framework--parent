package com.datazuul.framework.webapp.wicket.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.FormComponent;

public class ValidationMsgBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    public void onRendered(final Component c) {
        if (c instanceof FormComponent) {
            final FormComponent fc = (FormComponent) c;
            if (!fc.isValid()) {
                String error;
                if (fc.hasFeedbackMessage()) {
                    error = fc.getFeedbackMessages().first(FeedbackMessage.ERROR).getMessage().toString();
                } else {
                    error = "Your input is invalid.";
                }
                fc.getResponse().write("<div class=\"validationMsg\">" + error + "</div>");
            }
        }
    }
}
