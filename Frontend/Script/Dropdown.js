$(function(){
                $(".dropdown").hover(            
                function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("slow");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
                },
function() {
            $('.dropdown-menu', this).stop( true, true ).fadeOut("slow");
            $(this).toggleClass('open');
            $('b', this).toggleClass("caret caret-up");                
            });

    $('a[href="#search"]').on('click', function(event) {
        event.preventDefault();
        // console.log('search');
        $('#search').addClass('open');
        $('#search > form > input[type="search"]').focus();
    });
    
    $('#search, #search button.close').on('click keyup', function(event) {
        if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
            $(this).removeClass('open');
        }
    });
    
    
    // $('form').submit(function(event) {
    //     event.preventDefault();
    //     return false;
    // })

});

// $(function () {
// });