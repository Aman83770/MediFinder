$(function() {
    
    var formLogin = $('#login-form');
    var formForgot = $('#forgot-form');
    var formRegister = $('#register-form');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    var showLogin = $('.show-login');
    showLogin.on('click', function() {
        formForgot.css('display', 'none');
        formRegister.css('display', 'none');
        formLogin.css('display', 'block');
    });

    var showForgotPassword = $('.show-forgot-password');
    showForgotPassword.on('click', function() {
        formLogin.css('display', 'none');
        formRegister.css('display', 'none');
        formForgot.css('display', 'block');
    });
    
    var showRegister = $('.show-register');
    showRegister.on('click', function() {
        formForgot.css('display', 'none');
        formLogin.css('display', 'none');
        formRegister.css('display', 'block');
    });

    // var showDoctor = $('.register-form');
    // showDoctor.on('click', function() {
    //     Speciality.css('display', 'block');
    //     store_name.css('display', 'none');
    //     hospital_name.css('display', 'none');
    //     diagnostic_name.css('display', 'none');
    // });

    // var showLogin = $('.show-login');
    // showLogin.on('click', function() {
    //     formForgot.css('display', 'none');
    //     formRegister.css('display', 'none');
    //     formLogin.css('display', 'block');
    // });

    // $("form").submit(function () {
    //     switch(this.id) {
    //         case "login-form":
    //             var $lg_username=$('#login_username').val();
    //             var $lg_password=$('#login_password').val();
    //             if ($lg_username == "ERROR") {
    //                 msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Login error");
    //             } else {
    //                 msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Login OK");
    //             }
    //             return false;
    //             break;
    //         case "lost-form":
    //             var $ls_email=$('#lost_email').val();
    //             if ($ls_email == "ERROR") {
    //                 msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "error", "glyphicon-remove", "Send error");
    //             } else {
    //                 msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "success", "glyphicon-ok", "Send OK");
    //             }
    //             return false;
    //             break;
    //         case "register-form":
    //             var $rg_username=$('#register_username').val();
    //             var $rg_email=$('#register_email').val();
    //             var $rg_password=$('#register_password').val();
    //             if ($rg_username == "ERROR") {
    //                 msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "error", "glyphicon-remove", "Register error");
    //             } else {
    //                 msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "success", "glyphicon-ok", "Register OK");
    //             }
    //             return false;
    //             break;
    //         default:
    //             return false;
    //     }
    //     return false;
    // });
    
    // $('#login_register_btn').click( function () { modalAnimate($formLogin, $formRegister) });
    // $('#register_login_btn').click( function () { modalAnimate($formRegister, $formLogin); });
    // $('#login_lost_btn').click( function () { modalAnimate($formLogin, $formLost); });
    // $('#lost_login_btn').click( function () { modalAnimate($formLost, $formLogin); });
    // $('#lost_register_btn').click( function () { modalAnimate($formLost, $formRegister); });
    // $('#register_lost_btn').click( function () { modalAnimate($formRegister, $formLost); });
    
    function modalAnimate ($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height", $oldH);
        $oldForm.fadeToggle($modalAnimateTime, function(){
            $divForms.animate({height: $newH}, $modalAnimateTime, function(){
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }
    
    function msgFade ($msgId, $msgText) {
        $msgId.fadeOut($msgAnimateTime, function() {
            $(this).text($msgText).fadeIn($msgAnimateTime);
        });
    }
    
    function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
        var $msgOld = $divTag.text();
        msgFade($textTag, $msgText);
        $divTag.addClass($divClass);
        $iconTag.removeClass("glyphicon-chevron-right");
        $iconTag.addClass($iconClass + " " + $divClass);
        setTimeout(function() {
            msgFade($textTag, $msgOld);
            $divTag.removeClass($divClass);
            $iconTag.addClass("glyphicon-chevron-right");
            $iconTag.removeClass($iconClass + " " + $divClass);
        }, $msgShowTime);
    }

    $('#WorkArea').on('change', function() {
        var selectedWorkArea = $('#WorkArea option:selected').val();
        if (selectedWorkArea === 'Doctor') {
            $('#Speciality').css('display', 'block');
            $('#StoreName').css('display', 'none');
            $('#HospitalName').css('display', 'none');
            $('#DiagnosticName').css('display', 'none');
        } 
        else if (selectedWorkArea === 'Chemist') {
            $('#Speciality').css('display', 'none');
            $('#StoreName').css('display', 'block');
            $('#HospitalName').css('display', 'none');
            $('#DiagnosticName').css('display', 'none');
        } 
        else if (selectedWorkArea === 'Hospital') {
            $('#Speciality').css('display', 'none');
            $('#StoreName').css('display', 'none');
            $('#HospitalName').css('display', 'block');
            $('#DiagnosticName').css('display', 'none');
        }
        else if (selectedWorkArea === 'Diagnostic Centre') {
            $('#Speciality').css('display', 'none');
            $('#StoreName').css('display', 'none');
            $('#HospitalName').css('display', 'none');
            $('#DiagnosticName').css('display', 'block');
        }
        else if (selectedWorkArea === 'none') {
            $('#Speciality').css('display', 'none');
            $('#StoreName').css('display', 'none');
            $('#HospitalName').css('display', 'none');
            $('#DiagnosticName').css('display', 'none');
        }
    });
    

});
    /* (@Avijit)TODO: Send AJAX request for login and then set cookie by $.cookie('t', 'doc/chem/hosp/dia') */

