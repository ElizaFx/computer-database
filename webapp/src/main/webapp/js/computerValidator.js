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
	value = elt.val().trim();
	if (value.length != 0) {
		parent = elt.parent();
		if (isDate(value)) {
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
function isDate(sDate) {
	var isoDate
	if (value.match("^\\d{2}([/.-])\\d{2}\\1\\d{4}$"))
		if (strings['global.datePattern'] == "dd/MM/yyyy")
			isoDate = sDate.replace(/(\d{2}).(\d{2}).(\d{4})/, "$3-$2-$1");
		else if (strings['global.datePattern'] == "MM/dd/yyyy")
			isoDate = sDate.replace(/(\d{2}).(\d{2}).(\d{4})/, "$3-$1-$2");
		else
			return false;
	try {
		var d = new Date(isoDate);
		return d.toISOString().indexOf(isoDate) == 0
	} catch (err) {
		return false;
	}
}
$("#introduced").change(function(e) {
	validateDate($(this));
}).keyup(function(e) {
	validateDate($(this));
});

$("#discontinued").change(function(e) {
	validateDate($(this));
}).keyup(function(e) {
	validateDate($(this));
});