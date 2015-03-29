package com.datazuul.framework.webapp.wicket.components.form;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.datazuul.framework.util.EnumUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;

public class EnumCheckGroupPanel<T extends Enum> extends FormComponentPanel {

    private static final long serialVersionUID = 1L;
    private T exclusiveValue = null;
    private CheckGroup group;

    public EnumCheckGroupPanel(final String id, final IModel model, final List<T> values, final T exclusiveValue) {
        super(id, model);
        if (exclusiveValue != null) {
            this.exclusiveValue = exclusiveValue;
            sortValues(values);
        }
        addComponents(model, values, exclusiveValue);
    }

    /**
     * FIXME did not work with a CompoundPropertyModel (over id, because "group" is tried to be looked up...)
     *
     * @param id
     * @param values
     * @param exclusiveValue
     */
    private EnumCheckGroupPanel(final String id, final List<T> values, final T exclusiveValue) {
        super(id);
        if (exclusiveValue != null) {
            this.exclusiveValue = exclusiveValue;
            sortValues(values);
        }
        addComponents(null, values, exclusiveValue);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        if (exclusiveValue != null) {
            response.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(this.getClass(), "exclusiveCheckbox.js")));
        }
    }

    /**
     * Move exclusive value to the first position
     *
     * @param values
     */
    private void sortValues(final List<T> values) {
        values.remove(exclusiveValue);
        values.add(0, exclusiveValue);
    }

    private void addComponents(final IModel model, final List<T> values, final T exclusiveValue) {
        // ... field
        if (model == null) {
            group = new CheckGroup("group", new Model());
        } else {
            group = new CheckGroup("group", model);
        }
        add(group);
        // group.add(new CheckGroupSelector("groupselector"));
        final ListView types = new ListView("values", values) {
            private static final long serialVersionUID = 1L;
            // private final String exclusiveValue =
            // FamilyStatusType.ANY.name();
            protected String exclusiveCheckboxValue = null;

            @Override
            protected void populateItem(final ListItem item) {

                final Check check = new Check("check", item.getModel());
                final T value = (T) item.getModelObject();
                check.setLabel(new ResourceModel(EnumUtils.getEnumKey(value)));
                item.add(check);
                if (value == exclusiveValue) {
                    exclusiveCheckboxValue = check.getValue();
                }
                item.add(new SimpleFormComponentLabel("label", check));
            }

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                visitChildren(new IVisitor<Component, Void>() {
                    @Override
                    public void component(final Component component, final IVisit visit) {
                        if (exclusiveCheckboxValue != null) {
                            if (component instanceof ListItem) {
                                final Check check = (Check) ((ListItem) component).get("check");
                                check.add(new AttributeModifier("onclick", "exclusiveCheck(this, '"
                                        + exclusiveCheckboxValue + "');"));
                            }
                        }
                        visit.dontGoDeeper();
                    }
                });
            }
        };
        types.setReuseItems(true);
        group.add(types);
    }

    @Override
    protected void convertInput() {
        super.convertInput();
        setConvertedInput(group.getDefaultModelObject());
    }

    @Override
    public boolean checkRequired() {
        group.setRequired(isRequired());
        return group.checkRequired();
    }
}
