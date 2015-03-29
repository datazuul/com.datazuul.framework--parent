package com.datazuul.framework.webapp.wicket.markup.html.repeater.datatable;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.model.IModel;

/**
 * A model for checkboxes that represent a more-than-one-selection.
 * 
 * @author Igor Vaynberg
 */
public class CheckBoxModel extends AbstractCheckBoxModel {
	private final IModel<Collection<Serializable>> selection;
	private final Serializable token;

	/**
	 * Constructor
	 * 
	 * @param selection
	 *            model that contains a collection of tokens which will
	 *            represent selection state
	 * @param token
	 *            token whose presence in the collection represents a selection
	 *            state
	 */
	public CheckBoxModel(IModel<Collection<Serializable>> selection,
			Serializable token) {
		super();
		this.selection = selection;
		this.token = token;
	}

	@Override
	public boolean isSelected() {
		return selection.getObject().contains(token);
	}

	@Override
	public void select() {
		selection.getObject().add(token);
	}

	@Override
	public void unselect() {
		selection.getObject().remove(token);
	}

}
