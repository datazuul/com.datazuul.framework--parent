package com.datazuul.framework.business.services.persistence;

import java.io.Serializable;
import java.util.List;

import com.datazuul.framework.business.services.DomainObjectService;
import com.datazuul.framework.domain.DomainObject;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

/**
 * @author Ralf Eichinger
 */
public abstract class AbstractDomainObjectServiceImpl<K extends Serializable, T extends DomainObject<K>> implements
		DomainObjectService<K, T> {

	protected GenericDAO<T, K> dao;

	/**
	 * Constructor needed for test handing over a mock dao, otherwise Spring is
	 * used to inject implementation.
	 *
	 * @param dao
	 *            the data access interface
	 */
	public AbstractDomainObjectServiceImpl(final GenericDAO<T, K> dao) {
		this.dao = dao;
	}

	@Override
	public long count(final ISearch search) {
		return dao.count(search);
		//		// FIXME: remove the following workaround, when hibernate-generic-dao
		//		// fixed the count
		//		final SearchResult<T> searchResult = dao.searchAndCount(search);
		//		return searchResult.getTotalCount();
	}

	/**
	 * Default constructor needed for Spring...
	 */
	// public AbstractDomainObjectServiceImpl() {
	// }

	@Override
	public Long countAll() {
		return new Long(dao.count(null));
	}

	@Override
	public boolean delete(final T object) {
		return dao.remove(object);
	}

	@Override
	public List<T> findAll(final int offset, final int max) {
		final Search search = new Search();
		search.setFirstResult(offset);
		search.setMaxResults(max);
		return dao.search(search);
	}

	@Override
	public T get(final K id) {
		return dao.find(id);
	}

	@Override
	public boolean save(final T object) {
		return dao.save(object);
	}

	@Override
	public List<T> search(final ISearch search) {
		return dao.search(search);
	}

	@Override
	public void update(final T object) {
		dao.save(object);
	}
}
