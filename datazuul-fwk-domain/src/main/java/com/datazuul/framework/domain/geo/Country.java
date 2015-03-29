package com.datazuul.framework.domain.geo;

import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;

/**
 * Countries with code according to ISO-3166. Units for currency, length,
 * distance and weight.
 * 
 * IMPORTANT: If any unit for a country changes: the data in database has to be
 * migrated (if it is stored unit specific, e.g. distance (because of getting
 * always a round number...)!
 * 
 * @author Ralf Eichinger
 */
public enum Country {
    ANY(null),

    // ABKHAZIA(Continent.ASIA ),
    AFGHANISTAN("AF"), ALAND_ISLANDS("AX"), ALBANIA("AL"), ALGERIA("DZ"), AMERICAN_SAMOA("AS"), ANDORRA("AD"), ANGOLA(
	    "AO"), ANGUILLA("AI"), ANTARCTICA("AQ"), ANTIGUA_AND_BARBUDA("AG"), ARGENTINA("AR"), ARMENIA("AM"), ARUBA(
	    "AW"),
    // ASHMORE_AND_CARTIER_ISLANDS(Continent.AUSTRALIA_OCEANIA ),
    AUSTRALIA("AU"), AUSTRIA("AT", Currency.EUR), AZERBAIJAN("AZ"), BAHAMAS("BS"), BAHRAIN("BH"),
    // BAKER_ISLAND(Continent.AUSTRALIA_OCEANIA ),
    BANGLADESH("BD"), BARBADOS("BB"), BELARUS("BY"), BELGIUM("BE", Currency.EUR), BELIZE("BZ"), BENIN("BJ"), BERMUDA(
	    "BM"), BHUTAN("BT"), BOLIVIA("BO"), BONAIRE("BQ"), BOSNIA_HERZEGOVINA("BA"), BOTSWANA("BW"),
    // BOUVET_ISLAND("BV" ),
    BRAZIL("BR"), BRITISH_INDIAN_OCEAN_TERRITORY("IO"), BRUNEI("BN"), BULGARIA("BG"), BURKINA_FASO("BF"), BURUNDI("BI"), CAMBODIA(
	    "KH"), CAMEROON("CM"), CANADA("CA", Currency.CAD), CAPE_VERDE("CV"), CAYMAN_ISLANDS("KY"), CENTRAL_AFRICAN_REPUBLIC(
	    "CF"), CHAD("TD"), CHILE("CL"), CHINA("CN"), CHRISTMAS_ISLAND("CX"),
    // CLIPPERTON_ISLAND(Continent.NORTH_AMERICA ),
    COCOS_KEELING_ISLANDS("CC"), COLOMBIA("CO"), COMOROS("KM"), CONGO_DEMOCRATIC_REPUBLIC_OF("CD"), CONGO_REPUBLIC_OF(
	    "CG"), COOK_ISLANDS("CK"),
    // CORAL_SEA_ISLANDS(Continent.AUSTRALIA_OCEANIA ),
    COSTA_RICA("CR"), COTE_D_IVOIRE("CI"), CROATIA("HR"), CUBA("CU"), CURACAO("CW"), CYPRUS("CY", Currency.EUR), CZECH_REPUBLIC(
	    "CZ"), DENMARK("DK"), DJIBOUTI("DJ"), DOMINICA("DM"), DOMINICAN_REPUBLIC("DO"), EAST_TIMOR("TL"), ECUADOR(
	    "EC"), EGYPT("EG"), EL_SALVADOR("SV"), EQUATORIAL_GUINEA("GQ"), ERITREA("ER"), ESTONIA("EE", Currency.EUR), ETHIOPIA(
	    "ET"), FALKLAND_ISLANDS("FK"), FAROE_ISLANDS("FO"), FIJI("FJ"), FINLAND("FI", Currency.EUR), FRANCE("FR",
	    Currency.EUR),
    // FRENCH_ANTARCTIC(Continent.ANTARCTICA ),
    FRENCH_GUIANA("GF"), FRENCH_POLYNESIA("PF"), FRENCH_SOUTHERN_TERRITORIES("TF"), GABON("GA"), GAMBIA("GM"), GEORGIA(
	    "GE"), GERMANY("DE", Currency.EUR), GHANA("GH"), GIBRALTAR("GI"), GREECE("GR", Currency.EUR), GREENLAND(
	    "GL"), GRENADA("GD"), GUADELOUPE("GP"), GUAM("GU"), GUATEMALA("GT"), GUERNSEY("GG"), GUINEA("GN"), GUINEA_BISSAU(
	    "GW"), GUYANA("GY"), HAITI("HT"),
    // HAWAII(Continent.AUSTRALIA_OCEANIA ),
    HEARD_AND_MCDONALD_ISLANDS("HM"), HONDURAS("HN"), HONG_KONG("HK"),
    // HOWLAND_ISLAND(Continent.AUSTRALIA_OCEANIA ),
    HUNGARY("HU"), ICELAND("IS"), INDIA("IN"), INDONESIA("ID"), IRAN("IR"), IRAQ("IQ"), IRELAND("IE", Currency.EUR), ISLE_OF_MAN(
	    "IM"), ISRAEL("IL"), ITALY("IT", Currency.EUR), JAMAICA("JM"),
    // JARVIS_ISLAND(Continent.AUSTRALIA_OCEANIA ),
    JAPAN("JP"), JERSEY("JE"),
    // JOHNSTON_ATOLL(Continent.AUSTRALIA_OCEANIA ),
    JORDAN("JO"), KAZAKHSTAN("KZ"), KENYA("KE", Currency.USD, NonSI.INCH, SI.KILOGRAM, SI.KILOMETER),
    // KINGMAN_REEF(Continent.AUSTRALIA_OCEANIA ),
    KIRIBATI("KI"), KOREA_NORTH("KR"), KOREA_SOUTH("KP"), KOSOVO("KV"), // "KV"
									// is
									// not
									// offical!
    KUWAIT("KW"), KYRGYZSTAN("KG"), LAOS("LA"), LATVIA("LV"), LEBANON("LB"), LESOTHO("LS"), LIBERIA("LR"), LIBYA("LY"), LIECHTENSTEIN(
	    "LI"), LITHUANIA("LT"), LUXEMBOURG("LU", Currency.EUR), MACAO("MO"), MACEDONIA("MK"), MADAGASCAR("MG"), MALAWI(
	    "MW"), MALAYSIA("MY"), MALDIVES("MV"), MALI("ML"), MALTA("MT", Currency.EUR), MARSHALL_ISLANDS("MH"), MARTINIQUE(
	    "MQ"), MAURITANIA("MR"), MAURITIUS("MU"), MAYOTTE("YT"), MEXICO("MX"), MICRONESIA("FM"),
    // MIDWAY_ATOLL(Continent.AUSTRALIA_OCEANIA ),
    MOLDOVA("MD"), MONACO("MC"), MONGOLIA("MN"), MONTENEGRO("ME"), MONTSERRAT("MS"), MOROCCO("MA"), MOZAMBIQUE("MZ"), MYANMAR(
	    "MM"), NAMIBIA("NA"), NAURU("NR"),
    // NAVASSA_ISLAND(Continent.NORTH_AMERICA ),
    NEPAL("NP"), NETHERLANDS("NL", Currency.EUR), NETHERLANDS_ANTILLES("AN"), NEW_CALEDONIA("NC"), NEW_ZEALAND("NZ"), NICARAGUA(
	    "NI"), NIGER("NE"), NIGERIA("NG"), NIUE("NU"), NORFOLK_ISLAND("NF"), NORTHERN_MARIANA_ISLANDS("MP"), NORWAY(
	    "NO"), OMAN("OM"), PAKISTAN("PK"), PALAU("PW"), PALESTINE("PS"),
    // PALMYRA_ATOLL(Continent.AUSTRALIA_OCEANIA ),
    PANAMA("PA"), PAPUA_NEW_GUINEA("PG"), PARAGUAY("PY"), PERU("PE"), PHILIPPINES("PH"), PITCAIRN_ISLANDS("PN"), POLAND(
	    "PL"), PORTUGAL("PT", Currency.EUR), PUERTO_RICO("PR"), QATAR("QA"), REUNION("RE"), ROMANIA("RO"), RUSSIAN_FEDERATION(
	    "RU"), RWANDA("RW"),
    // SABA(Continent.NORTH_AMERICA ),
    SAINT_BARTHELEMY("BL"),
    // SAINT_EUSTATIUS(Continent.NORTH_AMERICA ),
    SAINT_HELENA_ASCENSION_TRISTAN_DE_CUNHA("SH"), SAINT_KITTS_AND_NEVIS("KN"), SAINT_LUCIA("LC"), SAINT_MARTIN("MF"), SAINT_PIERRE_AND_MIQUELON(
	    "PM"), SAINT_VINCENT_AND_THE_GRENADINES("VC"), SAMOA("WS"), SAN_MARINO("SM"), SAO_TOME_AND_PRINCIPE("ST"), SAUDI_ARABIA(
	    "SA"), SENEGAL("SN"), SERBIA("RS"), SEYCHELLES("SC"), SIERRA_LEONE("SL"), SINGAPORE("SG"), SINT_MAARTEN(
	    "SX"), SLOVAKIA("SK", Currency.EUR), SLOVENIA("SI", Currency.EUR), SOLOMON_ISLANDS("SB"), SOMALIA("SO"),
    // SOMALILAND(Continent.AFRICA ),
    SOUTH_AFRICA("ZA"), SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS("GS"), SOUTH_SUDAN("SSD"), SPAIN("ES", Currency.EUR), SRI_LANKA(
	    "LK"), SUDAN("SDN"), SURINAME("SR"), SVALBARD_AND_JAN_MAYEN("SJ"), SWAZILAND("SZ"), SWEDEN("SE"), SWITZERLAND(
	    "CH"), SYRIA("SY"), TAIWAN("TW"), TAJIKISTAN("TJ"), TANZANIA("TZ"), THAILAND("TH"), TOGO("TG"), TOKELAU(
	    "TK"), TONGA("TO"), TRINIDAD_AND_TOBAGO("TT"), TUNISIA("TN"), TURKEY("TR"), TURKMENISTAN("TM"), TURKS_AND_CAICOS_ISLANDS(
	    "TC"), TUVALU("TV"), UGANDA("UG"), UKRAINE("UA"), UNITED_ARAB_EMIRATES("AE"), UNITED_KINGDOM("GB",
	    Currency.GBP), UNITED_STATES("US", Currency.USD, NonSI.INCH, NonSI.POUND, NonSI.MILE), UNITED_STATES_MINOR_OUTLYING_ISLANDS(
	    "UM"), URUGUAY("UY"), UZBEKISTAN("UZ"), VANUATU("VU"), VATICAN_CITY("VA"), VENEZUELA("VE"), VIETNAM("VN"), VIRGIN_ISLANDS_BRITISH(
	    "VG"), VIRGIN_ISLANDS_US("VI"),
    // WAKE_ISLAND(Continent.AUSTRALIA_OCEANIA ),
    WALLIS_AND_FUTUNA("WF"), WESTERN_SAHARA("EH"), YEMEN("YE"), ZAMBIA("ZM"), ZIMBABWE("ZW");

