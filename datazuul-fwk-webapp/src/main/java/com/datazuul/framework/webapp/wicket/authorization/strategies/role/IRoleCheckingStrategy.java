package com.datazuul.framework.webapp.wicket.authorization.strategies.role;

import com.datazuul.framework.domain.authorization.Roles;

/**
 * Strategy for doing role checking. Normally, an implementation of this strategy interface would
 * look in the current session for credentials that indicate what roles the current user can take
 * on, but any kind of strategy is possible. For example, you could have a role checking strategy
 * that allowed gave users the ADMIN role between 9AM and 5PM.
 * 
 * @author Eelco Hillenius
 * @author Jonathan Locke
 */
public interface IRoleCheckingStrategy {
    /**
     * Whether any of the given roles matches. For example, if a user has role USER and the provided
     * roles are {USER, ADMIN} this method should return true as the user has at least one of the
     * roles that were provided.
     * 
     * @param roles
     *            the roles
     * @return true if a user or whatever subject this implementation wants to work with has at
     *         least on of the provided roles
     */
    boolean hasAnyRole(Roles roles);
}