$("#computerName").keyup(function(e) {
	elt = $(this);
	parent = elt.parent();
	if (elt.val().trim().length != 0) {
		if (parent.hasClass("has-error")) {
			parent.removeClass("has-error")
		}
	} else {
		if (!parent.hasClass("has-error")) {
			parent.addClass("has-error")
		}
	}
});

function validateDate(elt) {
	value = elt.val();
	if (value.length != 0) {
		parent = elt.parent();
		if (value.match("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")
				|| value.match("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
			if (!parent.hasClass("has-success")) {
				parent.removeClass("has-error").addClass("has-success")
			}
		} else if (!parent.hasClass("has-error")) {
			parent.removeClass("has-success").addClass("has-error")
		}
	} else {
		parent.removeClass("has-error").removeClass("has-success")
	}
}

$("#introduced").keyup(function(e) {
	validateDate($(this));
});

$("#discontinued").keyup(function(e) {
	validateDate($(this));
});