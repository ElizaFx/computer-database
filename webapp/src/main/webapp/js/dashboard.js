//On load
$(function() {
	// Default: hide edit mode
	$(".editMode").hide();

	// Click on "selectall" box
	$("#selectall").click(function() {
		$('.cb').prop('checked', this.checked);
	});

	// Click on a checkbox
	$(".cb").click(function() {
		if ($(".cb").length == $(".cb:checked").length) {
			$("#selectall").prop("checked", true);
		} else {
			$("#selectall").prop("checked", false);
		}
		if ($(".cb:checked").length != 0) {
			$("#deleteSelected").enable();
		} else {
			$("#deleteSelected").disable();
		}
	});

});

// Function setCheckboxValues
(function($) {

	$.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

		var str = $('.' + checkboxFieldName + ':checked').map(function() {
			return this.value;
		}).get().join();

		$(this).attr('value', str);

		return this;
	};

}(jQuery));

// Function toggleEditMode
(function($) {

	$.fn.toggleEditMode = function() {
		if ($(".editMode").is(":visible")) {
			$(".editMode").hide();
			$("#editComputer").text(strings['global.delete']);
		} else {
			$(".editMode").show();
			$("#editComputer").text(strings['global.back']);
		}
		return this;
	};

}(jQuery));

var deleteSelection = function() {
	$('#deleteForm input[name=selection]').setCheckboxValues('selection', 'cb');
	$('#deleteForm').submit();
	removeCustomAlert();
	return false;
};

// Function delete selected: Asks for confirmation to delete selected computers,
// then submits it to the deleteForm
(function($) {
	$.fn.deleteSelected = function() {
		createCustomAlert(deleteSelection,
				strings['dashboard.confirmDeletion'], strings['global.ok'],
				strings['global.cancel'])
	};
}(jQuery));

// Event handling
// Onkeydown
$(document).keydown(
		function(e) {

			switch (e.keyCode) {
			// DEL key
			case 46:
				if ($(".editMode").is(":visible")
						&& $(".cb:checked").length != 0) {
					$.fn.deleteSelected(deleteSelection,
							strings['dashboard.confirmDeletion'],
							strings['global.ok'], strings['global.cancel']);
				}
				break;
			// E key (CTRL+E will switch to edit mode)
			case 69:
				if (e.ctrlKey) {
					$.fn.toggleEditMode();
				}
				break;
			}
		});

var ALERT_BUTTON_TEXT = "Ok";

function createCustomAlert(process, txt, okTxt, cancelTxt) {
	d = document;
	if (d.getElementById("modalContainer"))
		return false;

	// Main container
	mObj = d.getElementsByTagName("body")[0]
			.appendChild(d.createElement("div"));
	mObj.id = "modalContainer";
	mObj.style.height = d.documentElement.scrollHeight + "px";

	// alertBox which is a bootstrap panel
	alertObj = mObj.appendChild(d.createElement("div"));
	alertObj.id = "alertBox";

	alertObj.style.visiblity = "visible";
	alertObj.classList.toggle("panel");
	alertObj.classList.toggle("panel-primary");

	// Add a cool panel-heading without text
	alertObj.appendChild(d.createElement("div")).classList
			.toggle("panel-heading");

	// Add a panel-body for the text
	msg = alertObj.appendChild(d.createElement("div"));
	msg.classList.toggle("panel-body");
	msg.innerHTML = txt;

	// Add another panel-body for buttons
	btnGrp = alertObj.appendChild(d.createElement("div"))
	btnGrp.classList.toggle("pull-right");
	btnGrp.classList.toggle("panel-body");
	btnGrp.classList.toggle("btn-toolbar");

	// Button Ok with its text and function
	OkBtn = btnGrp.appendChild(d.createElement("a"));
	OkBtn.appendChild(d.createTextNode(okTxt));
	OkBtn.href = "#";
	OkBtn.focus();
	OkBtn.classList.toggle("btn");
	OkBtn.classList.toggle("btn-primary");
	OkBtn.classList.toggle("btn-sm");
	OkBtn.onclick = process;

	// Button cancel with its text and close function
	closeBtn = btnGrp.appendChild(d.createElement("a"));
	closeBtn.appendChild(d.createTextNode(cancelTxt));
	closeBtn.classList.toggle("btn");
	closeBtn.classList.toggle("btn-default");
	closeBtn.classList.toggle("btn-sm");
	closeBtn.href = "#";
	closeBtn.focus();
	closeBtn.onclick = function() {
		removeCustomAlert();
		return false;
	}

	alertObj.style.display = "block";
}

function removeCustomAlert() {
	document.getElementsByTagName("body")[0].removeChild(document
			.getElementById("modalContainer"));
}