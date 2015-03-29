package com.datazuul.framework.webapp.wicket.components.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class CreditCardExpirationDateFormComponentPanel extends FormComponentPanel<Date> {
    private static final long serialVersionUID = 1L;

    private Integer month = 0;
    private Integer year = 0;

    private DropDownChoice<Integer> monthField;
    private DropDownChoice<Integer> yearField;

    public CreditCardExpirationDateFormComponentPanel(String id, final IModel<Date> model) {
	super(id, model);
	init();
    }

    public CreditCardExpirationDateFormComponentPanel(String id) {
	super(id);
	init();
    }

    private void init() {
	add(monthField = new DropDownChoice<Integer>("monthField", new PropertyModel<Integer>(this, "month"),
		getMonthValues(), new MonthRenderer()));
	monthField.setOutputMarkupId(true);
	monthField.setRequired(true);

	add(yearField = new DropDownChoice<Integer>("yearField", new PropertyModel<Integer>(this, "year"),
		getYearValues(-1, -1), new YearRenderer()));
	yearField.setOutputMarkupId(true);
	yearField.setRequired(true);
    }

    public String getMonthFieldMarkupId() {
	return monthField.getMarkupId();
    }

    public String getYearFieldMarkupId() {
	return yearField.getMarkupId();
    }

    @Override
    protected void onConfigure() {
	super.onConfigure();

	Calendar calendar = Calendar.getInstance();
	month = calendar.get(Calendar.MONTH);
	year = calendar.get(Calendar.YEAR);
    }

    /**
     * @see org.apache.wicket.markup.html.form.FormComponent#convertInput()
     */
    @Override
    protected void convertInput() {
	// note that earlier versions did override updateModel, which looked
	// somewhat better, but wasn't useful for when you want to do
	// validations with either normal validators or form validators
	Integer month = monthField.getConvertedInput();
	Integer year = yearField.getConvertedInput();

	if (month != null && year != null) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    calendar.add(Calendar.MONTH, 1);
	    setConvertedInput(calendar.getTime());
	} else {
	    setConvertedInput(null);
	}
    }

    /**
     * Gets the months.
     * 
     * Note: The first month of the year in the Gregorian and Julian calendars
     * is JANUARY which is 0.
     * 
     * @return the months of a year
     */
    private List<Integer> getMonthValues() {
	final List<Integer> months = new ArrayList<Integer>();
	final int totalMonths = 12;
	for (int i = 0; i < totalMonths; i++) {
	    months.add(i);
	}
	return months;
    }

    /**
     * Gets the years.
     * 
     * @param endYear
     *            newest year in list
     * @param startYear
     *            oldest year in list
     * 
     * @return the years between startYear and endYear (including both)
     */
    private List<Integer> getYearValues(int startYear, int endYear) {
	final List<Integer> years = new ArrayList<Integer>();

	final Calendar cal = Calendar.getInstance();

	if (startYear == -1) {
	    startYear = cal.get(Calendar.YEAR);
	}
	if (endYear == -1) {
	    endYear = cal.get(Calendar.YEAR) + 20;
	    // see http://stackoverflow.com/questions/2500588/maximum-year-in-expry-date-of-credit-card
	    /*
	     * There is no official guideline as the credit card issuers can choose each when the cards
	     * they issue will expire. In fact they have been issuing cards for longer and longer
	     * periods of time. If you're trying to determine how far out into the future you should
	     * accommodate expiration dates for, err on the safe side and give your customers many years
	     * to choose from. That way you future proof your application.
	     * FYI, many credit card issuers do not use the expiration date when determining whether
	     * or not to approve a credit card purchase. So if you're worried about an incorrect date
	     * being provided the processor will ultimately have the final say on whether the transaction
	     * is approved or not so I wouldn't worry about it.
	     */
	}

	for (int i = startYear; i <= endYear; i++) {
	    years.add(i);
	}

	return years;
    }

    // render months as names instead of numbers
    private class MonthRenderer extends ChoiceRenderer<Integer> {
	private static final long serialVersionUID = 1L;

	@Override
	public String getDisplayValue(final Integer month) {
	    // Left padding a String with 2 Zeros:
	    return String.format("%02d", month + 1);
	    // 0 = January
	}

	@Override
	public String getIdValue(final Integer obj, final int index) {
	    return String.valueOf(index);
	}
    }

    // render months as names instead of numbers
    private class YearRenderer extends ChoiceRenderer<Integer> {
	private static final long serialVersionUID = 1L;

	@Override
	public String getDisplayValue(final Integer year) {
	    // only last two digits:
	    String displayValue = String.valueOf(year);
	    return displayValue.substring(displayValue.length() - 2);
	}

	@Override
	public String getIdValue(final Integer obj, final int index) {
	    return String.valueOf(index);
	}
    }
}
