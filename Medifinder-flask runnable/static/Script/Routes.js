$(document).ready(function() {
	if (typeof($.cookie("t")) !== 'undefined') {
		// t - Type
		var loginType = $.cookie("t");
		
		// Doctor
		if (loginType === 'doc') {
			
		}
		// Chemist
		else if (loginType === 'chem') {

		}
		// Hospital
		else if (loginType === 'hosp') {

		}
		// Diagnostic Center
		else if (loginType === 'dia') {
			
		}
	}
	// Not logged in..
	else {

	}
	// $.cookie('t', 'doc');
});