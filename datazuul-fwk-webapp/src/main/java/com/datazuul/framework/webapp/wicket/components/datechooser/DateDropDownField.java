package com.datazuul.framework.webapp.wicket.components.datechooser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * @deprecated use DateChooserPanel instead
 * @author Ralf Eichinger
 * @see https
 *      ://rowellbelen.com/content/another-custom-wicket-component-datedropdownfield
 */
@Deprecated
public class DateDropDownField extends FormComponentPanel<Date> {
    private static final long serialVersionUID = -839727513016106766L;

    private int month, day, year;
    private final DropDownChoice<Integer> monthField;
    private final DropDownChoice<Integer> dayField;
    private final DropDownChoice<Integer> yearField;

    public DateDropDownField(final String id, final IModel<Date> model, final int startYear, final int endYear,
	    final boolean required) {
	super(id, model);
	setRequired(required);

	// create month field
	monthField = new DropDownChoice<Integer>("month", new PropertyModel<Integer>(this, "month"), // bind
												     // model
		getMonths(), // list of choices
		new MonthRenderer()); // renderer
	monthField.setLabel(new Model<String>("month"));
	monthField.add(new RangeValidator<Integer>(0, 11)); // add a simple
							    // validator
	monthField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
	    @Override
	    protected void onUpdate(final AjaxRequestTarget target) {
		// change the days dropdown when month changes
		updateChoices(target);
	    }
	});
	// create day field
	dayField = new DropDownChoice<Integer>("day", new PropertyModel<Integer>(this, "day"), getDays());
	dayField.setLabel(new Model<String>("day"));

	// create year field
	yearField = new DropDownChoice<Integer>("year", new PropertyModel<Integer>(this, "year"), getYears(startYear,
		endYear));
	yearField.setLabel(new Model<String>("year"));

	add(monthField);
	add(dayField);
	add(yearField);
    }

    @Override
    public void onBeforeRender() {
	// initialize model
	Date date = getModelObject();
	if (date == null) {
	    date = new Date();
	    setModelObject(date);
	}

	// synchronize main model and drop down components
	final Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	month = cal.get(Calendar.MONTH);
	day = cal.get(Calendar.DAY_OF_MONTH);
	year = cal.get(Calendar.YEAR);

	super.onBeforeRender();
    }

    @Override
    public void convertInput() {
	try {
	    // get values from individual components
	    final int month = monthField.getConvertedInput();
	    final int day = dayField.getConvertedInput();
	    final int year = yearField.getConvertedInput();

	    final Calendar cal = new GregorianCalendar(year, month, 1);
	    final int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    if (day > totalDays) {
		error(new ValidationError().addMessageKey("error.validation.daysOfMonth"));
		return;
	    }

	    // update the model from converted input
	    final Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.DAY_OF_MONTH, day);
	    calendar.set(Calendar.YEAR, year);
	    setConvertedInput(calendar.getTime());
	} catch (final Exception e) {
	    setConvertedInput(null);
	}
    }

    // month choices
    private List<Integer> getMonths() {
	final List<Integer> months = new ArrayList<Integer>(12);

	for (int i = 0; i < 12; i++) {
	    months.add(i);
	}

	return months;
    }

    // day choices
    private List<Integer> getDays() {
	final List<Integer> days = new ArrayList<Integer>();
	final int totalDays = 31;

	// final Date date = getModelObject();
	// if (date != null) {
	// final Calendar cal = Calendar.getInstance();
	// cal.setTime(date);
	// totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// }

	for (int i = 1; i <= totalDays; i++) {
	    days.add(i);
	}
	return days;
    }

    // year choices
    private List<Integer> getYears(int startYear, int endYear) {
	final List<Integer> years = new ArrayList<Integer>();

	final Calendar cal = Calendar.getInstance();

	if (startYear == -1) {
	    startYear = cal.get(Calendar.YEAR) - 2;
	}
	if (endYear == -1) {
	    endYear = cal.get(Calendar.YEAR) + 8;
	}

	for (int i = startYear; i <= endYear; i++) {
	    years.add(i);
	}

	return years;
    }

    // render months as names instead of numbers
    private class MonthRenderer extends ChoiceRenderer<Integer> {
	private static final long serialVersionUID = -9037815586384085976L;

	@Override
	public String getDisplayValue(final Integer object) {
	    final SimpleDateFormat format = new SimpleDateFormat("MMM");
	    final Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.MONTH, object);
	    return format.format(cal.getTime());
	}
    }

    protected void updateChoices(final AjaxRequestTarget target) {
	dayField.setChoices(getDays());
	target.add(dayField);
    }
}