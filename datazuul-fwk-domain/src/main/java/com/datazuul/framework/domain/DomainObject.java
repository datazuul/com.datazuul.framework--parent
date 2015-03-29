package com.datazuul.framework.domain;

import java.io.Serializable;

/**
 * @author Ralf Eichinger
 */
public interface DomainObject<K extends Serializable> extends Serializable {
	public K getId();
}
