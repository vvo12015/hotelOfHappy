$(document).ready(
    function() {
        var now = new Date();
        var month = (now.getMonth() + 1);
        var day = now.getDate();
        if (month < 10)
            month = "0" + month;
        if (day < 10)
            day = "0" + day;
        var today = now.getFullYear() + '-' + month + '-' + day;
        $('#startDate').val(today);
        $('#finishDate').val(today);
        $('#startDate').attr("min", today);
        $('#finishDate').attr("min", today);
    });
$(document).ready(
    function(){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/categories",
            data: '',
            dataType: 'json',
            timeout: 600000,
            success:
                function(response){
                    $.each(response, function (i) {
                        var li = $("<li></li>");
                        li.attr('data-target', "#myCarousel");
                        li.attr('data-slide-to', i);
                        var div = $("<div></div>");
                        div.addClass("item");
                        if (i == 0) {
                            div.addClass("active");
                            li.addClass("active");
                        }
                        var img = $("<img></img>");
                        img.attr("src", "images/" + response[i].image);
                        img.attr("style", "width:100%;height:550px;");
                        div.append(img);
                        var subDiv = $("<div></div>");
                        subDiv.addClass("carousel-caption");
                        var header = "<h3>" + response[i].name + "</h3>";
                        subDiv.append(header);
                        div.append(subDiv);
                        $(".carousel-indicators").append(li);
                        $(".carousel-inner").append(div);
                    });
                }
        });
    }
);