package com.datazuul.framework.webapp.wicket.model;

import com.datazuul.framework.business.services.DomainObjectService;
import com.datazuul.framework.domain.DomainObject;
import java.io.Serializable;

/**
 * @author Ralf Eichinger
 */
public class LoadableDetachableDomainObjectModel<ID extends Serializable> extends AbstractEntityModel {

    private static final long serialVersionUID = 6146601199748875907L;
    private final DomainObjectService domainObjectService;

    public LoadableDetachableDomainObjectModel(final DomainObject domainObject,
            final DomainObjectService domainObjectService) {
        super(domainObject);
        this.domainObjectService = domainObjectService;
    }

    public LoadableDetachableDomainObjectModel(final Class clazz, final Serializable id,
            final DomainObjectService domainObjectService) {
        super(clazz, id);
        this.domainObjectService = domainObjectService;
    }

    @Override
    protected DomainObject load(Class clazz, Serializable id) {
        return domainObjectService.get(id);
    }
}
