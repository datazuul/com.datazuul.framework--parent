package com.datazuul.framework.domain;

public interface PersistentObject {
    /*
     * see:
     * "Don't Let Hibernate Steal Your Identity"
     * (http://onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html?page=1)
     * varchar(36):
     * http://stackoverflow.com/questions/1908683/storing-uuid-in-hsqldb-database
     */
    public String getUuid();
    public void setUuid(String uuid);

    /*
     * Versioning: see
     * http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html_single/#d0e5844
     * http://turgaykivrak.wordpress.com/2009/05/16/72/
     * http://www.cs.bham.ac.uk/~aps/syllabi/2004_2005/issws/h03/hibernate.html#versioning
     * http://www.intertech.com/Blog/Post/Versioning-Optimistic-Locking-in-Hibernate.aspx
     * 
     * we are using version (not timestamp) here, because on a cluster timestamps
     * could be slightly differ...
     */
    public Integer getVersion();
    public void setVersion(Integer version);
}
