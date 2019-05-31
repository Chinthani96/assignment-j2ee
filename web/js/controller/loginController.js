$(document).ready(function () {
    var username = localStorage.getItem("username");

    if (username !== null && username !== '') {
        window.location.href = "index.html";
    }

    // console.log("Hi");

    $("#loginBtn").click(function (event) {
        var username = $("#username-field").val();
        var password = $("#password-field").val();
        // console.log("hi reached here");

        $.ajax({
            url: url + "users",
            method: "post",
            data: {
                username: username,
                password: password,
                isLogin: true
            },
            async: true
        }).done(function (response) {
            if (response.login_status === "SUCCESS") {
                localStorage.setItem("username", response.username);
                localStorage.setItem("user_type", response.user_type);
                localStorage.setItem("created_date", response.created_date);
                localStorage.setItem("lastLoginDate", response.lastLoginDate);

                window.location.href = "index.html";

            } else {

            }
        });
    })
});