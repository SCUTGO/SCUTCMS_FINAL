function GetJsonDataFromAdd() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token"),
        "procureDate": $("#add_procureDate").val(),
        "username": $("#add_pusername").val(),
        "amount": $("#add_amount").val(),
        "productName": $("#add_productName").val(),
        "unit_price": $("#add_unit_price").val()
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
        "procureDate": $("#update_procureDate").val(),
        "username": $("#update_userName").val(),
        "amount": $("#update_amount").val(),
        "productName": $("#update_productName").val(),
        "unit_price": $("#update_unitPrice").val()
    };
    return json;
}

function GetJsonDataFromDelete() {
    var json = {
        "rootUser":getCookie("rootUser"),
        "token":getCookie("token"),
        "productName": $("#delete_productName").val()
    };
    return json;
}
function shopping() {
    var test_json;
    console.log(GetJsonDataForInfo());

    $.ajax({
        type: "GET",
        url: "/ProcurementManagement",
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
    for(var i = 0; i < test_json.procurement.length;i++){
        html += '<tr>';
        html+='<td>';
        html+=test_json.procurement[i].procureDate;
        html+='</td>';
        html+='<td>';
        html+=test_json.procurement[i].username;
        html+='</td>';
        html+='<td>';
        html+=test_json.procurement[i].amount;
        html+='</td>';
        html+='<td>';
        html+=test_json.procurement[i].productName;
        html+='</td>';
        html+='<td>';
        html+=test_json.procurement[i].unit_price;
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
        url: "/ProcurementManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromAdd()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='shopping.html';
});

$("#btn_update").click(function(){
    console.log(GetJsonDataFromUpdate());
    $.ajax({
        type: "PUT",
        url: "/ProcurementManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromUpdate()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='shopping.html';
});

$("#btn_delete").click(function(){
    console.log("hello!!");
    console.log(GetJsonDataFromDelete());
    $.ajax({
        type: "DELETE",
        url: "/ProcurementManagement",
        async:false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(GetJsonDataFromDelete()),
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
    window.location.href='shopping.html';
});


function updateInfo(obj){
    var tds=$(obj).parent().parent().find('td');
    $('#update_procureDate').val(tds.eq(0).text());
    $('#update_userName').val(tds.eq(1).text());
    $('#update_amount').val(tds.eq(2).text());
    $('#update_productName').val(tds.eq(3).text());
    $('#update_unitPrice').val(tds.eq(4).text());
    $('#updateOrder').modal('show');
}
function deleteInfo(obj){
    var tds=$(obj).parent().parent().find('td');
    $('#delete_productName').val(tds.eq(3).text());
    $('#deleteOrder').modal('show');
}