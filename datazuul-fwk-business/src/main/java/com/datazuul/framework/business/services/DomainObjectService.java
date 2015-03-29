package com.datazuul.framework.business.services;

import java.io.Serializable;
import java.util.List;

import com.datazuul.framework.domain.DomainObject;
import com.googlecode.genericdao.search.ISearch;

/**
 * @author Ralf Eichinger
 */
public interface DomainObjectService<K extends Serializable, T extends DomainObject<K>> {
	public long count(ISearch search);

	public Long countAll();

	public boolean delete(T object);

	public List<T> findAll(int offset, int max);

	public T get(K id);

	public boolean save(T object);

	public List<T> search(ISearch search);

	public void update(T object);
}
