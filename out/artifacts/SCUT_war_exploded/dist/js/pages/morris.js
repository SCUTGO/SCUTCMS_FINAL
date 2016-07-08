function GetJsonDataFromAdd() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token"),
        "positionName": $("#add_positionName").val(),
        "baseSalary": $("#add_baseSalary").val(),
        "wageHourly": $("#add_wageHourly").val(),
        "punishment": $("#add_punishment").val(),
    };
    return json;
}
function GetJsonDataForInfo() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token")
    };
    return json;
}

function GetJsonDataFromUpdate() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token"),
        "positionName": $("#update_positionName").val(),
        "baseSalary": $("#update_baseSalary").val(),
        "wageHourly": $("#update_wageHourly").val(),
        "punishment": $("#update_punishment").val(),
    };
    return json;
}

function GetJsonDataFromDelete() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token"),
        "positionName": $("#delete_positionName").val()
    };
    return json;
}
function test() {
    var test_json;
    console.log("hello!!!");
    console.log(GetJsonDataForInfo());
    $.ajax({
        type: "GET",
        url: "/PositionManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataForInfo()),
        dataType: "json",
        success: function (data) {
            console.log(data);
            test_json=data;
            //      console.log(test_json);
            test_json=eval("("+data+")");
            //     console.log(test_json);
        }
    });
    if(test_json.state=="FAIL")
        window.location.href='../login.html';
    document.getElementById("RootUsername").innerHTML =getCookie("rootUser");
    document.getElementById("RootUsernamea").innerHTML =getCookie("rootUser");
    var html = '';
    for(var i = 0; i < test_json.positions.length;i++){
        html += '<tr>';
        html+='<td>';
        html+=test_json.positions[i].positionName;
        html+='</td>';
        html+='<td>';
        html+=test_json.positions[i].baseSalary;
        html+='</td>';
        html+='<td>';
        html+=test_json.positions[i].wageHourly;
        html+='</td>';
        html+='<td>';
        html+=test_json.positions[i].punishment;
        html+='</td>';
        html+='<td><button name="update" value="update" class="btn btn-info" onclick="updateInfo(this);">修改</button><button name="delete" value="delete" class="btn btn-danger" onclick="deleteInfo(this);">删除</button></td>';
        html+='</tr>';
    }
    document.getElementById("table_body").innerHTML=html;
};


$("#sign_out").click(function () {
    console.log("deleteCookie");
    console.log(getCookie("rootUser"));
    delCookie("rootUser");
    delCookie("token");
})


$("#btn_submit").click(function() {
    console.log(GetJsonDataFromAdd());
    $.ajax({
        type: "POST",
        url: "/PositionManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromAdd()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='morris.html';
});

$("#btn_update").click(function(){
    console.log(GetJsonDataFromUpdate());
    $.ajax({
        type: "PUT",
        url: "/PositionManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromUpdate()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='morris.html';
});

$("#btn_delete").click(function(){
    console.log("hello!!");
    console.log(GetJsonDataFromDelete());
    $.ajax({
        type: "DELETE",
        url: "/PositionManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromDelete()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='morris.html';
});


function updateInfo(obj){
    var tds=$(obj).parent().parent().find('td');
    $('#update_positionName').val(tds.eq(0).text());
    $('#update_baseSalary').val(tds.eq(1).text());
    $('#update_wageHourly').val(tds.eq(2).text());
    $('#update_punishment').val(tds.eq(3).text());
    $('#updatePosition').modal('show');
}
function deleteInfo(obj){
    var tds=$(obj).parent().parent().find('td');
    $('#delete_positionName').val(tds.eq(0).text());
    $('#deletePosition').modal('show');
}