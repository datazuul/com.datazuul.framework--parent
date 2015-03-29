package com.datazuul.framework.business.services.persistence;

import java.io.Serializable;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.easymock.EasyMock;

import com.datazuul.framework.business.services.DomainObjectService;
import com.datazuul.framework.domain.DomainObject;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * Unit test (persistence layer mocked) for
 * org.wicketstuff.phonebook.business.services.persistence.ContactServiceImpl
 * 
 * @author Ralf Eichinger
 */
public class TestAbstractDomainObjectServiceImpl<K extends Serializable, T extends DomainObject<K>> extends TestCase {
	private GenericDAO<T, K> daoMock = null;
	private DomainObjectService<K, T> service = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		daoMock = EasyMock.createMock(GenericDAO.class);
		service = new AbstractDomainObjectServiceImpl<K, T>(daoMock) {
		};
	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#countAll()}
	 * .
	 */
	public void testCountAll() {
		EasyMock.expect(daoMock.count(null)).andReturn(100);
		EasyMock.replay(daoMock);

		final Long result = service.countAll();

		EasyMock.verify(daoMock);
		Assert.assertEquals(new Long(100), result);
	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#delete(org.wicketstuff.phonebook.domain.DomainObject)}
	 * .
	 */
	//	public void testDelete() {
	//		T domainObject = (T) EasyMock.createMock(DomainObject.class);
	//		daoMock.remove(domainObject);
	//		EasyMock.expectLastCall();
	//		EasyMock.replay(daoMock);
	//
	//		service.delete(domainObject);
	//
	//		EasyMock.verify(daoMock);
	//	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#findAll(int, int)}
	 * .
	 */
	//	public void testFindAll() {
	//		T domainObject = (T) EasyMock.createMock(DomainObject.class);
	//		List<T> domainObjectList = new ArrayList<T>();
	//		for (int i = 0; i < 10; i++) {
	//			domainObjectList.add(domainObject);
	//		}
	//
	//		EasyMock.expect(daoMock.findAll(20, 30)).andReturn(domainObjectList);
	//		EasyMock.replay(daoMock);
	//
	//		List<T> result = service.findAll(20, 30);
	//
	//		EasyMock.verify(daoMock);
	//		Assert.assertEquals(10, result.size());
	//		Assert.assertEquals(domainObjectList, result);
	//	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#get(java.io.Serializable)}
	 * .
	 */
	public void testGet() {
		final K id = (K) new Integer(1);

		// no domain object found
		EasyMock.expect(daoMock.find(id)).andReturn(null);
		EasyMock.replay(daoMock);

		T result = service.get(id);
		Assert.assertNull("No Contact", result);

		// one domain object found
		final T domainObject = (T) EasyMock.createMock(DomainObject.class);
		EasyMock.reset(daoMock);
		EasyMock.expect(daoMock.find(id)).andReturn(domainObject);
		EasyMock.replay(daoMock);

		result = service.get(id);

		EasyMock.verify(daoMock);
		Assert.assertEquals("One Contact", domainObject, result);
	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#save(org.wicketstuff.phonebook.domain.DomainObject)}
	 * .
	 */
	//	public void testSave() {
	//		T domainObject = (T) EasyMock.createMock(DomainObject.class);
	//		daoMock.save(domainObject);
	//		EasyMock.expectLastCall();
	//		EasyMock.replay(daoMock);
	//
	//		service.save(domainObject);
	//
	//		EasyMock.verify(daoMock);
	//	}

	/**
	 * Test method for
	 * {@link de.javapro.framework.business.services.persistence.AbstractDomainObjectServiceImpl#update(org.wicketstuff.phonebook.domain.DomainObject)}
	 * .
	 */
	// public void testUpdate() {
	// T domainObject = (T) EasyMock.createMock(DomainObject.class);
	// daoMock.save(domainObject);
	// EasyMock.expectLastCall();
	// EasyMock.replay(daoMock);
	//
	// service.update(domainObject);
	//
	// EasyMock.verify(daoMock);
	// }

}
