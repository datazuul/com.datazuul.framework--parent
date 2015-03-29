package com.datazuul.framework.webapp.wicket.authorization.strategies.role;

import org.apache.wicket.authorization.IAuthorizationStrategy;

import com.datazuul.framework.domain.authorization.Roles;

/**
 * Base strategy that uses an instance of
 * {@link com.datazuul.framework.webapp.wicket.authorization.strategies.role.role.IRoleCheckingStrategy}.
 * 
 * @author Eelco Hillenius
 */
public abstract class AbstractRoleAuthorizationStrategy implements IAuthorizationStrategy {
    /** Role checking strategy. */
    private final IRoleCheckingStrategy roleCheckingStrategy;

    /**
     * Construct.
     * 
     * @param roleCheckingStrategy
     *            the authorizer delegate
     */
    public AbstractRoleAuthorizationStrategy(IRoleCheckingStrategy roleCheckingStrategy) {
	if (roleCheckingStrategy == null) {
	    throw new IllegalArgumentException("roleCheckingStrategy must be not null");
	}
	this.roleCheckingStrategy = roleCheckingStrategy;
    }

    /**
     * Gets whether any of the given roles applies to the authorizer.
     * 
     * @param roles
     *            the roles
     * @return whether any of the given roles applies to the authorizer
     */
    protected final boolean hasAny(Roles roles) {
	if (roles.isEmpty()) {
	    return true;
	} else {
	    return roleCheckingStrategy.hasAnyRole(roles);
	}
    }

    /**
     * Conducts a check to see if the roles object is empty. Since the roles object does not contain
     * any null values and will always hold an empty string, an extra test is required beyond
     * roles.isEmpty().
     * 
     * @param roles
     *            the Roles object to test
     * @return true if the object holds no real roles
     */
    protected final boolean isEmpty(Roles roles) {
	if (roles.isEmpty()) {
	    return true;
	}

	if (roles.size() == 1) {
	    return "".equals(roles.iterator().next());
	}

	return false;
    }
}