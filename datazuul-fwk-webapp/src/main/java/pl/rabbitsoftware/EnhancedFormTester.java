package pl.rabbitsoftware;

import static junit.framework.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.datazuul.framework.webapp.wicket.components.form.EnumCheckGroupPanel;

/**
 * added implementation for selectCheckbox see
 * http://paulszulc.wordpress.com/category/wicket/
 * 
 * @author Paul Szulc, Ralf Eichinger
 */
public class EnhancedFormTester {

    private final FormTester formTester;
    private final WicketTester tester;
    private final String formPath;

    public EnhancedFormTester(final WicketTester tester, final String formPath) {
	this(tester, tester.newFormTester(formPath), formPath);
    }

    public EnhancedFormTester(final WicketTester tester, final FormTester formTester, final String formPath) {
	this.formTester = formTester;
	this.tester = tester;
	this.formPath = formPath;
    }

    private void assertComponent(final String path, final Class<? extends Component> clazz) {
	final String fullPath = formPath + ":" + path;
	tester.assertComponent(fullPath, clazz);
	tester.assertVisible(fullPath);
	final Component component = tester.getComponentFromLastRenderedPage(fullPath);
	assertTrue(component.isEnabled());
    }

    private EnhancedFormTester select(final String path, final int index, final Class<? extends Component> clazz) {
	assertComponent(path, clazz);
	formTester.select(path, index);
	return new EnhancedFormTester(tester, formTester, formPath);
    }

    private EnhancedFormTester select(final String path, final Object value, final Class<? extends Component> clazz) {
	return select(path, new Object[] { value }, clazz);
    }

    private EnhancedFormTester select(final String path, final Object[] values, final Class<? extends Component> clazz) {
	assertComponent(path, clazz);
	EnhancedFormTester result = null;
	final Component component = formTester.getForm().get(path);
	final AbstractSingleSelectChoice component2 = (AbstractSingleSelectChoice) formTester.getForm().get(path);
	List choices = component2.getChoices();
	if (choices != null) {
	    for (final Object value : values) {
		for (int index = 0; index < choices.size(); index++) {
		    final Object choice = choices.get(index);
		    if (choice.equals(value)) {
			result = select(path, index, clazz);
		    }
		}
	    }
	}
	return result;
    }

    private EnhancedFormTester setValue(final String path, final String value, final Class<? extends Component> clazz) {
	assertComponent(path, clazz);
	formTester.setValue(path, value);
	return new EnhancedFormTester(tester, formTester, formPath);
    }

    public EnhancedFormTester selectDropDownChoice(final String path, final int index) {
	return select(path, index, DropDownChoice.class);
    }

    public EnhancedFormTester selectDropDownChoice(final String path, final Object value) {
	return select(path, value, DropDownChoice.class);
    }

    public EnhancedFormTester selectCheckbox(final String path, final boolean selected) {
	assertComponent(path, CheckBox.class);
	formTester.setValue(path, selected);
	return new EnhancedFormTester(tester, formTester, formPath);
    }

    public EnhancedFormTester selectCheckGroup(final String path, final Object[] values) {
	assertComponent(path, CheckGroup.class);

	final List<Object> valuesList = Arrays.asList(values);
	final CheckGroup checkGroup = (CheckGroup) formTester.getForm().get(path);
	final String paramName = checkGroup.getInputName();
	// delete preset values
	tester.getRequest().getPostParameters().setParameterValues(paramName, null);
	checkGroup.visitChildren(Check.class, new IVisitor<Check, Check>() {
	    @Override
	    public void component(final Check check, final IVisit<Check> visit) {
		if (valuesList.contains(check.getDefaultModelObject())) {
		    final String paramValue = check.getValue();
		    tester.getRequest().getPostParameters().addParameterValue(paramName, paramValue);
		}
	    }
	});
	return new EnhancedFormTester(tester, formTester, formPath);
    }

    public EnhancedFormTester selectRadioChoice(final String path, final int index) {
	throw new UnsupportedOperationException("todo");
    }

    public EnhancedFormTester setTextFieldValue(final String path, final String value) {
	return setValue(path, value, TextField.class);
    }

    public EnhancedFormTester setTextAreaValue(final String path, final String value) {
	return setValue(path, value, TextArea.class);
    }

    public EnhancedFormTester setPasswordTextFieldValue(final String path, final String value) {
	return setValue(path, value, PasswordTextField.class);
    }

    public void submitWithButton(final String path) {
	assertComponent(path, Button.class);
	formTester.submit(path);
    }

    public void submitWithAjaxButton(final String path) {
	assertComponent(path, AjaxFallbackButton.class);
	formTester.submit(path);
    }

    public void submitWithSubmitLink(final String path) {
	tester.assertComponent(formPath + ":" + path, SubmitLink.class);
	tester.assertVisible(formPath + ":" + path);
	final Component button = tester.getComponentFromLastRenderedPage(formPath + ":" + path);
	assertTrue(button.isEnabled());
	formTester.submit(path);
    }

}
