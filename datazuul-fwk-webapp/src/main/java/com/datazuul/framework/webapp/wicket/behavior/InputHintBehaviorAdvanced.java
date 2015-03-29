package com.datazuul.framework.webapp.wicket.behavior;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class InputHintBehaviorAdvanced extends Behavior {

    private static final long serialVersionUID = 1L;
    private final Set<Component> bound = new HashSet<Component>();
    private final String hintStyle;
    private final String entryStyle;
    private final Form form;
    private final IModel<String> hintText;

    public InputHintBehaviorAdvanced(final Form form, final IModel<String> hintText, final String hintStyle) {
        this.hintStyle = hintStyle;
        this.entryStyle = "";
        this.form = form;
        this.hintText = hintText;
    }

    public InputHintBehaviorAdvanced(final Form form, final IModel<String> hintText, final String hintStyle,
            final String entryStyle) {
        this.hintStyle = hintStyle;
        this.entryStyle = entryStyle;
        this.form = form;
        this.hintText = hintText;
    }

    /**
     * Override and return false to suppress static Javascript and CSS contributions. (May be desired if you are
     * concatenating / compressing resources as part of build process)
     *
     * @return
     */
    protected boolean autoAddToHeader() {
        return true;
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse iHeaderResponse) {
        super.renderHead(component, iHeaderResponse);
        if (autoAddToHeader()) {
            // FIXME migrate to wicket 6
//            iHeaderResponse.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(InputHintBehavior.class, "visural-inputhint.js")));
        }
        iHeaderResponse.render(JavaScriptContentHeaderItem.forScript(getInitJS(), null));
        iHeaderResponse.render(OnDomReadyHeaderItem.forScript(getDomJS()));
    }

    private String getInitJS() {
        final StringBuilder js = new StringBuilder();
        for (final Component com : bound) {
            js.append("visural_inputHints['visural_ih_").append(com.getMarkupId())
                    .append("'] = new VisuralInputHint('").append(com.getMarkupId()).append("','");
            String hintTextString = null;
            if (hintText instanceof ResourceModel) {
                hintTextString = ((ResourceModel) hintText).getObject();
            } else if (hintText instanceof StringResourceModel) {
                hintTextString = ((StringResourceModel) hintText).getString();
            }
            js.append(hintTextString.replace("'", "\\'"));
            js.append("','").append(hintStyle.replace("'", "\\'")).append("', '")
                    .append(entryStyle.replace("'", "\\'")).append("');");
        }
        return js.toString();
    }

    private String getDomJS() {
        final StringBuilder js = new StringBuilder();
        for (final Component com : bound) {
            js.append("visural_inputHints['visural_ih_").append(com.getMarkupId()).append("'].handleBlur();");
        }
        return js.toString();
    }

    @Override
    public void bind(final Component component) {
        bound.add(component);
        component.setOutputMarkupId(true);
        // FIXME migrate to wicket 6
//        form.add(new InputHintFormBehavior(component.getMarkupId()));
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        final String focus = tag.getAttribute("onfocus");
        final String blur = tag.getAttribute("onblur");
        tag.put("onfocus", "visural_inputHints['visural_ih_" + component.getMarkupId() + "'].handleFocus();"
                + (StringUtils.isNotBlank(focus) ? focus : ""));
        tag.put("onblur", "visural_inputHints['visural_ih_" + component.getMarkupId() + "'].handleBlur();"
                + (StringUtils.isNotBlank(blur) ? blur : ""));
    }
}
