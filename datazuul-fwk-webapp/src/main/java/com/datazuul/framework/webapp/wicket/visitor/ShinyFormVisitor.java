package com.datazuul.framework.webapp.wicket.visitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.datazuul.framework.webapp.wicket.behavior.ErrorHighlightBehavior;

public class ShinyFormVisitor implements IVisitor<Component, Void>, Serializable {
    private static final long serialVersionUID = 1L;

    Set<Component> visited = new HashSet<Component>();

    @Override
    public void component(final Component c, final IVisit visit) {
	if (!visited.contains(c)) {
	    visited.add(c);
	    // c.setComponentBorder(new RequiredComponentBorder());
	    c.add(new ErrorHighlightBehavior());
	    // c.add(new ValidationMsgBehavior());
	}
    }
}
