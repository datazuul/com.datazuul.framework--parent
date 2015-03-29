package com.datazuul.framework.webapp.wicket.components.locale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;

import com.datazuul.framework.domain.Language;
import com.datazuul.framework.util.EnumUtils;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * @author Ralf Eichinger
 */
public abstract class LanguageSwitcherPanel extends Panel {

    private static final long serialVersionUID = 1L;
    final List<Language> languages;

    public LanguageSwitcherPanel(final String id, final List<Language> languages) {
        super(id);
        this.languages = languages;

        setOutputMarkupId(true);

        // handle current locale
        final WebMarkupContainer currentLocaleContainer = new WebMarkupContainer("currentLocale") {
            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                final Locale currentLocale = getCurrentLocale();
                String currentCountry = currentLocale.getCountry();
                if (currentCountry == null || currentCountry.length() == 0) {
                    currentCountry = currentLocale.getLanguage();
                }
                add(AttributeModifier.replace("class", "current " + currentCountry.toLowerCase()));
            }
        };
        add(currentLocaleContainer);

        final Label label = new Label("label", new Model() {
            @Override
            public Serializable getObject() {
                final Locale currentLocale = getCurrentLocale();
                Language language = Language.getLanguage(currentLocale);
                String label = getLocalizer().getString(EnumUtils.getEnumKey(language), LanguageSwitcherPanel.this,
                        null, currentLocale, null, null);
                return label;
            }
        });
        label.setRenderBodyOnly(true);
        currentLocaleContainer.add(label);

        // handle other locales
        final ListView lv = new ListView("lv", new LoadableDetachableModel() {
            @Override
            protected Object load() {
                return getLocales(languages);
            }

            private List<Locale> getLocales(final List<Language> availableTranslationLanguages) {
                final List<Locale> locales = new ArrayList<Locale>();
                final Locale currentLocale = getLocale();
                for (final Language language : availableTranslationLanguages) {
                    final Locale languageLocale = language.getLocale();
                    // filter current locale from list
                    if (languageLocale != currentLocale) {
                        locales.add(languageLocale);
                    }
                }
                return sortLocales(currentLocale, locales);
            }

            private List<Locale> sortLocales(final Locale currentLocale, final List<Locale> locales) {
                final List<Locale> sortedLocales = new ArrayList<Locale>();

                final ArrayList<SortableLocale> sortableChoicesList = new ArrayList<SortableLocale>();
                for (final Locale locale : locales) {
                    Language language = Language.getLanguage(locale);
                    String label = getLocalizer().getString(EnumUtils.getEnumKey(language), LanguageSwitcherPanel.this,
                            null, locale, null, null);
                    final SortableLocale sLocale = new SortableLocale(locale, label);
                    sortableChoicesList.add(sLocale);
                }
                Collections.sort(sortableChoicesList);

                for (final SortableLocale sortableLocale : sortableChoicesList) {
                    sortedLocales.add(sortableLocale.getLocale());
                }
                return sortedLocales;
            }
        }) {
            @Override
            protected void populateItem(final ListItem item) {
                final Locale itemLocale = ((Locale) item.getModelObject());
                String country = itemLocale.getCountry();
                if (country == null || country.length() == 0) {
                    country = itemLocale.getLanguage();
                }
                item.add(new AttributeModifier("class", country.toLowerCase()));

                Language language = Language.getLanguage(itemLocale);
                final String display = getString(EnumUtils.getEnumKey(language));

                final Link link = new Link("link") {
                    @Override
                    public void onClick() {
                        setLocale(itemLocale);
                    }
                };
                link.add(new AttributeModifier("title", display));
                item.add(link);

                final Label label = new Label("label", display);
                label.setRenderBodyOnly(true);
                link.add(label);
            }
        };
        lv.setReuseItems(false);
        lv.setOutputMarkupId(true);
        currentLocaleContainer.add(lv);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssReferenceHeaderItem.forReference(new CssResourceReference(LanguageSwitcherPanel.class, "css/LanguageSwitcher.css")));
    }

    /**
     * @param locale the locale to set
     */
    public abstract void setLocale(final Locale locale);

    private Locale getCurrentLocale() {
        Locale currentLocale = getLocale();
        boolean found = false;
        for (final Language language : languages) {
            if (language.getLocale().equals(currentLocale)) {
                found = true;
            }
        }
        if (!found) {
            final Locale defaultLocale = Language.ENGLISH.getLocale();
            getSession().setLocale(defaultLocale);
            currentLocale = defaultLocale;
        }
        return currentLocale;
    }
}
