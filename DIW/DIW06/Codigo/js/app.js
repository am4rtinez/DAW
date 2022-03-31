$(function () {
    const primaryNav = $(".primary-navigation");
    const navToggle = $(".nav-toggle");
    const navIcon = $('#nav-icon')

    navToggle.click(function (e) { 
        e.preventDefault();
        navIcon.toggleClass("fa-times");
        primaryNav.slideToggle();
    });

    $(".img-fadeout").click(function (e) { 
        e.preventDefault();
        $(this).fadeOut();
    });

    $('#restore').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeIn();
    });

    $('#fadetoggle').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeToggle(3000);
    });

    $("#fadeto").click(function(){
        let img =  $(".img-fadeout")
        console.log(img.attr('faded'))
        if (img.attr('faded') == "false"){
            img.fadeTo(1000, 0.4);
            img.attr('faded', true)
        } else {
            img.fadeTo(1000, 1);
            img.attr('faded', false)
        }
    });

    $('#hide').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").hide();
    });

    $('#show').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").show();
    });
})
