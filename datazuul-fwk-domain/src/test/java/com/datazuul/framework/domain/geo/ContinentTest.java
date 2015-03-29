package com.datazuul.framework.domain.geo;

import org.junit.Assert;
import org.junit.Test;

import com.datazuul.framework.domain.geo.Country;

/**
 * @author Ralf Eichinger
 */
public class ContinentTest {
    @Test
    public void testGetCountries() {
	Country[] countries = Continent.EUROPE.getCountries();
	for (Country country : countries) {
	    String countryCode = country.getCountryCode();
	    System.out.println(countryCode);
	    Assert.assertNotNull(countryCode);
	}
    }
}
