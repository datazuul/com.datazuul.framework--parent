package com.datazuul.framework.domain.authorization;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Utility class for working with roles.
 * 
 * @author Eelco Hillenius
 * @author Jonathan Locke
 * @author Ralf Eichinger
 */
public final class Roles extends HashSet<String> {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public Roles() {
    }

    /**
     * Construct.
     * 
     * @param roles
     *            Roles as a comma separated list, like "ADMIN, USER"
     */
    public Roles(final String roles) {
	init(roles);
    }

    private void init(final String roles) {
	if (roles != null && !roles.isEmpty()) {
	    for (final String role : roles.split("\\s*,\\s*")) {
		add(role);
	    }
	}
    }

    /**
     * Construct.
     * 
     * @param roles
     *            Roles
     */
    public Roles(final String[] roles) {
	for (final String role : roles) {
	    add(role);
	}
    }

    /**
     * Whether this roles object containes the provided role.
     * 
     * @param role
     *            the role to check
     * @return true if it contains the role, false otherwise
     */
    public boolean hasRole(final String role) {
	if (role != null) {
	    return contains(role);
	}
	return false;
    }

    /**
     * Whether this roles object contains any of the provided roles.
     * 
     * @param roles
     *            the roles to check
     * @return true if it contains any of the roles, false otherwise
     */
    public boolean hasAnyRole(Roles roles) {
	if (roles != null) {
	    for (String role : roles) {
		if (hasRole(role)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Whether this roles object contains all the provided roles.
     * 
     * @param roles
     *            the roles to check
     * @return true if it contains all the roles or the provided roles object is
     *         null, false otherwise
     */
    public boolean hasAllRoles(Roles roles) {
	if (roles != null) {
	    for (String role : roles) {
		if (!hasRole(role)) {
		    return false;
		}
	    }
	}
	return true;
    }

    public String getRoles() {
	return toString();
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(String roles) {
	init(roles);
    }

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	Iterator<String> iterator = this.iterator();
	while (iterator.hasNext()) {
	    String role = (String) iterator.next();
	    sb.append(role).append(",");
	}
	String result = sb.toString();
	if (result.endsWith(",")) {
	    result = result.substring(0, result.lastIndexOf(","));
	}
	return result;
    }
}
