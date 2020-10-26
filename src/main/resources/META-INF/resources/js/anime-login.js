$(document).ready(function () {

    if ($("#email").val() !== '') {
        animeUp('.log .focus-input')
    }
    if ($("#password").val() !== '') {
        animeUp('.pas .focus-input')
    }

    $(".input").focus(function () {
        if ($(this).attr('id') === 'email') {
            animeUp('.log .focus-input')
        }
        if ($(this).attr('id') === 'password') {
            animeUp('.pas .focus-input')
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