package com.datazuul.framework.webapp.wicket.components.authentication;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

/**
 * @author Ralf Eichinger
 */
public abstract class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;
    /**
     * Field for password.
     */
    private PasswordTextField password;
    /**
     * Field for user name.
     */
    private TextField<String> username;

    /**
     * Sign in form.
     */
    public final class LoginForm extends Form {

        private static final long serialVersionUID = 1L;
        /**
         * El-cheapo model for form.
         */
        private final ValueMap properties = new ValueMap();

        /**
         * Constructor.
         *
         * @param id id of the form component
         */
        public LoginForm(final String id) {
            super(id);

            // Attach textfield components that edit properties map
            // in lieu of a formal beans model
            add(username = new TextField<String>("username", new PropertyModel<String>(properties, "username")));
            add(password = new PasswordTextField("password", new PropertyModel<String>(properties, "password")));

            username.setType(String.class);
            password.setType(String.class);
        }

        /**
         * @see org.apache.wicket.markup.html.form.Form#onSubmit()
         */
        @Override
        public final void onSubmit() {
            if (login(getUsername(), getPassword())) {
                // If login has been called because the user was not yet
                // logged in, than continue to the original destination,
                // otherwise to the Home page
                continueToOriginalDestination();
                // if we reach this line there was no intercept page, so go to home page
                setResponsePage(getApplication().getHomePage());
            } else {
                // Try the component based localizer first. If not found try the
                // application localizer. Else use the default
                final String errmsg = getLocalizer().getString("loginError", this, "Unable to sign you in");

                error(errmsg);
            }
        }
    }

    /**
     * @param id See Component constructor
     * @see org.apache.wicket.Component#Component(String)
     */
    public LoginPanel(final String id) {
        super(id);

        // Create feedback panel and add to page
        // final FeedbackPanel feedback = new FeedbackPanel("feedback");
        // add(feedback);
        // use overall feedback panel!

        // Add sign-in form to page, passing feedback panel as
        // validation error handler
        add(new LoginForm("loginForm"));
    }

    /**
     * Convenience method to access the password.
     *
     * @return The password
     */
    public String getPassword() {
        return password.getDefaultModelObjectAsString();
    }

    /**
     * Convenience method to access the username.
     *
     * @return The user name
     */
    public String getUsername() {
        return username.getDefaultModelObjectAsString();
    }

    /**
     * Sign in user if possible.
     *
     * @param username The username
     * @param password The password
     * @return True if signin was successful
     */
    public abstract boolean login(final String username, final String password);
}
