$(document).ready(function(){
    const base_email = "test@rsatu.ru";
    const base_password = "qwerty";

    $("button").click(function(e) {
        //
        if (isEmail($("#email").val())) {
            if ($("#password").val()===$("#password-repeat").val()) {
                let formData = {
                    'email': $('input[name=email]').val(),
                    'password': $('input[name=password]').val(),
                };
                $.post( "register", JSON.stringify(formData)).done(function(data) {
                    let json = JSON.parse(data)
                    if (json.location) {
                        // data.redirect contains the string URL to redirect to
                        location.href = json.location;
                    }
                }).fail(function () {
                    showEmailError()
                    showPasswordError()
                })
            } else {
                showPasswordError()
                showPasswordRepeatError()
            }
            // if (login($("#email").val(),$("#password").val())) {
            //     location.href = '/'
            // } else {
            //     showPasswordError()
            //     showEmailError()
            //     // swal("ОШИБКА", "Неправильный логин или пароль")
            // }
        } else {
            showEmailError()
        }
        
    });
    $(".log .input").focus(function () {
        hideEmailError()
    })
    $(".pas .input").focus(function () {
        hidePasswordError()
    })
    $(".pas-repeat .input").focus(function () {
        hidePasswordError()
        hidePasswordRepeatError()
    })
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
    function showPasswordError() {
        $(".pas .error-msg").text("Пароли не совпадают")
        anime({
            targets: '.pas .error',
            opacity: 1,
            height: '30',
            duration: 400,
            begin: function() {
                document.querySelector('.pas .error').style.display = 'block';
            },
        });
    }
    function showPasswordRepeatError() {
        $(".pas .error-msg").text("Неправильный пароль")
        anime({
            targets: '.pas-repeat .error',
            opacity: 1,
            height: '30',
            duration: 400,
            begin: function() {
                document.querySelector('.pas .error').style.display = 'block';
            },
        });
    }
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
    function hidePasswordError() {
        anime({
            targets: '.pas .error',
            opacity: 1,
            height: '0',
            duration: 400,
            begin: function() {
                document.querySelector('.pas .error').style.display = 'none';
            },
        });
    }
    function hidePasswordRepeatError() {
        anime({
            targets: '.pas-repeat .error',
            opacity: 1,
            height: '0',
            duration: 400,
            begin: function() {
                document.querySelector('.pas .error').style.display = 'none';
            },
        });
    }
    function isEmail(email) {
        var regex = /^([a-zA-Z]{1})+([a-zA-Z0-9_.])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,10})+$/;
        return regex.test(email);
    }
    function login(email, password) {
        return (email==base_email)&&(password==base_password);
    }
  });
