function exclusiveCheck(field, exclusiveValue) {
	var fieldnameFields = eval("document.getElementsByName('" + field.name + "')");
	var anyValueField;
	for (i = 0; i < fieldnameFields.length; i++) {
		if(fieldnameFields[i].getAttribute("value") == exclusiveValue) {
			anyValueField = fieldnameFields[i];
		}
	}
	if (field.id == anyValueField.id) {
		// if clicked field is anyValueField uncheck all other fields
		if (anyValueField.checked == true) {
			for (i = 0; i < fieldnameFields.length; i++) {
				fieldnameFields[i].checked = false;
			}
			anyValueField.checked = true;
		} else {
			anyValueField.checked = true;
		}
	} else {
		// other field has been clicked
		var checkFlag = false;
		for (i = 0; i < fieldnameFields.length; i++) {
			if (fieldnameFields[i].checked == true) {
				checkFlag = true;
				break;
			}
		}
		// if at minimum one other fields been selected uncheck 'Any' field
		if (checkFlag == true) {
			anyValueField.checked = false;
		}
		// if no other field has been selected check 'Any' field
		else {
			anyValueField.checked = true;
		}
	}
}