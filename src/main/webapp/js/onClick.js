    function populateSelect(array, selectName){
            $(selectName + " option").remove();
            $.each(array, function(i, val){
                $(selectName).append($("<option></option>").attr("value", val).text(val));
            });
    };
    function populateListRoomsAndCategories(response){
        $("option").remove();
        $.each(response.data.freeRoomOfCategory, function(i, val){
            $("#freeRooms").append($("<option></option>").attr("value", val).text(val));
        });

        $.each(response.data.allFreeCategories, function(i){
            var categoryName = response.data.allFreeCategories[i];
            $("#freeCategories").append($("<option></option>").attr("value", categoryName).text(categoryName));
        });
    };
    function requestForLists(){
        var data = {};
        data["startDate"] = $("#startDate").val();
        data["finishDate"] = $("#finishDate").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/free-rooms-categories",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 600000,
            success: function(response){
                populateListRoomsAndCategories(response);
            },
            error: function (e){
                $("#container").html("<h1>Ajax error</h1>");
            }
        });
    };
    $(document).ready(function(){
        $('#rooms').click(function () {
            $.ajax({
                type: "GET",
                cache: false,
                url: '/rooms',
                data: "",
                success: function (response) {
                    var html = "";
                    $.each(response.data, function (i) {
                        html = html + response.data[i].number + "<br/>";
                    });
                    $('#container').html(html);
                }
            });
        });
        $('#click-create-order').click(function(){
            if ($('#orderFromForm').attr("style") == "display: none;"){
                $('#orderFromForm').show();
            }else{
                $('#orderFromForm').hide();
            }
        });
        $('#createOrder').click(function(){

                    var data = {};
                    data["startDate"] = $("#startDate").val();
                    data["finishDate"] = $("#finishDate").val();
                    data["numberRoom"] = $("#numberRoom").val();
                    data["firstName"] = $("#firstName").val();
                    data["lastName"] = $("#lastName").val();
                    data["totalSum"] = $("#totalSum").text();
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/create-order",
                        data: JSON.stringify(data),
                        dataType: 'json',
                        timeout: 600000,
                        success: function(response){
                            $("#info").text("Your booking successfully")
                        },
                        error: function (e){
                            $("#container").html("<h1>Ajax error</h1>");
                        }
                    });
        });
        $('#startDate').change(function() {
            if ($("#startDate").val() > $("#finishDate").val()){
                $("#finishDate").val($("#startDate").val());
            }
        });
        $('#checkRooms').click(function(){
            requestForLists();
        });
        $('#freeRooms').click(function(){
            var data = {};
            data["numberRoom"] = $("#freeRooms").val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/serviceOfRoom",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000,
                success: function(response){
                    $("#services" + " option").remove();
                    $.each(response.data.services, function(i, val){
                        $("#services").append($("<option></option>").attr("value", val.serviceId).text(val.name));
                    });
                    $("#totalSum").text("$" + response.data.priceRoom);
                },
                error: function (e){
                    $("#container").html("<h1>Ajax error</h1>");
                }
            });
        });
        $('#services').click(function(){
            var data = {};
            data["services"] = $("#services").val();
            data["numberRoom"] = $("#freeRooms").val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/totalSum",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000,
                success: function(response){
                    $("#totalSum").text("$" + response.data);
                },
                error: function (e){
                    $("#container").html("<h1>Ajax error</h1>");
                }
            });
        });
    });
