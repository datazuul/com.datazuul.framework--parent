package com.datazuul.framework.webapp.wicket.markup.html.link;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * @author Ralf Eichinger
 */
public abstract class MenuGroupLink extends Link implements MenuGroupMember {
    private final Object menuGroup;
    private final Class pageClass;

    public MenuGroupLink(final String id, final Object menuGroup, final Class pageClass) {
	this(id, null, menuGroup, pageClass);
    }
    public MenuGroupLink(final String id, IModel model, final Object menuGroup, final Class pageClass) {
	super(id, model);
	this.menuGroup = menuGroup;
	this.pageClass = pageClass;
    }

    @Override
    public Object getMenuGroup() {
	return menuGroup;
    }

    public Class getPageClass() {
	return pageClass;
    }

}
