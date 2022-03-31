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
})
