package com.datazuul.framework.webapp.wicket.components.cssmenu;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

/** Lightweight menu object that stores a menu and its label */
public class LabeledMenuItem extends MenuItem implements Serializable {
    private static final long serialVersionUID = 0L;
    private final AbstractLink link;
    private final Label label;

    public LabeledMenuItem(final AbstractLink link, final IModel displayModel) {
	this.link = link;
	if (!link.getId().equals(Constants.LINK_ID)) {
	    throw new IllegalArgumentException();// TODO
	}
	this.label = new Label(Constants.LINK_TEXT_ID, displayModel);
	this.link.add(this.label);
    }

    public LabeledMenuItem(final IModel displayModel) {
	this.link = null;
	this.label = new Label(Constants.LINK_TEXT_ID, displayModel);
    }

    public AbstractLink getLink() {
	return this.link;
    }

    public Label getLabel() {
	return this.label;
    }
}