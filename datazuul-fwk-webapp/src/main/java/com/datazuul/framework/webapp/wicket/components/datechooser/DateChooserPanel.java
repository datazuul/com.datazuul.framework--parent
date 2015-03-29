package com.datazuul.framework.webapp.wicket.components.datechooser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Works on a {@link java.util.Date} object. Displays three DropDownChoice
 * fields:
 * <ul>
 * <li>day: integer from 1 to max. days of month</li>
 * <li>month: integer from 0 to max. months of year - 1 (0 - 11)</li>
 * <li>year: integer from given startYear to given endYear</li>
 * </ul>
 *
 * @author Ralf Eichinger
 */
public class DateChooserPanel<T extends Date> extends FormComponentPanel<Date> {

    private static final long serialVersionUID = 1L;
    /**
     * The day ddc.
     */
    private DropDownChoice<Integer> dayDDC;
    /**
     * The month ddc.
     */
    private DropDownChoice<Integer> monthDDC;
    /**
     * The year ddc.
     */
    private DropDownChoice<Integer> yearDDC;
    protected Locale locale;

    public DateChooserPanel(final String id, final int startYear, final int endYear, final Locale locale) {
        super(id);
        createComponent(startYear, endYear, locale);
    }

    public DateChooserPanel(final String id, final IModel<Date> model, final int startYear, final int endYear,
            final Locale locale) {
        super(id, model);
        createComponent(startYear, endYear, locale);
    }

    private void createComponent(final int startYear, final int endYear, final Locale locale) {
        this.locale = locale;

        dayDDC = new DropDownChoice<Integer>("day", new Model<Integer>(), getDayValues());
        dayDDC.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                getFormComponent().setDefaultModelObject(getFormComponent().getConvertedInput());
            }
        });
        dayDDC.setOutputMarkupId(true);
        add(dayDDC);

        monthDDC = new DropDownChoice<Integer>("month", new Model<Integer>(), getMonthValues(), new MonthRenderer());
        monthDDC.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                getFormComponent().setDefaultModelObject(getFormComponent().getConvertedInput());
                // change the days dropdown when month changes
                updateDayValues(target);
            }
        });
        monthDDC.setOutputMarkupId(true);
        add(monthDDC);

        yearDDC = new DropDownChoice<Integer>("year", new Model<Integer>(), getYearValues(startYear, endYear));
        yearDDC.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                getFormComponent().setDefaultModelObject(getFormComponent().getConvertedInput());
                // change the days dropdown when year changes
                updateDayValues(target);
            }
        });
        yearDDC.setOutputMarkupId(true);
        add(yearDDC);

        add(new IValidator<Date>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void validate(final IValidatable<Date> validatable) {
                final Integer day = dayDDC.getConvertedInput();
                final Integer month = monthDDC.getConvertedInput();
                final Integer year = yearDDC.getConvertedInput();
                if (day != null && month != null && year != null) {
                    final Calendar cal = new GregorianCalendar(year, month, 1);
                    final int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (day > totalDays) {
                        error(new ValidationError().addMessageKey("error.validation.daysOfMonth"));
                    }
                }
            }
        });
    }

    @Override
    public void validate() {
        super.validate();
        if (isValid()) {
            final Integer day = dayDDC.getConvertedInput();
            final Integer month = monthDDC.getConvertedInput();
            final Integer year = yearDDC.getConvertedInput();
            final Calendar cal = new GregorianCalendar(year, month, day);
            setDefaultModelObject(cal.getTime());
        }
    }

    /**
     * Here we pull out each field from the Date if it exists and put the
     * contents into the fields.
     */
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        final Date date = this.getModelObject();

        if (date != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // copy the field values into the form fields.
            this.dayDDC.setModelObject(cal.get(Calendar.DAY_OF_MONTH));
            this.monthDDC.setModelObject(cal.get(Calendar.MONTH));

            final int currentYear = cal.get(Calendar.YEAR);
            if (this.yearDDC.getChoices().contains(currentYear)) {
                this.yearDDC.setModelObject(cal.get(Calendar.YEAR));
            }
        }
    }

    @Override
    public boolean checkRequired() {
        if (isRequired()) {
            final String inputYear = yearDDC.getInput();
            final String inputMonth = monthDDC.getInput();
            final String inputDay = dayDDC.getInput();

            if (!Strings.isEmpty(inputDay) && !Strings.isEmpty(inputMonth) && !Strings.isEmpty(inputYear)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Build up a new Date instance from the values in the fields.
     *
     * Note: after convertInput has been called the panels model object has not
     * yet been updated. Only after any IValidator of the panel itself has
     * passed, the model object will be set to the new value.
     */
    @Override
    protected void convertInput() {
        super.convertInput();

        final Integer year = yearDDC.getConvertedInput();
        final Integer month = monthDDC.getConvertedInput();
        final Integer day = dayDDC.getConvertedInput();
        if (year != null && month != null && day != null) {
            final Calendar cal = Calendar.getInstance();
            cal.set(year, month, day, 0, 0, 0);
            final Date selectedDate = cal.getTime();
            setConvertedInput(selectedDate);
        }
    }

    /**
     * Gets the days.
     *
     * Note: The first day of the month has value 1.
     *
     * @return the days of a month (not month specific)
     */
    private List<Integer> getDayValues() {
        return getDayValues(null, null);
    }

    private List<Integer> getDayValues(final Integer monthOfYear, final Integer year) {
        final List<Integer> days = new ArrayList<Integer>();
        int totalDays = 31;
        if ((monthOfYear != null && year != null) && (monthOfYear >= 0 && year >= 0)) {
            final Calendar cal = new GregorianCalendar(year, monthOfYear, 1);
            totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        for (int i = 1; i <= totalDays; i++) {
            days.add(i);
        }
        return days;
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
     * @param endYear newest year in list
     * @param startYear oldest year in list
     *
     * @return the years between startYear and endYear (including both)
     */
    private List<Integer> getYearValues(int startYear, int endYear) {
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

        private static final long serialVersionUID = 1L;

        @Override
        public String getDisplayValue(final Integer object) {
            final SimpleDateFormat format = new SimpleDateFormat("MMM", DateChooserPanel.this.locale);
            final Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, object);
            return format.format(cal.getTime());
        }

        @Override
        public String getIdValue(final Integer obj, final int index) {
            return String.valueOf(index);
        }
    }

    protected void updateDayValues(final AjaxRequestTarget target) {
        final Integer monthOfYear = (Integer) monthDDC.getDefaultModelObject();
        final Integer iYear = (Integer) yearDDC.getDefaultModelObject();
        if (iYear != null && monthOfYear != null) {
            dayDDC.setChoices(getDayValues(monthOfYear.intValue(), iYear.intValue()));
            target.add(dayDDC);
        }
    }
}
