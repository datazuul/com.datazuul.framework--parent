package com.datazuul.framework.persistence.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.datazuul.framework.domain.DomainObject;
import com.datazuul.framework.persistence.dao.DomainObjectDao;

/**
 * @author Ralf Eichinger
 */
public abstract class AbstractDomainObjectDaoImpl<K extends Serializable, T extends DomainObject<K>> implements
        DomainObjectDao<K, T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDomainObjectDaoImpl.class);
    private final Class<T> _domainClass;
    private SessionFactory _sessionFactory;

    protected AbstractDomainObjectDaoImpl(final Class<T> domainClass) {
        _domainClass = domainClass;
    }

    @Override
    public Long countAll() {
        final Criteria criteria = getSession().createCriteria(_domainClass);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public void delete(final T object) {
        getSession().delete(object);
    }

    @Override
    public List<T> findAll(final int offset, final int max) {
        final Criteria criteria = getSession().createCriteria(_domainClass);
        if (offset != 0) {
            criteria.setFirstResult(offset);
        }
        if (max != 0) {
            criteria.setMaxResults(max);
        }
        return criteria.list();
    }

    @Override
    public T get(final K id) {
        return (T) getSession().get(_domainClass, id);
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(_domainClass);
    }

    protected Class<T> getDomainClass() {
        return _domainClass;
    }

    @Override
    public T getNew() {
        try {
            return _domainClass.newInstance();
        } catch (final InstantiationException e) {
            LOGGER.error("newInstance failed", e);
        } catch (final IllegalAccessException e) {
            LOGGER.error("newInstance failed", e);
        }
        return null;
    }

    public Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return _sessionFactory;
    }

    @Override
    @Transactional
    public void save(final T object) {
        // see http://www.stevideter.com/2008/12/07/saveorupdate-versus-merge-in-hibernate/
        try {
            getSession().saveOrUpdate(object);
        } catch (Exception exception) {
            LOGGER.warn("hibernate save or update failed, trying merge!");
            getSession().merge(object);
        }
    }

    public void setSessionFactory(final SessionFactory sessionFactory) {
        _sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void update(final T object) {
        getSession().update(object);
    }
}
