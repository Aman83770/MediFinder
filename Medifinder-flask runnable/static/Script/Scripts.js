
$(document).ready(function(){/* activate scrollspy menu */
$('body').scrollspy({
  target: '#navbar-collapsible',
  offset: 50
});


// // Javascript to enable link to tab
// var url = document.location.toString();
// if (url.match('#')) {
//     $('.nav-tabs a[href=#'+url.split('#')[1]+']').tab('show') ;
// } 

// // Change hash for page-reload
// $('.nav-tabs a').on('shown.bs.tab', function (e) {
//     window.location.hash = e.target.hash;
//     console.log('Tab clicked');
// })

/* smooth scrolling sections */
$('a[href*=#]:not([href=#])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top - 50
        }, 1000);
        return false;
      }
    }
});


// $('#PasswordConfirm').on('input', function() {
//   buttonDisableToggle($('#Password').val(), $('#PasswordConfirm').val());
// });

// $('#Password').on('input', function() {
//   buttonDisableToggle($('#Password').val(), $('#PasswordConfirm').val());
// });

// function buttonDisableToggle(passVal, passConfirmVal) {
//   passVal !== passConfirmVal ? $('#signup-submit-btn').attr('disabled', true) :  $('#signup-submit-btn').attr('disabled', false);
// }

// });
});


