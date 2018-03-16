    function populateSelect(array, selectName){
            $(selectName + " option").remove();
            $.each(array, function(i, val){
                $(selectName).append($("<option></option>").attr("value", val).text(val));
            });
    };
    function showOrdersOfUser(user){
        $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/showOrdersOfUser",
                data: JSON.stringify(user),
                dataType: 'json',
                timeout: 600000,
                success: function(response){
                    $.each(response.data, function(i){
                        var tdStartDate = $("<td>");
                        tdStartDate.text(response.data[i].startDate);
                        var tdFinishDate = $("<td>");
                        tdFinishDate.text(response.data[i].finishDate);
                        var tdCategory = $("<td>");
                        tdCategory.text(response.data[i].categoryName);
                        var valServices = "";
                        $.each(response.data[i].serviceList, function(j){
                            valServices += response
                            .data[i].serviceList[j] + "; ";
                        })
                        var tdPrice = $("<td>");
                        tdPrice.text(response.data[i].totalPrice);
                        var tr = $("<tr>");
                        tr.append(tdStartDate);
                        tr.append(tdFinishDate);
                        tr.append(tdCategory);
                        tr.append(valServices);
                        $("#orderTable").append(tr);
                        $("#divTable").show();
                    })
                },
                error: function (e){
                    $("#container").html("<h1>Ajax error</h1>");
                }
        });
    }
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
                    data["numberRoom"] = $("#freeRooms").val();
                    while ($("#firstName").val() == ''){
                        $("#firstName").val(prompt('Enter firstName'));
                    }
                    data["firstName"] = $("#firstName").val();
                    while ($("#lastName").val() == ''){
                        $("#lastName").val(prompt('Enter lastName'));
                    }
                    data["lastName"] = $("#lastName").val();
                    data["services"] = $("#services").val();
                    data["totalSum"] = $("#totalSum").text();
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/create-order",
                        data: JSON.stringify(data),
                        dataType: 'json',
                        timeout: 600000,
                        success: function(response){
                            if (response.data.freeRoomOfCategory == null){
                            $.each(response.data, function(i){
                                var tdStartDate = $("<td>");
                                tdStartDate.text(response.data[i].startDate);
                                var tdFinishDate = $("<td>");
                                tdFinishDate.text(response.data[i].finishDate);
                                var tdCategory = $("<td>");
                                tdCategory.text(response.data[i].categoryName);
                                var valServices = "";
                                $.each(response.data[i].serviceList, function(j){
                                    valServices += response
                                    .data[i].serviceList[j] + "; ";
                                })
                                var tdPrice = $("<td>");
                                tdPrice.text(response.data[i].totalPrice);
                                var tr = $("<tr>");
                                tr.append(tdStartDate);
                                tr.append(tdFinishDate);
                                tr.append(tdCategory);
                                tr.append(valServices);
                                $("#orderTable").append(tr);
                                $("#divTable").show();
                            })
                            }else{
                                populateSelect(response.data.freeRoomOfCategory, "freeRooms");
                                populateSelect(response.data.allFreeCategories, "freeCategories");
                            }
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
        $("#your-orders").click(function(){
            if ($("#divTable").attr("style") == "display: none;"){
                var  user = {};
                 while ($("#firstName").val() == ''){
                    $("#firstName").val(prompt('Enter firstName'));
                 }
                user["firstName"] = $("firstName").val();
                while ($("#lastName").val() == ''){
                    $("#lastName").val(prompt('Enter lastName'));
                }
                user["lastName"] = $("lastName").val();
                showOrdersOfUser(user);
                $("#divTable").show();
                $("#divTable").text = "Hide your orders";
            }else{
                $("#divTable").hide();
                $("#divTable").text = "Show your orders";
            }

        });
    });
