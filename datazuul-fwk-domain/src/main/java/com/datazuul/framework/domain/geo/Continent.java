package com.datazuul.framework.domain.geo;


/**
 * @author ralf
 */
public enum Continent {
    ANY, AFRICA, ANTARCTICA, ASIA, AUSTRALIA_OCEANIA, EUROPE, NORTH_AMERICA, SOUTH_AMERICA;

    private Country[] countries;

    public void setCountries(Country[] countries) {
	this.countries = countries;
    }

    public Country[] getCountries() {
	return this.countries;
    }

    static {
	ANY.setCountries(null);
	AFRICA.setCountries(new Country[] { Country.ALGERIA, Country.ANGOLA, Country.BENIN, Country.BOTSWANA,
		Country.BURKINA_FASO, Country.BURUNDI, Country.CAMEROON, Country.CAPE_VERDE,
		Country.CENTRAL_AFRICAN_REPUBLIC, Country.CHAD, Country.COMOROS, Country.CONGO_DEMOCRATIC_REPUBLIC_OF,
		Country.CONGO_REPUBLIC_OF, Country.COTE_D_IVOIRE, Country.DJIBOUTI, Country.EGYPT,
		Country.EQUATORIAL_GUINEA, Country.ERITREA, Country.ETHIOPIA, Country.GABON, Country.GAMBIA,
		Country.GHANA, Country.GUINEA, Country.GUINEA_BISSAU, Country.KENYA, Country.LESOTHO, Country.LIBERIA,
		Country.LIBYA, Country.MADAGASCAR, Country.MALAWI, Country.MALI, Country.MAURITANIA, Country.MAURITIUS,
		Country.MAYOTTE, Country.MOROCCO, Country.MOZAMBIQUE, Country.NAMIBIA, Country.NIGER, Country.NIGERIA,
		Country.REUNION, Country.RWANDA, Country.SAINT_HELENA_ASCENSION_TRISTAN_DE_CUNHA,
		Country.SAO_TOME_AND_PRINCIPE, Country.SENEGAL, Country.SEYCHELLES,
		Country.SIERRA_LEONE,
		Country.SOMALIA, // Country.SOMALILAND,
		Country.SOUTH_AFRICA, Country.SUDAN, Country.SWAZILAND, Country.TANZANIA, Country.TOGO,
		Country.TUNISIA, Country.UGANDA, Country.WESTERN_SAHARA, Country.ZAMBIA, Country.ZIMBABWE });
	ANTARCTICA.setCountries(new Country[] { Country.ANTARCTICA, // Country.FRENCH_ANTARCTIC,
		Country.HEARD_AND_MCDONALD_ISLANDS, Country.SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS });
	ASIA.setCountries(new Country[] { // Country.ABKHAZIA,
	Country.AFGHANISTAN, Country.ARMENIA, Country.AZERBAIJAN, Country.BAHRAIN, Country.BANGLADESH, Country.BHUTAN,
		Country.BRITISH_INDIAN_OCEAN_TERRITORY, Country.BRUNEI, Country.CAMBODIA, Country.CHINA,
		Country.CHRISTMAS_ISLAND, Country.COCOS_KEELING_ISLANDS, Country.CYPRUS, Country.EAST_TIMOR,
		Country.GEORGIA, Country.HONG_KONG, Country.INDIA, Country.INDONESIA, Country.IRAN, Country.IRAQ,
		Country.ISRAEL, Country.JAPAN, Country.JORDAN, Country.KAZAKHSTAN, Country.KOREA_NORTH,
		Country.KOREA_SOUTH, Country.KUWAIT, Country.KYRGYZSTAN, Country.LAOS, Country.LEBANON, Country.MACAO,
		Country.MALAYSIA, Country.MALDIVES, Country.MONGOLIA, Country.MYANMAR, Country.NEPAL, Country.OMAN,
		Country.PAKISTAN, Country.PALESTINE, Country.PHILIPPINES, Country.QATAR, Country.SAUDI_ARABIA,
		Country.SINGAPORE, Country.SRI_LANKA, Country.SYRIA, Country.TAIWAN, Country.TAJIKISTAN,
		Country.THAILAND, Country.TURKEY, Country.TURKMENISTAN, Country.UNITED_ARAB_EMIRATES,
		Country.UZBEKISTAN, Country.VIETNAM, Country.YEMEN });
	AUSTRALIA_OCEANIA.setCountries(new Country[] {
		Country.AMERICAN_SAMOA,
		Country.AUSTRALIA,
		Country.COOK_ISLANDS,
		Country.FIJI,
		Country.FRENCH_POLYNESIA,
		Country.GUAM, // Country.HAWAII,
		Country.KIRIBATI, Country.MARSHALL_ISLANDS, Country.MICRONESIA, Country.NAURU, Country.NEW_CALEDONIA,
		Country.NEW_ZEALAND, Country.NIUE, Country.NORFOLK_ISLAND, Country.NORTHERN_MARIANA_ISLANDS,
		Country.PALAU, Country.PAPUA_NEW_GUINEA, Country.PITCAIRN_ISLANDS, Country.SAMOA,
		Country.SOLOMON_ISLANDS, Country.TOKELAU, Country.TONGA, Country.TUVALU, Country.VANUATU,
		Country.WALLIS_AND_FUTUNA });
	EUROPE.setCountries(new Country[] { Country.ALAND_ISLANDS, Country.ALBANIA, Country.ANDORRA, Country.AUSTRIA,
		Country.BELARUS, Country.BELGIUM, Country.BOSNIA_HERZEGOVINA, Country.BULGARIA, Country.CROATIA,
		Country.CZECH_REPUBLIC, Country.DENMARK, Country.ESTONIA, Country.FAROE_ISLANDS, Country.FINLAND,
		Country.FRANCE, Country.GERMANY, Country.GIBRALTAR, Country.GREECE, Country.GUERNSEY, Country.HUNGARY,
		Country.ICELAND, Country.IRELAND, Country.ISLE_OF_MAN, Country.ITALY, Country.JERSEY, Country.KOSOVO,
		Country.LATVIA, Country.LIECHTENSTEIN, Country.LITHUANIA, Country.LUXEMBOURG, Country.MACEDONIA,
		Country.MALTA, Country.MOLDOVA, Country.MONACO, Country.MONTENEGRO, Country.NETHERLANDS,
		Country.NORWAY, Country.POLAND, Country.PORTUGAL, Country.ROMANIA, Country.RUSSIAN_FEDERATION,
		Country.SAN_MARINO, Country.SERBIA, Country.SLOVAKIA, Country.SLOVENIA, Country.SPAIN,
		Country.SVALBARD_AND_JAN_MAYEN, Country.SWEDEN, Country.SWITZERLAND, Country.UKRAINE,
		Country.UNITED_KINGDOM, Country.VATICAN_CITY });
	NORTH_AMERICA.setCountries(new Country[] { Country.ANGUILLA, Country.ANTIGUA_AND_BARBUDA, Country.ARUBA,
		Country.BAHAMAS, Country.BARBADOS, Country.BELIZE, Country.BERMUDA, Country.BONAIRE, Country.CANADA,
		Country.CAYMAN_ISLANDS, Country.COSTA_RICA, Country.CUBA, Country.CURACAO, Country.DOMINICA,
		Country.DOMINICAN_REPUBLIC, Country.EL_SALVADOR, Country.GREENLAND, Country.GRENADA,
		Country.GUADELOUPE, Country.GUATEMALA, Country.HAITI, Country.HONDURAS, Country.JAMAICA,
		Country.MARTINIQUE, Country.MEXICO,
		Country.MONTSERRAT,
		Country.NETHERLANDS_ANTILLES,
		Country.NICARAGUA,
		Country.PANAMA,
		Country.PUERTO_RICO,
		// Country.SABA,
		Country.SAINT_BARTHELEMY,
		// Country.SAINT_EUSTATIUS,
		Country.SAINT_KITTS_AND_NEVIS, Country.SAINT_LUCIA, Country.SAINT_MARTIN,
		Country.SAINT_PIERRE_AND_MIQUELON, Country.SAINT_VINCENT_AND_THE_GRENADINES, Country.SINT_MAARTEN,
		Country.TRINIDAD_AND_TOBAGO, Country.TURKS_AND_CAICOS_ISLANDS, Country.UNITED_STATES,
		Country.VIRGIN_ISLANDS_BRITISH, Country.VIRGIN_ISLANDS_US });
	SOUTH_AMERICA.setCountries(new Country[] { Country.ARGENTINA, Country.BOLIVIA, Country.BRAZIL, Country.CHILE,
		Country.COLOMBIA, Country.ECUADOR, Country.FALKLAND_ISLANDS, Country.FRENCH_GUIANA, Country.GUYANA,
		Country.PARAGUAY, Country.PERU, Country.SURINAME, Country.URUGUAY, Country.VENEZUELA });
    }
}
