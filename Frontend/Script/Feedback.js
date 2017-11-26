$('#FeedbackForm').submit(function(event) {
        $('.form-group').removeClass('has-error'); // remove the error class
        $('.help-block').remove(); // remove the error text
        // get the form data
        var FeedbackFormData = {
            'UserName'  : $('input[name=name]').val(),
            'UserEmail' : $('input[name=email]').val(),
            'UserMessage' : $('input[name=message]').val(),
            'UserRating' : $('input[name=RatingValue]').val(),
        };
        // process the form
        event.stopPropagation();
        $.ajax({
            type        : "POST",
            url         : '/Feedback', 
            data        : FeedbackFormData,
            dataType    : 'json', 
            success: function(reponse) {
                console.log(reponse);
            },
            error: function(error) {
                console.log(error);
            }
        })
    });
