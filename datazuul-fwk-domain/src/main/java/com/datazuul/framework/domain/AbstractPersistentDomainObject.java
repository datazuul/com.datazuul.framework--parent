package com.datazuul.framework.domain;

public abstract class AbstractPersistentDomainObject extends AbstractPersistentObject implements DomainObject<Long> {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * @param id
     *            id of domain object
     */
    public void setId(final Long id) {
	this.id = id;
    }

    @Override
    public Long getId() {
	return id;
    }
}
