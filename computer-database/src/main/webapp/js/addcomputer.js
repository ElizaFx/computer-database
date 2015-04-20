$(document).keyup(
		function(e) {
			if ($("#computerName").is(":focus")) {
				parent = $("#computerName").parent();
				if ($("#computerName").val().length != 0) {
					if (parent.hasClass("has-error")) {
						parent.removeClass("has-error")
					}
				} else {
					if (!parent.hasClass("has-error")) {
						parent.addClass("has-error")
					}
				}
			} else if ($("#introduced").is(":focus")
					|| $("#discontinued").is(":focus")) {
				elt = $(':focus');
				value = elt.val();
				if (value.length != 0) {
					parent = elt.parent();
					if (value.match("^\\d{4}([/.-])\\d{2}\\1\\d{2}$")
							|| value.match("^\\d{2}([/.-])\\d{2}\\1\\d{4}$")) {
						if (!parent.hasClass("has-success")) {
							parent.removeClass("has-error").addClass(
									"has-success")
						}
					} else if (!parent.hasClass("has-error")) {
						parent.removeClass("has-success").addClass("has-error")
					}
				} else {
					parent.removeClass("has-error").removeClass("has-success")
				}
			}
		});