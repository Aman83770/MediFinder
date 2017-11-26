$('#SupportForm').submit(function(event) {
		$('.form-group').removeClass('has-error'); // remove the error class
		$('.help-block').remove(); // remove the error text
		// get the form data
		var SupportFormData = {
			'UserName' 	: $('input[name=name]').val(),
			'UserEmail' : $('input[name=email]').val(),
			'Subject' : $('input[name=Subject]').val(),
			'UserMessage' : $('input[name=UserMessage]').val(),
		};
		// process the form
		event.stopPropagation();
		$.ajax({
			type 		: "POST", // define the type of HTTP verb we want to use (POST for our form)
			url 		: '#', // the url where we want to POST
			data 		: SupportFormData, // our data object
			dataType 	: 'json', // what type of data do we expect back from the server
			success: function(reponse) {
				console.log(reponse);
			},
			error: function(error) {
				console.log(error);
			}
		})
	});
