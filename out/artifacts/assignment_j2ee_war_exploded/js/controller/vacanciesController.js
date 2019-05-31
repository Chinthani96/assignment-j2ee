$(document).ready(function () {
    var username = localStorage.getItem("username");

    if (username === null || username === '') {
        window.location.href = "login.html";
        return;
    }

    $.ajax({
        url: url + "/posts?action=subscriptions&pgNo=1",
        headers: {
            username: localStorage.getItem("username"),
        },
        method: "get",
        async: false
    })
        .done(function (response) {
            if (response === null || response === "null") {
                alert("Sorry something went wrong! Please sign in again!");
                localStorage.clear();
                window.location.href = "login.html";
                return;
            }

            var vacanciesHtml = '';
            response.forEach(function (post){
                vacanciesHtml = vacanciesHtml + "<article class=\"secondcontent\">\n" +
                    "                    <header>\n" +
                    "                      \n" +
                    "                    </header>\n" +
                    "                    <content>\n" +
                    "                        <h4 id=\"designation2\"><a href=\"#\">"+post.Designation+"</a></h4>\n" +
                    "                        <h6 id=\"companyname2\">"+post.compName+"</h6>\n" +
                    "                        <p id=\"VacDetails\">"+post.vacDetails +"</p>\n" +
                    "\n" +
                    "                    </content>\n" +
                    "                </article>"
            })
                .error(function (response) {

                })
        })
});