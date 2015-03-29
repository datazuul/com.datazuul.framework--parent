package com.datazuul.framework.webapp.wicket.model;

import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class clazz, Serializable id) {
	super("Entity of type" + clazz.getSimpleName() + " and id " + id + " not found.");
    }

}
