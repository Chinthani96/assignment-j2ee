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

            var postsHtml = '';

            response.forEach(function (post) {
                postsHtml = postsHtml + "<article class=\"secondcontent\">\n" +
                    "                    <header>\n" +
                    "                        <div class=\"row\">\n" +
                    "                            <div class=\"col-sm-10\"><a href=\"#\"><h4 id=\"Company1\">" + post.cretaedUser.username + "</h4></a></div>\n" +
                    "                            <div class=\"col-sm-2\"><a href=\"#\"><h6 id=\"Company1\">Options</h6></a></div>\n" +
                    "                        </div>\n" +
                    "                        <p class=\"post-info\">" + post.title + " </p>\n" +
                    "                    </header>\n" +
                    "                    <content>\n" +
                    "                        <p class=\"captions\" id=\"caption1\">" + post.description + "</p>\n" +
                    "                        <img src=\"" + post.imagePath + "\" height=\"250x\" width=\"100%\">\n" +
                    "                        <br>\n" +
                    "                        <br>\n" +
                    "                        <p class=\"post-info\"><a href=\"#\">100 likes . 4 comments</a></p>\n" +
                    "                        <hr>\n" +
                    "                        <div class=\"row\">\n" +
                    "                            <div class=\"col-sm-4\" id=\"icons\">\n" +
                    "                                <button type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-thumbs-up\"></i> Like\n" +
                    "                                </button>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-sm-4\" id=\"icons\">\n" +
                    "                                <button type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-comment-alt\"></i> Comment\n" +
                    "                                </button>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-sm-4\" id=\"icons\">\n" +
                    "                                <button type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-share\"></i> Share\n" +
                    "                                </button>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </content>\n" +
                    "                </article>\n" +
                    "                <br>"
            })

            var eventsHtml = '';
            response.forEach(function (post){
                eventsHtml = eventsHtml + "<article class=\"secondcontent\">\n" +
                    "                    <header>\n" +
                    "\n" +
                    "                    </header>\n" +
                    "                    <content>\n" +
                    "                        <div class=\"event\">\n" +
                    "                            <a href=\"#\"><h3 id=\"event2\">"+post.eventName +"</h3></a>\n" +
                    "\n" +
                    "                            <p id=\"details2\">"+post.eventDetails +"</p>\n" +
                    "                            <p id=\"date2\">"+post.eventDate +"</p>\n" +
                    "                            <br>\n" +
                    "                            <p id=\"venue\">"+ post.eventVenue+ "</p>\n" +
                    "                        </div>\n" +
                    "                    </content>\n" +
                    "                </article>"
            })


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