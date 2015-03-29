package com.datazuul.framework.webapp.wicket.model;

import java.io.Serializable;

import com.datazuul.framework.business.services.DomainObjectService;
import com.datazuul.framework.domain.DomainObject;

public class EntityModel<ID extends Serializable, T extends DomainObject<ID>> extends AbstractEntityModel<T> {
    private static final long serialVersionUID = 1L;

    private DomainObjectService<ID, T> domainObjectService;

    public EntityModel(T domainObject, DomainObjectService<ID, T> domainObjectService) {
	super(domainObject);
	this.domainObjectService = domainObjectService;
    }

    /**
     * Concrete implementation of the #load() method using services of business layer.
     * @see com.datazuul.framework.webapp.wicket.model.AbstractEntityModel#load(java.lang.Class, java.io.Serializable)
     */
    @Override
    protected T load(Class clazz, Serializable id) {
	return domainObjectService.get((ID) id);
    }
}
