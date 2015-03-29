package com.datazuul.framework.webapp.wicket.markup.html.link;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * @author Ralf Eichinger
 */
public class BookmarkablePageLinkGroup extends BookmarkablePageLink implements MenuGroupMember {
	private final Object menuGroup;

	public BookmarkablePageLinkGroup(final String id, final Object menuGroup, final Class pageClass) {
		super(id, pageClass);
		this.menuGroup = menuGroup;
	}

	@Override
	public Object getMenuGroup() {
		return menuGroup;
	}

}
