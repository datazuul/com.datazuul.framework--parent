package com.datazuul.framework.webapp.wicket.authorization.strategies.role;

import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;

import com.datazuul.framework.webapp.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;

/**
 * Compound strategy that works with roles.
 * 
 * @author Eelco Hillenius
 */
public class RoleAuthorizationStrategy extends CompoundAuthorizationStrategy {
    /**
     * Construct.
     * 
     * @param roleCheckingStrategy
     *            the role checking strategy
     */
    public RoleAuthorizationStrategy(final IRoleCheckingStrategy roleCheckingStrategy) {
	//add(new AnnotationsRoleAuthorizationStrategy(roleCheckingStrategy));
	add(new MetaDataRoleAuthorizationStrategy(roleCheckingStrategy));
    }
}