    private String countryCode;
    private Unit<Length> unitDistance;
    private Unit<Length> unitLength;
    private Unit<Mass> unitMass;
    private Unit<Money> unitMoney;

    static {
	// Changes the units for output
	UnitFormat.getInstance().label(Currency.CAD, "$");
	UnitFormat.getInstance().label(Currency.EUR, "€");
	UnitFormat.getInstance().label(Currency.GBP, "£");
	UnitFormat.getInstance().label(Currency.USD, "$");

	Currency.setReferenceCurrency(Currency.EUR);
	// exchange rates from 2012/08/08
	Currency.CAD.setExchangeRate(0.81f); // 1 $ = 0.809757 EUR, 1 € =
					     // 1.234983 CAD
	Currency.GBP.setExchangeRate(1.26f); // 1 £ = 1.258717 EUR, 1 € =
					     // 0.794460 GBP
	Currency.USD.setExchangeRate(0.81f); // 1 $ = 0.807456 EUR, 1 € =
					     // 1.238458 USD
    }

    private Country(final String countryCode) {
	this(countryCode, Currency.USD);
    }

    private Country(final String countryCode, final Unit<Money> unitMoney) {
	this(countryCode, unitMoney, SI.CENTIMETER, SI.KILOGRAM, SI.KILOMETER);
    }

    private Country(final String countryCode, final Unit<Money> unitMoney, final Unit<Length> unitLength,
	    final Unit<Mass> unitMass, final Unit<Length> unitDistance) {
	this.countryCode = countryCode;
	this.unitMoney = unitMoney;
	this.unitDistance = unitDistance;
	this.unitLength = unitLength;
	this.unitMass = unitMass;
    }

    /**
     * @return the country code
     */
    public String getCountryCode() {
	return countryCode;
    }

    public Unit<Money> getUnitMoney() {
	return unitMoney;
    }

    /**
     * @return the unitDistance
     */
    public Unit<Length> getUnitDistance() {
	return unitDistance;
    }

    /**
     * @return the unitLength
     */
    public Unit<Length> getUnitLength() {
	return unitLength;
    }

    /**
     * @return the unitMass
     */
    public Unit<Mass> getUnitMass() {
	return unitMass;
    }
}
