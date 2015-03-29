package com.datazuul.framework.webapp.wicket.components.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisitor;

import com.datazuul.framework.webapp.wicket.visitor.ShinyFormVisitor;

public class ShinyForm extends Form {
    IVisitor shinyVisitor = new ShinyFormVisitor();

    public ShinyForm(final String id) {
	super(id);
    }

    public ShinyForm(final String id, final IModel model) {
	super(id, model);
    }

    @Override
    public void onBeforeRender() {
	super.onBeforeRender();
	visitChildren(shinyVisitor);
    }
}
