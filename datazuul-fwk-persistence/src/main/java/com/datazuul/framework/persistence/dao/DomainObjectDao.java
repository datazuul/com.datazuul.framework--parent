package com.datazuul.framework.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.datazuul.framework.domain.DomainObject;

/**
 * @author Ralf Eichinger
 */
public interface DomainObjectDao<K extends Serializable, T extends DomainObject<K>> {
	public Long countAll();

	public void delete(T o);

	public List<T> findAll(int offset, int max);

	public T get(K id);

	public T getNew();

	public void save(T o);

	public void update(T o);
}