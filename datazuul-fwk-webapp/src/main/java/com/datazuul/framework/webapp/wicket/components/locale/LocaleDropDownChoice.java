package com.datazuul.framework.webapp.wicket.components.locale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datazuul.framework.domain.Language;
import com.datazuul.framework.util.EnumUtils;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * @author Ralf Eichinger
 */
public final class LocaleDropDownChoice extends DropDownChoice<Locale> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleDropDownChoice.class);

    /**
     * Construct.
     *
     * @param id component id
     */
    public LocaleDropDownChoice(final String id, final List<Locale> choices) {
        super(id);

        final List<Locale> sortedChoicesList = sortLocales(getLocale(), choices);
        setChoices(sortedChoicesList);

        final PropertyModel<Locale> pm = new PropertyModel<Locale>(LocaleDropDownChoice.this, "locale");
        // set the model that gets the current locale, and that is used for
        // updating the current locale to property 'locale' of FormInput
        setDefaultModel(pm);

        LOGGER.info("Default Locale set to: {}", pm.getObject());

        final LocaleChoiceRenderer lcr = new LocaleChoiceRenderer(pm);
        setChoiceRenderer(lcr);

        add(new AttributeModifier("class", "flag germany"));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssReferenceHeaderItem.forReference(new CssResourceReference(this.getClass(), "css/localeDropDownChoice.css")));
    }

    // @Override
    // protected void appendOptionHtml(AppendingStringBuffer buffer,
    // Locale choice, int index, String selected) {
    // buffer.append("class='flag germany'");
    //
    // AppendingStringBuffer tmp = new AppendingStringBuffer(50);
    // super.appendOptionHtml(tmp, choice, index, selected);
    // if (getChoiceRenderer() instanceof IStyledChoiceRenderer) {
    // IStyledChoiceRenderer<T> styledChoiceRenderer =
    // (IStyledChoiceRenderer<T>) getChoiceRenderer();
    //
    // String currentOptGroupLabel =
    // styledChoiceRenderer.getOptGroupLabel(choice);
    //
    // if (!Utils.equalsOrNull(currentOptGroupLabel,
    // previouslyAppendedOptGroupLabel)) {
    // // OptGroup changed
    // if (previouslyAppendedOptGroupLabel != null) {
    // endOptGroup(buffer);
    // }
    //
    // if (currentOptGroupLabel != null) {
    // // OptGroup started
    // int start = tmp.indexOf("<option");
    // StringBuilder label = new
    // StringBuilder(currentOptGroupLabel.length() + 19);
    // label.append("<optgroup
    // label=\"").append(currentOptGroupLabel).append("\">");
    // tmp.insert(start, label);
    // }
    // }
    //
    // if ((currentOptGroupLabel != null) && (index == (choices-1))) {
    // // Last option group must end too
    // endOptGroup(tmp);
    // }
    //
    // {
    // String cssClass = getChoiceRenderer().getOptionCssClassName(choice);
    // if (cssClass != null) {
    // int start = tmp.indexOf("<option");
    // tmp.insert(start + 7, new
    // StringBuilder("	class=\"").append(cssClass).append("\""));
    // }
    // }
    //
    // previouslyAppendedOptGroupLabel = currentOptGroupLabel;
    // }
    // buffer.append(tmp);
    // }
    /**
     * @see org.apache.wicket.markup.html.form.DropDownChoice#onSelectionChanged(java.lang.Object)
     */
    @Override
    public void onSelectionChanged(final Locale newSelection) {
        // note that we don't have to do anything here, as our property
        // model should recognize this
        final List<Locale> choicesList = (List<Locale>) getChoices();
        final List<Locale> sortedChoicesList = sortLocales(newSelection, choicesList);
        setChoices(sortedChoicesList);
    }

    private List<Locale> sortLocales(final Locale newSelection, final List<Locale> choicesList) {
        final ArrayList<SortableLocale> sortableChoicesList = new ArrayList<SortableLocale>();
        for (final Locale locale : choicesList) {
            Language language = Language.getLanguage(locale);
            String label = getLocalizer().getString(EnumUtils.getEnumKey(language), this, null, locale, null, null);
            final SortableLocale sLocale = new SortableLocale(locale, label);
            sortableChoicesList.add(sLocale);
        }
        Collections.sort(sortableChoicesList);

        final List<Locale> sortedChoicesList = new ArrayList<Locale>();
        for (final SortableLocale sLocale : sortableChoicesList) {
            final Locale locale = sLocale.getLocale();
            sortedChoicesList.add(0, locale);
        }
        return sortedChoicesList;
    }

    /**
     * @see org.apache.wicket.markup.html.form.DropDownChoice#wantOnSelectionChangedNotifications()
     */
    @Override
    protected boolean wantOnSelectionChangedNotifications() {
        // we want roundtrips when a the user selects another item
        return true;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(final Locale locale) {
        getSession().setLocale(locale);
        LOGGER.info("Locale changed to: {}", getLocale());
    }
}
