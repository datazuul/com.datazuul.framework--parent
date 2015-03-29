package com.datazuul.framework.webapp.wicket.components.locale;

import java.util.Locale;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Ralf Eichinger
 */
public class LocaleChoiceRenderer extends ChoiceRenderer<Locale> {
	private PropertyModel<Locale> model;

	/**
	 * Constructor.
	 */
	public LocaleChoiceRenderer(PropertyModel<Locale> model) {
		super();
		this.model = model;
	}

	/**
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(Object)
	 */
	@Override
	public Object getDisplayValue(Locale locale) {
		String display = locale.getDisplayName(model.getObject());
		return display;
	}
}
