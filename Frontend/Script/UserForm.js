      // magic.js
      $(document).ready(function() {
          // process the form
  // document.getElementById("demo").innerHTML = a;
          $('#register-form').submit(function(event) {
              // console.log("Form SignUp");

              $('.form-group').removeClass('has-error'); // remove the error class
              $('.help-block').remove(); // remove the error text

              // get the form data
              // there are many ways to get this data using jQuery (you can use the class or id also)
              var SignUpFormData = {
                  'FirstName'         : $('input[name=FirstName]').val(),
                  'LastName'          : $('input[name=LastName]').val(),
                  'Gender'            : $('input[name=Gender]').val(),
                  'Email'             : $('input[name=Email]').val(),
                  'WorkArea'          : $('#WorkArea option:selected').val(),
                  'Speciality'        : $('input[name=Speciality]').val(),
                  'StoreName'         : $('input[name=StoreName]').val(),
                  'HospitalName'      : $('input[name=HospitalName]').val(),
                  'DiagnosticName'    : $('input[name=DiagnosticName]').val(),
                  'Password'          : $('input[name=Password]').val(),
              };

              // console.log(SignUpFormData);
              // process the form
              event.preventDefault();
              // event.stopPropagation();
              $.ajax({
                  type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
                  // type         : 'GET', // define the type of HTTP verb we want to use (POST for our form)
                  url         : '/SignUp/', // the url where we want to POST
                  data        : SignUpFormData, // our data object
                  dataType        : 'json', // our data object
                  success: function(response) {
                  // event.preventDefault();
                    setTimeout(function () {waitingDialog.hide();}, 100);
                    // console.log(response.message);
                    if (response.message=="Name already exists!" || response.message=="EmailID already exists!") 
                    {
                      alert("exists");
                      document.getElementById("ErrorMessage").style.display = "block";
                      window.location.href("#");
                    }
                    else
                    { 
                      waitingDialog.show('Signed up successfully');setTimeout(function () {waitingDialog.hide();}, 2000);
                      window.location.href = "/";
                    }
                  },
                  error: function(error) {
                    // event.preventDefault();
                    setTimeout(function () {waitingDialog.hide();}, 500);
                    waitingDialog.show('Something went wrong.Try later...!!!');setTimeout(function () {waitingDialog.hide();}, 5000);
                    console.log(error);
                  }
              // stop the form from submitting the normal way and refreshing the page
              // event.preventDefault();
            });
            });

      // Forget Password Form

      $('#forgot-form').submit(function(event) {
        console.log("Forget form");
        $('.form-group').removeClass('has-error'); // remove the error class
        $('.help-block').remove(); // remove the error text

        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        var ForgotFormData = {
          'UserEmail'     : $('input[name=UserEmail]').val(),
        };
        // process the form
        $.ajax({
          type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
          url         : 'link.py', // the url where we want to POST
          data        : ForgetFormData, // our data object
          dataType    : 'json', // what type of data do we expect back from the server
          encode      : true
        });
      });
      });


        $('#login-form').submit(function(event) {
              $('.form-group').removeClass('has-error'); // remove the error class
              $('.help-block').remove(); // remove the error text
              var LoginFormData = {
                  'UserName'      : $('input[name=UserName]').val(),
                  'Password'      : $('input[name=Password]').val(),
              };
              $.ajax({
                  type        : 'POST', 
                  url         : '/Login/', 
                  data        : LoginFormData,
                  dataType        : 'json', 
                  success: function(response) {
                  event.preventDefault();
                    setTimeout(function () {waitingDialog.hide();}, 100);
                    if (response.message=="Invalid UserName or Password") 
                    {
                      document.getElementById("ErrorMessageLogin").style.display = "block";
                      window.location.href="#";
                    }
                    else
                    { 
                    // alert(response.message);
                      window.location.href="#";
                      // console.log(response.Id);
                      waitingDialog.show('Signing In...');setTimeout(function () {waitingDialog.hide();}, 3000);
                        localStorage.setItem("Type",JSON.stringify("Member"));
                      if (response.WorkArea=="doc3") {
                        localStorage.setItem("DoctorId",JSON.stringify(response.Id));
                        localStorage.setItem("DoctorName",JSON.stringify(response.name));
                        localStorage.setItem("DoctorQualification",JSON.stringify(response.quali));
                        localStorage.setItem("DoctorAddress",JSON.stringify(response.address));
                        localStorage.setItem("DoctorTimings",JSON.stringify(response.timings));
                        localStorage.setItem("DoctorEmailid",JSON.stringify(response.emailid));
                        localStorage.setItem("DoctorFees",JSON.stringify(response.fees));
                        window.location.href="/DoctorProfile";

                      } 
                      else if(response.WorkArea=="medi4") {
                        localStorage.setItem("ChemistId",JSON.stringify(response.Id));
                        localStorage.setItem("ChemistName",JSON.stringify(response.name));
                        localStorage.setItem("ChemistContactNo",JSON.stringify(response.telno));
                        localStorage.setItem("ChemistAddress",JSON.stringify(response.address));
                        localStorage.setItem("ChemistEmailid",JSON.stringify(response.emailid));
                        window.location.href="/ChemistProfile";
                      }
                      else if(response.WorkArea=="hospital1") {
                        localStorage.setItem("HospitalId",JSON.stringify(response.Id));
                        localStorage.setItem("HospitalName",JSON.stringify(response.name));
                        localStorage.setItem("HospitalContactNo",JSON.stringify(response.contact));
                        localStorage.setItem("HospitalAddress",JSON.stringify(response.address));
                        localStorage.setItem("HospitalEmailid",JSON.stringify(response.emailid));
                        window.location.href="/HospitalProfile";
                      }
                      else if (response.WorkArea=="diag1") {
                        localStorage.setItem("DiagnosticId",JSON.stringify(response.Id));
                        localStorage.setItem("DiagnosticName",JSON.stringify(response.name));
                        localStorage.setItem("DiagnosticAddress",JSON.stringify(response.address));
                        localStorage.setItem("DiagnosticTiming",JSON.stringify(response.timings));
                        localStorage.setItem("DiagnosticWebsite",JSON.stringify(response.website));
                        window.location.href="/DiagnosticProfile";
                      }
                    }

                  },
                  error: function(error) {
                    event.preventDefault();
                    alert(error);
                    setTimeout(function () {waitingDialog.hide();}, 500);
                    waitingDialog.show('Something went wrong.Try later...!!!');setTimeout(function () {waitingDialog.hide();}, 5000);
                    window.location.href="#";
                  }
          });
        });

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
            url         : '/Feedback1/', 
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

    $('#SupportForm').submit(function(event) {
      alert("Hello");
      $('.form-group').removeClass('has-error'); 
      $('.help-block').remove(); 
      // get the form data
      var SupportFormData = {
        'UserName'  : $('input[name=name]').val(),
        'UserEmail' : $('input[name=email]').val(),
        'Subject' : $('input[name=Subject]').val(),
        'UserMessage' : $('input[name=UserMessage]').val(),
      };
      // process the form
      event.stopPropagation();
      $.ajax({
        type    : "POST", 
        url     : '/Support1/',
        data    : SupportFormData,
        dataType  : 'json', 
        success: function(reponse) {
          console.log(reponse);
          alert("Submit successfully");
        },
        error: function(error) {
          alert("Something went wrong! Try Again.");
          console.log(error);
        }
      })
  });

      
