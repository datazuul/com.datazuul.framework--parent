package com.datazuul.framework.webapp.wicket.components.cssmenu.quickmenu.dropdown;

import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.datazuul.framework.webapp.wicket.components.cssmenu.LabeledMenuItem;
import com.datazuul.framework.webapp.wicket.components.cssmenu.MenuGroupName;
import com.datazuul.framework.webapp.wicket.components.cssmenu.MenuItem;
import com.datazuul.framework.webapp.wicket.components.cssmenu.MenuSeparator;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;

public class QuickMenu extends Panel {

    private static final long serialVersionUID = 1L;
    private final List<MenuItem> topMenuItems;

    public QuickMenu(final String id, final List<MenuItem> topLevelEntries) {
        super(id);
        setOutputMarkupId(true);
        setRenderBodyOnly(true);

        this.topMenuItems = topLevelEntries;

        add(new SubMenuListView("topmenuitems", new PropertyModel(this, "topMenuItems")));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssReferenceHeaderItem.forReference(new CssResourceReference(getClass(), "quickmenu_styles.css")));
        response.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(getClass(), "quickmenu.js")));
    }

    final class LinkFragment extends Fragment {

        private static final long serialVersionUID = 0L;

        public LinkFragment(final AbstractLink link) {
            super("linkfragment", "LINKFRAGMENT", QuickMenu.this);
            add(link);
            setRenderBodyOnly(true);
        }
    }

    final class TextFragment extends Fragment {

        private static final long serialVersionUID = 0L;

        public TextFragment(final Label label) {
            super("linkfragment", "TEXTFRAGMENT", QuickMenu.this);
            add(label);
            setRenderBodyOnly(true);
        }
    }

    final class GroupNameFragment extends Fragment {

        private static final long serialVersionUID = 0L;

        public GroupNameFragment(final Label label) {
            super("linkfragment", "GROUPNAMEFRAGMENT", QuickMenu.this);
            add(label);
            setRenderBodyOnly(true);
        }
    }

    final class SeparatorFragment extends Fragment {

        public SeparatorFragment() {
            super("linkfragment", "SEPARATORFRAGMENT", QuickMenu.this);
        }
        private static final long serialVersionUID = 0L;
    }

    private final class SubMenuListView extends ListView {

        private static final long serialVersionUID = 0L;

        private SubMenuListView(final String id, final IModel model) {
            super(id, model);
            setRenderBodyOnly(true);
        }

        private SubMenuListView(final String id, final List<MenuItem> list) {
            super(id, list);
            setRenderBodyOnly(true);
        }

        @Override
        protected void populateItem(final ListItem item) {
            final MenuItem menuItem = (MenuItem) item.getModelObject();
            item.add(menuItem.isTopLevel() ? new MenuItemFragment(menuItem, true) : new MenuItemFragment(menuItem,
                    false));
        }
    }

    private final class MenuItemFragment extends Fragment {

        private static final long serialVersionUID = 0L;

        public MenuItemFragment(final MenuItem menuItem, final boolean isTopLevel) {
            super("menuitemfragment", "MENUITEMFRAGMENT", QuickMenu.this);
            setRenderBodyOnly(true);
            // Add the menu's label (hyperlinked if a link is provided)
            final WebMarkupContainer menuitemul = new WebMarkupContainer("menuitemlist");
            add(menuitemul);

            if (!menuItem.getChildren().isEmpty()) {
                // Hide the <ul> tag if there are no submenus
                menuitemul.add(new SubMenuListView("menuitemlinks", menuItem.getChildren()));
            } else {
                menuitemul.setVisible(false);
            }
            if (menuItem instanceof MenuSeparator) {
                add(new SeparatorFragment());
            } else if (menuItem instanceof LabeledMenuItem) {
                final LabeledMenuItem lmenuItem = (LabeledMenuItem) menuItem;
                if (lmenuItem instanceof MenuGroupName) {
                    add(new GroupNameFragment(lmenuItem.getLabel()));
                } else {

                    if (lmenuItem.getLink() != null) {
                        add(new LinkFragment(lmenuItem.getLink()));
                    } else {
                        add(new TextFragment(lmenuItem.getLabel()));
                    }
                }
            } else {
                throw new IllegalArgumentException("Dont know how to handle class '" + menuItem.getClass().getName()
                        + "'");
            }

            final WebMarkupContainer sep = new WebMarkupContainer("toplevelseparator");
            sep.setVisible(isTopLevel);
            add(sep);
        }
    }
}
