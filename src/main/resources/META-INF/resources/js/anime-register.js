$(document).ready(function () {
    $(".input").focus(function () {
        if ($(this).attr('id') === 'email') {
            animeUp('.log .focus-input')
        }
        if ($(this).attr('id') === 'password') {
            animeUp('.pas .focus-input')
        }
        if ($(this).attr('id') === 'password-repeat') {
            animeUp('.pas-repeat .focus-input')
        }
    })
    //
    $(".input").focusout(function () {
        if ($("#email").val() !== '') {
            animeUp('.log .focus-input')
        } else {
            animeDown('.log .focus-input')
        }
        if ($("#password").val() !== '') {
            animeUp('.pas .focus-input')
        } else {
            animeDown('.pas .focus-input')
        }
        if ($("#password-repeat").val() !== '') {
            animeUp('.pas-repeat .focus-input')
        } else {
            animeDown('.pas-repeat .focus-input')
        }
    })
    function animeUp(a) {
        anime({
            targets: a,
            fontSize: 14,
            translateY: '-175%',
            duration: 100,
            easing: 'linear',
        })
    }
    function animeDown(a) {
        anime({
            targets: a,
            fontSize: 16,
            translateY: '0%',
            duration: 100,
            easing: 'linear',
        })
    }
});