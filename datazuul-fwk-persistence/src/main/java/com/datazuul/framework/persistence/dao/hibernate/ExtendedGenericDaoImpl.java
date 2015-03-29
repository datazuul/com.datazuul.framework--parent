/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datazuul.framework.persistence.dao.hibernate;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ralf
 */
public class ExtendedGenericDaoImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedGenericDaoImpl.class);

    @Override
    public boolean save(T entity) {
        // see http://www.stevideter.com/2008/12/07/saveorupdate-versus-merge-in-hibernate/
        try {
            super.save(entity);
            return true;
        } catch (Exception exception) {
            LOGGER.warn("hibernate save or update failed, trying merge!: " + exception.getMessage());
            getSession().merge(entity);
            return true;
        }
    }
}
