$(document).ready(function () {
    const base_email = "test@rsatu.ru";
    const base_password = "qwerty";

    // $('form').submit(function(event) {
    //
    //     // get the form data
    //     // there are many ways to get this data using jQuery (you can use the class or id also)
    //     var formData = {
    //         'email'              : $('input[name=email]').val(),
    //         'password'             : $('input[name=password]').val(),
    //     };
    //
    //     // process the form
    //     $.ajax({
    //         type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
    //         url         : 'login', // the url where we want to POST
    //         data        : formData, // our data object
    //         dataType    : 'json', // what type of data do we expect back from the server
    //         encode          : true
    //     })
    //         // using the done promise callback
    //         .done(function(data) {
    //             // log data to the console so we can see
    //             console.log(data);
    //
    //             // here we will handle errors and validation messages
    //         });
    //
    //     // stop the form from submitting the normal way and refreshing the page
    //     event.preventDefault();
    // });

    $("button").click(function (e) {
        // e.preventDefault();
        //
        if (isEmail($("#email").val())) {
            var formData = {
                'email': $('input[name=email]').val(),
                'password': $('input[name=password]').val(),
            };
            $.post( "login", JSON.stringify(formData)).done(function(data) {
                var json = JSON.parse(data)
                if (json.location) {
                    // data.redirect contains the string URL to redirect to
                    location.href = json.location;
                }
            }).fail(function () {
                showEmailError()
                showPasswordError()
            })



            // $.ajax({
            //     type: "POST",
            //     url: "login",
            //     data: JSON.stringify(formData),
            //     dataType: "json",
            //     success: function(data, textStatus) {
            //         alert(data.location)
            //         if (data.redirect) {
            //             window.location.href = data.redirect;
            //         } else {
            //             alert("неполучилося")
            //         }
            //     }
            // });
            // $.ajax({
            //     url: 'login',
            //     type: 'post',
            //     dataType: 'json',
            //     data: JSON.stringify(formData),
            //     headers: {
            //         // "content-type": "text/plain;charset=UTF-8" // Add this line
            //         // 'content-type': 'application/json;charset=UTF-8' // Or add this line
            //     },
            // })
            // $('#form').ajaxForm();
        } else {

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
    $(".pas .input").focus(function () {
        hidePasswordError()
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
            begin: function () {
                document.querySelector('.log .error').style.display = 'block';
            },
        });
    }

    function showPasswordError() {
        $(".pas .error-msg").text("Неправильный пароль")
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
    function hideEmailError() {
        anime({
            targets: '.log .error',
            // content: 'Некорректный email',
            opacity: 0,
            height: '0',
            duration: 400,
            begin: function () {
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
    function isEmail(email) {
        var regex = /^([a-zA-Z]{1})+([a-zA-Z0-9_.])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,10})+$/;
        return regex.test(email);
    }
});
