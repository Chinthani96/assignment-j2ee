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

            var mainEventHtml ='';
             response(function (post){
                 mainEventHtml = mainEventHtml + "<article class=\"secondcontent\">\n" +
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

            var eventsHtml = '';

            response.forEach(function (post) {
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
                .error(function (response) {

                })
        })
});
