package com.datazuul.framework.webapp.wicket.visitor;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.datazuul.framework.webapp.wicket.markup.html.link.MenuGroupMember;

public class BookmarkablePageLinkVisitor implements IVisitor<BookmarkablePageLink, Void> {

    @Override
    public void component(final BookmarkablePageLink link, final IVisit visit) {
	if (MenuGroupMember.class.isAssignableFrom(link.getPage().getClass())
		&& MenuGroupMember.class.isAssignableFrom(link.getClass())) {
	    final MenuGroupMember menuMemberCurrentPage = (MenuGroupMember) link.getPage();
	    final MenuGroupMember menuMemberLink = (MenuGroupMember) link;
	    if (menuMemberCurrentPage.getMenuGroup() == menuMemberLink.getMenuGroup()) {
		link.add(new AttributeModifier("class", "current"));
	    }
	} else if (link.getPage().getPageClass().equals(link.getPageClass())) {
	    link.add(new AttributeModifier("class", "current"));
	}
    }
}