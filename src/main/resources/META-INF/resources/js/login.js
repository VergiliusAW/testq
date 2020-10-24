
$(document).ready(function(){
    const base_email = "test@rsatu.ru";
    const base_password = "qwerty";

    $("button").click(function(e) {
        //
        if (isEmail($("#email").val())) {
            // e.default();
            // if (login($("#email").val(),$("#password").val())) {
            //     location.href = '/'
            // } else {
            //     showPasswordError()
            //     showEmailError()
            //     // swal("ОШИБКА", "Неправильный логин или пароль")
            // }
        } else {
            e.preventDefault();
            showEmailError()
            // const popcorn = document.querySelector('#popcorn');
            // const tooltip = document.querySelector('#tooltip');
            // createPopper(popcorn, tooltip, {
            //     placement: 'bottom-start',
            // });
        }
        
    });
    $(".log .input").focus(function () {
        hideEmailError()
    })
    // $(".pas .input").focus(function () {
    //     hidePasswordError()
    // })
    function showEmailError() {
        $(".log .error-msg").text("Некорректный email")
        anime({
            targets: '.log .error',
            // content: 'Некорректный email',
            opacity: 1,
            height: '30',
            duration: 400,
            // easing: 'easeInSine',
            begin: function() {
                document.querySelector('.log .error').style.display = 'block';
            },
        });
    }
    // function showPasswordError() {
    //     $(".pas .error-msg").text("Неправильный пароль")
    //     anime({
    //         targets: '.pas .error',
    //         opacity: 1,
    //         height: '30',
    //         duration: 400,
    //         begin: function() {
    //             document.querySelector('.pas .error').style.display = 'block';
    //         },
    //     });
    // }
    function hideEmailError() {
        anime({
            targets: '.log .error',
            // content: 'Некорректный email',
            opacity: 0,
            height: '0',
            duration: 400,
            begin: function() {
                document.querySelector('.log .error').style.display = 'none';
            },
        });
    }
    // function hidePasswordError() {
    //     anime({
    //         targets: '.pas .error',
    //         opacity: 1,
    //         height: '0',
    //         duration: 400,
    //         begin: function() {
    //             document.querySelector('.pas .error').style.display = 'none';
    //         },
    //     });
    // }
    function isEmail(email) {
        var regex = /^([a-zA-Z]{1})+([a-zA-Z0-9_.])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,10})+$/;
        return regex.test(email);
    }
  });
