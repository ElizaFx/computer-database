$("#computerName").keyup(function(e) {
	elt = $(this);
	parent = elt.parent();
	if (elt.val().trim().length != 0) {
		if (parent.hasClass("has-error")) {
			parent.removeClass("has-error")
			parent.find(".glyphicon").remove();
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
			parent.find(".glyphicon").remove();
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

$('select').selectpicker();

$(document).ready(
		function() {
			var dateFormat = strings['global.datePattern'].toLowerCase()
					.replace("yyyy", "yy");
			var datePickerConfigEn = {
				firstDay : 1,
				minDate : new Date(1970, 1 - 1, 2),
				maxDate : new Date(2038, 1 - 1, 19),
				changeMonth : true,
				changeYear : true,
				yearRange : "1970:2038",
				duration : "normal",
				dateFormat : dateFormat
			};
			// Datepicker
			$(function() {
				$("#introduced").datepicker(datePickerConfigEn);
				$("#discontinued").datepicker(datePickerConfigEn);
			});
		});