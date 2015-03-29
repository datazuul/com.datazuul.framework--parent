package com.datazuul.framework.domain.geo;

import com.datazuul.framework.domain.AbstractPersistentDomainObject;

/**
 * <p>
 * <b>Warning (geonames.org):</b> The lat/lng accuracy for Turkey and Indian
 * Postal Index Number (PIN) is not very high, we have been asked to include the
 * data for India in the dump despite this inaccuracies. For Canada we have only
 * the first letters of the full postal codes (for copyright reasons)
 * </p>
 * 
 * <p>
 * The Argentina data file contains 4-digit postal codes which were replaced
 * with a new system in 1999.
 * </p>
 * 
 * @author Ralf Eichinger
 * 
 */
public class GeoLocation extends AbstractPersistentDomainObject {
    private static final long serialVersionUID = 1L;

    private String name; // the city's name (as long as we do not have anything
			 // other types of locations... then a Enum for type
			 // should be defined)
    private String zipcode;
    private Double latitude; // North/South
    private Double longitude; // West/East
    private Integer population; // of 2010
    private Country country;
    private Continent continent;
    private Subdivision subdivision_biggest;
    private Subdivision subdivision_smallest;

    public GeoLocation(final double latitude, final double longitude) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public GeoLocation() {
	super();
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
	this.name = name;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
	return zipcode;
    }

    /**
     * @param zipcode
     *            the zipcode to set
     */
    public void setZipcode(final String zipcode) {
	this.zipcode = zipcode;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
	return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(final Double latitude) {
	this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
	return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(final Double longitude) {
	this.longitude = longitude;
    }

    /**
     * @return the population
     */
    public Integer getPopulation() {
	return population;
    }

    /**
     * @param population
     *            the population to set
     */
    public void setPopulation(final Integer population) {
	this.population = population;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
	return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(final Country country) {
	this.country = country;
    }

    /**
     * @return the subdivision_biggest
     */
    public Subdivision getSubdivision_biggest() {
	return subdivision_biggest;
    }

    /**
     * @param subdivision_biggest
     *            the subdivision_biggest to set
     */
    public void setSubdivision_biggest(final Subdivision subdivision_biggest) {
	this.subdivision_biggest = subdivision_biggest;
    }

    /**
     * @return the subdivision_smallest
     */
    public Subdivision getSubdivision_smallest() {
	return subdivision_smallest;
    }

    /**
     * @param subdivision_smallest
     *            the subdivision_smallest to set
     */
    public void setSubdivision_smallest(final Subdivision subdivision_smallest) {
	this.subdivision_smallest = subdivision_smallest;
    }

    /**
     * @return the continent
     */
    public Continent getContinent() {
	return continent;
    }

    /**
     * @param continent
     *            the continent to set
     */
    public void setContinent(final Continent continent) {
	this.continent = continent;
    }
}
