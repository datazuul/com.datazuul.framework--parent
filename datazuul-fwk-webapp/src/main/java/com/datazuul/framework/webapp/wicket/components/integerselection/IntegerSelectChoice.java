package com.datazuul.framework.webapp.wicket.components.integerselection;

/**
 * Integer select choice
 *
 * @author Doug Donohoe, Ralf Eichinger
 */
public class IntegerSelectChoice extends SelectChoice<Integer> {
	private static final long serialVersionUID = 42L;

	public IntegerSelectChoice(Integer key) {
		super(key, key.toString());
	}

	public IntegerSelectChoice(Integer key, String display) {
		super(key, display);
	}
}
