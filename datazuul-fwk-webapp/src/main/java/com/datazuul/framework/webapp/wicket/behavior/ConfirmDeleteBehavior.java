package com.datazuul.framework.webapp.wicket.behavior;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.util.template.JavaScriptTemplate;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

/**
 * @author Ralf Eichinger
 */
public class ConfirmDeleteBehavior extends Behavior {

    private static final long serialVersionUID = 1L;
    private Component component;

    /**
     * This method is called after the behavior is associated with the component through the Component.add(IBehavior)
     * method call. You will need to determine the component markup ID later, as you will find out.
     *
     * @see org.apache.wicket.behavior.AbstractBehavior#bind(org.apache.wicket.Component)
     */
    @Override
    public void bind(final Component component) {
        // super.bind(component);

        this.component = component;

        // It is absolutely essential that you output component markup ID, as it
        // will be used later
        // to determine the JavaScript function name uniquely.
        component.setOutputMarkupId(true);

        // TextTemplateHeaderContributor accepts the class to be used for
        // retrieving the classloader for
        // loading the packaged template. Since you specify
        // ConfirmDeleteBehavior as the class, you need
        // to make sure that you keep the confirmdelete.js file in the same
        // package folder structure
        // as ConfirmDeleteBehavior.

        component.add(new Behavior() {
            private static final long serialVersionUID = 1L;
            private final TextTemplate template = new JavaScriptTemplate(new PackageTextTemplate(
                    ConfirmDeleteBehavior.class, "confirmdelete.js"));

            @Override
            public void renderHead(final Component component, final IHeaderResponse response) {
                final Map<String, Object> variables = new HashMap<String, Object>();
                variables.put("jsfunc", getJSFuncName());
                variables.put("msg", getJSMessage());
                response.render(new StringHeaderItem(template.asString(variables)));
            }
        });
    }

    /**
     * Use the Markup ID to provide a unique name for the JavaScript function.
     *
     * @return a unique JS-function name for each component using the behavior.
     */
    private final String getJSFuncName() {
        return "confirmDelete" + component.getMarkupId();
    }

    /**
     * Allow subclasses to specify the custom display message.
     *
     * @return message to be displayed in confirmation dialog.
     */
    protected String getJSMessage() {
        return "Delete Yes/No?";
    }

    /**
     * Bind the JS call to the onclick event.
     *
     * @see org.apache.wicket.behavior.AbstractBehavior#onComponentTag(org.apache.wicket.Component,
     * org.apache.wicket.markup.ComponentTag)
     */
    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        tag.put("onclick", "return " + getJSFuncName() + "()");
    }
}