var waitingDialog = waitingDialog || (function ($) {
    'use strict';

  // Creating modal dialog's DOM
  var $dialog = $(
    '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
    '<div class="modal-dialog modal-m">' +
    '<div class="modal-content">' +
      '<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
      '<div class="modal-body">' +
        '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
      '</div>' +
    '</div></div></div>');

  return {
    /**
     * Opens our dialog
     * @param message Custom message
     * @param options Custom options:
     *          options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
     *          options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
     */
    show: function (message, options) {
      // Assigning defaults
      if (typeof options === 'undefined') {
        options = {};
      }
      if (typeof message === 'undefined') {
        message = 'Loading';
      }
      var settings = $.extend({
        dialogSize: 'm',
        progressType: '',
        onHide: null // This callback runs after the dialog was hidden
      }, options);

      // Configuring dialog
      $dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
      $dialog.find('.progress-bar').attr('class', 'progress-bar');
      if (settings.progressType) {
        $dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
      }
      $dialog.find('h3').text(message);
      // Adding callbacks
      if (typeof settings.onHide === 'function') {
        $dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
          settings.onHide.call($dialog);
        });
      }
      // Opening dialog
      $dialog.modal();
    },
    /**
     * Closes dialog
     */
    hide: function () {
      $dialog.modal('hide');
    }
  };

})(jQuery);
