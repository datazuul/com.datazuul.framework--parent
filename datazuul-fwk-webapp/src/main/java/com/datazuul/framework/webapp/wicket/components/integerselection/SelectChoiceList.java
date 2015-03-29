package com.datazuul.framework.webapp.wicket.components.integerselection;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * List of SelectChoice items.
 *
 * @author Doug Donohoe, Ralf Eichinger
 */
public class SelectChoiceList<T extends SelectChoice<?>> extends ArrayList<T>
		implements IChoiceRenderer<T> {
	/**
	 * Create list with initial list of items.
	 *
	 * @param items
	 *            initial items
	 */
	public SelectChoiceList(T... items) {
		Collections.addAll(this, items);
	}

	/**
	 * Add item to list if not there and then sort. Returns pre-existing item
	 * (if there) or item passed in.
	 *
	 * @param item
	 *            to add to list
	 * @return item added
	 */
	public T addSorted(T item) {
		for (T t : this) {
			if (t.getKey().equals(item.getKey()))
				return t;
		}

		add(item);
		Collections.sort(this);
		return item;
	}

	/**
	 * Get the display value from the SelectChoice (what the user sees)
	 *
	 * @param object
	 *            a SelectChoice object
	 * @return object.getDisplay()
	 */
	public Object getDisplayValue(T object) {
		return object.getDisplay();
	}

	/**
	 * Get key value (what is returned from browser)
	 *
	 * @param object
	 *            a SelectChoice object
	 * @param index
	 *            not used
	 * @return object.getKeyAsString()
	 */
	public String getIdValue(T object, int index) {
		return object.getKeyAsString();
	}
}