package com.datazuul.framework.webapp.wicket.components.cssmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem implements Serializable {
    private static final long serialVersionUID = 0L;
    private boolean topLevel = true;
    private final List<MenuItem> subMenuItems = new ArrayList<MenuItem>();

    /** Add one menu item */
    public void addMenuItem(final MenuItem menu) {
	this.subMenuItems.add(menu);
	menu.setTopLevel(false);
    }

    public List<MenuItem> getChildren() {
	return this.subMenuItems;
    }

    public boolean isTopLevel() {
	return this.topLevel;
    }

    private void setTopLevel(final boolean topLevel) {
	this.topLevel = topLevel;
    }
}
