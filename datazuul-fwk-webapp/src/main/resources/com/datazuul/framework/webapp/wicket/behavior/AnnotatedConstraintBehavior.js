// AnnotatedConstraintBehavior.js
// javascript to simulate maxlength behavior on textareas
// source: http://cf-bill.blogspot.com/2005/05/unobtrusive-javascript-textarea.html

function textAreasInit() {
	var objs, oi, thisObj;

	objs = document.getElementsByTagName("textarea");
	oi = 0; // oi is object index

	for (oi = 0; oi < objs.length; oi++) {
		thisObj = objs[oi];
		// note that maxlength is casesensitve
		if (thisObj.getAttribute('maxlength')) {
			thisObj.onkeyup = forceMaxLength;
		}
		//thisObj.onchange = saveEntryValue;
	}
}

function forceMaxLength() {
	var maxLength = parseInt(this.getAttribute('maxlength'));
	if (this.value.length > maxlength) {
		this.value = this.value.substring(0, maxlength);
	}
}

function addEvent(elm, evType, fn, useCapture)
// addEvent and removeEvent
// cross-browser event handling for IE5+, NS6 and Mozilla
// By Scott Andrew
{
	if (elm.addEventListener) {
		elm.addEventListener(evType, fn, useCapture);
		return true;
	} else if (elm.attachEvent) {
		var r = elm.attachEvent("on" + evType, fn);
		return r;
	} else {
		alert("Handler could not be removed");
	}
}

addEvent(window, "load", textAreasInit);
