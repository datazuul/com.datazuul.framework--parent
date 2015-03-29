package com.datazuul.framework.domain.geo;

import com.datazuul.framework.domain.AbstractPersistentDomainObject;

public class Subdivision extends AbstractPersistentDomainObject {
    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private Country country;
    private Subdivision parent;

    /**
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(final String code) {
	this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
	this.name = name;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
	return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(final Country country) {
	this.country = country;
    }

    /**
     * @return the parent subdivision
     */
    public Subdivision getParent() {
	return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final Subdivision parent) {
	this.parent = parent;
    }
}
