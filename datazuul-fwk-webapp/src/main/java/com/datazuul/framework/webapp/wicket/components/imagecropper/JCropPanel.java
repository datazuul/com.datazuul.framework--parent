package com.datazuul.framework.webapp.wicket.components.imagecropper;

import java.awt.Rectangle;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.deepliquid.jcrop.JCrop;
import com.jquery.JQuery;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

/**
 * @author Ralf Eichinger
 */
public abstract class JCropPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private int aspectRatioHeight = -1;
    private int aspectRatioWidth = -1;
    private final WebMarkupContainer previewDiv;
    private final boolean previewEnabled;
    private int previewHeight = -1;
    private final String previewImageMarkupId;
    private int previewMaxSize = -1;
    private int previewWidth = -1;
    private int selectionX1, selectionY1, selectionX2, selectionY2;

    /**
     * @param id
     * @param cropAreaModel
     * @param resource
     */
    public JCropPanel(final String id, final IModel<Rectangle> cropAreaModel, final IResource imageResource,
            final boolean previewEnabled) {
        super(id, cropAreaModel);

        this.previewEnabled = previewEnabled;

        NonCachingImage image;
        NonCachingImage previewImage;

        image = new NonCachingImage("image", imageResource);
        // adding behavior
        image.add(new JCropBehavior());
        image.setOutputMarkupId(true);
        add(image);

        previewDiv = new WebMarkupContainer("previewDiv");
        add(previewDiv);
        if (!previewEnabled) {
            previewDiv.setVisible(false);
        }

        previewImage = new NonCachingImage("previewImage", imageResource);
        previewImage.setOutputMarkupId(true);
        previewDiv.add(previewImage);

        previewImageMarkupId = previewImage.getMarkupId();
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        // IHeaderContributor iHeaderContributor =
        // JSLib.getHeaderContribution(VersionDescriptor.alwaysLatest(Library.JQUERY));
        // iHeaderContributor.renderHead(response);

        response.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(JQuery.class, "jquery-" + JQuery.VERSION
                + ".min.js")));
        response.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(JCrop.class, "js/jquery.Jcrop-"
                + JCrop.VERSION + ".js")));
        response.render(CssReferenceHeaderItem.forReference(new CssResourceReference(JCrop.class, "css/jquery.Jcrop-" + JCrop.VERSION + ".css")));
    }

    public float getAspectRatio() {
        return ((float) aspectRatioWidth / (float) aspectRatioHeight);
    }

    /**
     * @return the aspectRatioHeight
     */
    public int getAspectRatioHeight() {
        return aspectRatioHeight;
    }

    /**
     * @return the aspectRatioWidth
     */
    public int getAspectRatioWidth() {
        return aspectRatioWidth;
    }

    /**
     * @return the previewHeight
     */
    public int getPreviewHeight() {
        return previewHeight;
    }

    /**
     * @return the previewMaxSize
     */
    public int getPreviewMaxSize() {
        return previewMaxSize;
    }

    /**
     * @return the previewWidth
     */
    public int getPreviewWidth() {
        return previewWidth;
    }

    /**
     * @return
     */
    private boolean isRatioEnabled() {
        return getAspectRatioHeight() != -1 && getAspectRatioWidth() != -1;
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        if (previewEnabled) {
            // set size of preview div:
            if (isRatioEnabled() && getPreviewMaxSize() != -1 && (getPreviewHeight() == -1 && getPreviewWidth() == -1)) {
                float width = getPreviewMaxSize();
                final float height = getPreviewMaxSize();

                if (getAspectRatio() < 1) {
                    // width smaller than height --> height = max size, width
                    // resized
                    width = width * getAspectRatio();
                } else {
                    // width bigger than height --> width = max size, height
                    // resized
                    width = width / getAspectRatio();
                }

                setPreviewWidth(Math.round(width));
                setPreviewHeight(Math.round(height));

                final String style = String.format("width: %dpx; height: %dpx; overflow: hidden;", getPreviewWidth(),
                        getPreviewHeight());
                previewDiv.add(new AttributeModifier("style", style));
            }
        }
    }

    /**
     * Called when the area to be cropped is changed.
     *
     * @param target
     */
    public abstract void onCropAreaUpdate(AjaxRequestTarget target);

    /**
     * Sets aspect ratio to width / height (e.g. 4:3 or 16:9);
     *
     * @param ratioWidth aspect ratio width
     * @param ratioHeight aspect ratio height
     */
    public void setAspectRatio(final int ratioWidth, final int ratioHeight) {
        setAspectRatioWidth(ratioWidth);
        setAspectRatioHeight(ratioHeight);
    }

    /**
     * @param aspectRatioHeight the aspectRatioHeight to set
     */
    public void setAspectRatioHeight(final int aspectRatioHeight) {
        this.aspectRatioHeight = aspectRatioHeight;
    }

    /**
     * @param aspectRatioWidth the aspectRatioWidth to set
     */
    public void setAspectRatioWidth(final int aspectRatioWidth) {
        this.aspectRatioWidth = aspectRatioWidth;
    }

    /**
     * @param previewHeight the previewHeight to set
     */
    public void setPreviewHeight(final int previewHeight) {
        this.previewHeight = previewHeight;
    }

    /**
     * @param previewMaxSize the previewMaxSize to set
     */
    public void setPreviewMaxSize(final int previewMaxSize) {
        this.previewMaxSize = previewMaxSize;
    }

    /**
     * @param previewWidth the previewWidth to set
     */
    public void setPreviewWidth(final int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public void setSelection(final int x1, final int y1, final int x2, final int y2) {
        this.selectionX1 = x1;
        this.selectionY1 = y1;
        this.selectionX2 = x2;
        this.selectionY2 = y2;
    }

    private class JCropBehavior extends AbstractDefaultAjaxBehavior {

        private static final long serialVersionUID = 1L;

        @Override
        public CharSequence getCallbackUrl() {
            // variables can be accessed here as
            // c.x, c.y, c.x2, c.y2, c.w, c.h
            final String suffix = "&x=' + c.x + '&y=' + c.y + '&w=' + c.w + '&h=' + c.h + '";
            return super.getCallbackUrl() + suffix;
        }

        @Override
        public void renderHead(final Component component, final IHeaderResponse response) {
            super.renderHead(component, response);

            final StringBuffer jsTemplate = new StringBuffer();
            jsTemplate.append("jQuery(function($){").append("\n");
            jsTemplate.append("// Create variables (in this scope) to hold the API and image size").append("\n");
            jsTemplate.append("var jcrop_api, boundx, boundy;").append("\n");

            // Setting options:
            // Here's an example:
            //
            // <script language="Javascript">
            //
            // jQuery(function() {
            // jQuery('#cropbox').Jcrop({
            // onSelect: showCoords,
            // bgColor: 'black',
            // bgOpacity: .4,
            // setSelect: [ 100, 100, 50, 50 ],
            // aspectRatio: 16 / 9
            // });
            // });
            //
            // </script>

            jsTemplate.append("$('#%s').Jcrop({").append("\n");
            // Called when the selection is moving:
            // jsTemplate.append("  onChange: update,").append("\n");
            jsTemplate.append("  onSelect: update");
            jsTemplate.append(",\n  bgColor: '#580000'");
            jsTemplate.append(",\n  bgOpacity: .4");
            if (selectionX1 >= 0 && selectionY1 >= 0 && selectionX2 > 0 && selectionY2 > 0) {
                jsTemplate.append(",\n  setSelect: [" + selectionX1 + "," + selectionY1 + "," + selectionX2 + ","
                        + selectionY2 + "]");
            }
            // set aspect ratio
            if (isRatioEnabled()) {
                jsTemplate.append(",").append("\n");
                jsTemplate.append("  aspectRatio: ").append(getAspectRatioWidth()).append(" / ")
                        .append(getAspectRatioHeight()).append("\n");
            }

            jsTemplate.append("},function(){").append("\n");
            jsTemplate.append("  // Use the API to get the real image size").append("\n");
            jsTemplate.append("  var bounds = this.getBounds();").append("\n");
            jsTemplate.append("  boundx = bounds[0];").append("\n");
            jsTemplate.append("  boundy = bounds[1];").append("\n");
            jsTemplate.append("  // Store the API in the jcrop_api variable").append("\n");
            jsTemplate.append("  jcrop_api = this;").append("\n");
            jsTemplate.append("});").append("\n");
            jsTemplate.append("function update(c) {").append("\n");
            jsTemplate.append("  if (parseInt(c.w) > 0) {").append("\n");
            jsTemplate.append("    // update model").append("\n");
            jsTemplate.append("    %s").append("\n");
            jsTemplate.append("    var rx = " + getPreviewWidth() + " / c.w;").append("\n");
            jsTemplate.append("    var ry = " + getPreviewHeight() + " / c.h;").append("\n");
            if (previewEnabled) {
                // FIXME update preview image to downsize selected crop (now it
                // uses 1:1 size of cropped image)
                jsTemplate.append("    $('#%s').css({").append("\n");
                jsTemplate.append("      width: Math.round(rx * boundx) + 'px',").append("\n");
                jsTemplate.append("      height: Math.round(ry * boundy) + 'px',").append("\n");
                jsTemplate.append("      marginLeft: '-' + Math.round(rx * c.x) + 'px',").append("\n");
                jsTemplate.append("      marginTop: '-' + Math.round(ry * c.y) + 'px'").append("\n");
                jsTemplate.append("    });").append("\n");
            }
            jsTemplate.append("  }").append("\n");
            jsTemplate.append("};").append("\n");
            jsTemplate.append("});").append("\n");

            final String javaScript = String.format(jsTemplate.toString(), getComponent().getMarkupId(),
                    getCallbackScript(), previewImageMarkupId);
            // String javaScript = String.format("new Cropper.Img('%s', {"
            // + "\n onEndCrop: function(coords, dimensions) {%s}," +
            // "\n minWidth: %d, minHeight: %d," + "\n});",
            // getComponent().getMarkupId(), getCallbackScript(), getMinWidth(),
            // getMinHeight());
            response.render(OnDomReadyHeaderItem.forScript(javaScript));

        }

        @Override
        protected void respond(final AjaxRequestTarget target) {
            final Request request = RequestCycle.get().getRequest();
            final int x = request.getQueryParameters().getParameterValue("x").toInt();
            final int y = request.getQueryParameters().getParameterValue("y").toInt();
            final int w = request.getQueryParameters().getParameterValue("w").toInt();
            final int h = request.getQueryParameters().getParameterValue("h").toInt();
            JCropPanel.this.setDefaultModelObject(new Rectangle(x, y, w, h));
            onCropAreaUpdate(target);
        }
    }
}
