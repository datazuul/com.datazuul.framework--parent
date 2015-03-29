package com.datazuul.framework.domain;

public abstract class AbstractPersistentObject implements PersistentObject {
    private String uuid = UUIDGenerator.createId();
    private Integer version;

    @Override
    public String getUuid() {
	return uuid;
    }

    @Override
    public void setUuid(String uuid) {
	this.uuid = uuid;
    }

    @Override
    public Integer getVersion() {
	return version;
    }

    @Override
    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || !(o instanceof PersistentObject)) {

	    return false;
	}

	PersistentObject other = (PersistentObject) o;

	// if the id is missing, return false
	if (getUuid() == null)
	    return false;

	// equivalence by id
	return getUuid().equals(other.getUuid());
    }

    @Override
    public int hashCode() {
	if (getUuid() != null) {
	    return getUuid().hashCode();
	} else {
	    return super.hashCode();
	}
    }

    @Override
    public String toString() {
	return this.getClass().getName() + "[uuid=" + getUuid() + "]";
    }
}
