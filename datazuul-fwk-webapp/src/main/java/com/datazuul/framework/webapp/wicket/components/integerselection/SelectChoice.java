package com.datazuul.framework.webapp.wicket.components.integerselection;

import java.io.Serializable;

/**
 * Base class for representing a select list choice.<br/>
 * Represents a user-defined type T and its display value.<br/>
 * Can be sorted via Collections.sort if T implements Comparable.
 *
 * @param <T> class of key
 *
 * @author Doug Donohoe, Ralf Eichinger
 */
public abstract class SelectChoice<T extends Serializable> implements Serializable, Comparable<SelectChoice<T>>
{
    private static final long serialVersionUID = 42L;

    private T key;
    private String display;

    public SelectChoice(T key, String display)
    {
        this.key = key;
        this.display = display;
    }

    /**
     * @return display value
     */
    public String getDisplay()
    {
        return display;
    }

    /**
     * Set the display value
     * @param display value to set
     */
    public void setDisplay(String display)
    {
        this.display = display;
    }

    /**
     * @return key value
     */
    public T getKey()
    {
        return key;
    }

    /**
     * Set the key value
     * @param key value to set
     */
    public void setKey(T key)
    {
        this.key = key;
    }

    /**
     * @return return String representation of the key
     */
    public String getKeyAsString()
    {
        return key.toString();
    }

    /**
     * Return String version of key so {@link org.apache.wicket.Component#getModelObjectAsString()} returns
     * a sensible value.  Useful if storing the value in {@link org.apache.wicket.PageParameters}.
     *
     * @return {@link #getKeyAsString()}
     */
    @Override
    public String toString()
    {
        return getKeyAsString();
    }

    /**
     * Implementation of Comparable.
     *
     * @param o the item to compare
     * @return this.key.compareTo(o.key)
     * @throws UnsupportedOperationException if the underlying key class does not implement Comparable
     */
    @SuppressWarnings({"unchecked"})
    public int compareTo(SelectChoice<T> o)
    {
        if (!(key instanceof Comparable))
        {
            throw new UnsupportedOperationException("Cannot compare non-comparable object: "+ key.getClass().getName());
        }

        Comparable<T> comparableKey = (Comparable<T>) key;

        return comparableKey.compareTo(o.key);
    }
}