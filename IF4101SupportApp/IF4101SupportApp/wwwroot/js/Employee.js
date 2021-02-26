$(document).ready(function () {
    $("#Employee_Form").validate({ // initialize the plugin
        rules: {
            EmployeeName: {
                required: true,
            },
            FirstSurname: {
                required: true
            },
            SecondSurname: {
                required: true
            },
            Email: {
                required: true,
                email: true
            },
            Password: {
                required: true,
                minlength: 8
            },
            Services: {
                required: true
            },
            EmployeeType: {
                required: true
            }
        }
    });

    $("#UEmployee_Form").validate({ // initialize the plugin
        rules: {
            UEmployeeName: {
                required: true,
            },
            UFirstSurname: {
                required: true
            },
            USecondSurname: {
                required: true
            },
            UEmail: {
                required: true,
                email: true
            },
            UEmployeeType: {
                required: true
            }
        }
    });

    $(".Select").selectpicker();

    LoadEmployeeTableData();
    
});


function LoadEmployeeTableData() {
    $('#table-employees').DataTable({
        "destroy": true,
        ajax: {
            url: '/Employee/GetAll',
            dataSrc: '',

        },
        columns: [
            {
                "render": function (data, type, s, meta) {

                    return '<div class="dropdown"><button class= "btn btn-sm btn-icon" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
                        '<i class="fa fa-ellipsis-h" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Actions"></i>' +
                        '</button>' +
                        '<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">' +
                        '<a class="dropdown-item" ><i class="fa fa-eye mr-2" onclick=editEmployee(' + s.employeeId + ')></i> Edit Profile</a>' +
                        '<a class="detele dropdown-item text-danger" onclick=deleteEmployee(' + s.employeeId + ') ><i class="fa fa-trash mr-2 "></i> Remove</a>' +
                        '</div>' +
                        '</div>'
                }
            },
            { data: 'employeeId' },
            { data: 'employeeName' },
            { data: 'firstSurname' },
            { data: 'secondSurname' },
            { data: 'email' }
        ]
    });
}

$('#table-employees tbody').on('click', 'tr', function () {
    var table = $('#table-employees').DataTable(); 
    table.$('tr.selected').removeClass('selected');
    $(this).addClass('selected');
    
});



$("#Employee_Form").submit(function (e) {
    e.preventDefault();
    if ($("#Employee_Form").valid()) {
        var interval = null;
        $.ajax({
            url: "/Employee/Insert",
            data: {
                EmployeeName: $("#EmployeeName").val(),
                FirstSurname: $("#FirstSurname").val(),
                SecondSurname: $("#SecondSurname").val(),
                Email: $("#Email").val(),
                Password: $("#Password").val(),
                Supervised: $("#Supervised").val(),
                Services: $("#Services").val(),
                EmployeeType: $("#EmployeeType").val()
            },
            type: "POST",
            dataType: "json",
            beforeSend: function () {
                i = 0;
                $("#employee-btn").prop("disabled", true);
                interval = setInterval(function () {
                    i = ++i % 4;
                    $("#employee-btn").html("Processing" + Array(i + 1).join("."));
                }, 500);
            },
            success: function (result) {
                clearInterval(interval);
                $("#employee-btn").prop("disabled", false);
                $("#employee-btn").html("Register");
                LoadEmployeeTableData();
            },
            error: function (errorMessage) {
                clearInterval(interval);
                $("#employee-btn").html("Error, Try Again!");
                $("#employee-btn").css("background-color", "red");
                setTimeout(function () {
                    $("#employee-btn").prop("disabled", false);
                    $("#employee-btn").html("Register");                   
                    $("#employee-btn").css("background-color", "#7f5feb");
                }, 3000);    
            }
        });
    }
});

$("#UEmployee_Form").submit(function (e) {
    e.preventDefault();
    alert($(this).valid());
    if ($(this).valid()) {
        var interval = null;
        $.ajax({
            url: "/Employee/Update",
            data: {
                EmployeeId: $("#UEmployeeId").val(),
                EmployeeName: $("#UEmployeeName").val(),
                FirstSurname: $("#UFirstSurname").val(),
                SecondSurname: $("#USecondSurname").val(),
                Email: $("#UEmail").val(),
                EmployeeType: $("#UEmployeeType").val()
            },
            type: "PUT",
            dataType: "json",
            beforeSend: function () {
                i = 0;
                $("#UEmployee-btn").prop("disabled", true);
                interval = setInterval(function () {
                    i = ++i % 4;
                    $("#UEmployee-btn").html("Processing" + Array(i + 1).join("."));
                }, 500);
            },
            success: function (result) {
                clearInterval(interval);
                $("#UEmployee-btn").prop("disabled", false);
                $("#UEmployee-btn").html("Update");
                LoadEmployeeTableData();
                $("#DetailEmployeeModal").modal("hide");
            },
            error: function (errorMessage) {
                clearInterval(interval);
                $("#UEmployee-btn").html("Error, Try Again!");
                $("#UEmployee-btn").css("background-color", "red");
                setTimeout(function () {
                    $("#UEmployee-btn").prop("disabled", false);
                    $("#UEmployee-btn").html("Add Comment");
                    $("#UEmployee-btn").css("background-color", "#7f5feb");
                }, 4000);
            }
        });
    }
});


function editEmployee (id){
    $.ajax({
        url: "/Employee/GetById",
        data: {
            id: id
        },
        type: "GET",
        success: function (result) {
            $("#UEmployeeId").val(result.employeeId);
            $("#UEmployeeName").val(result.employeeName);
            $("#UEmail").val(result.email);
            $("#UFirstSurname").val(result.firstSurname);
            $("#USecondSurname").val(result.secondSurname);
            $("#USupervised").val(result.supervised);
            $("#UEmployeeType").val(result.employeeType);
            $('.selectpicker').selectpicker('refresh');
            $("#DetailEmployeeModal").modal("show");
            
        },
        error: function (errorMessage) {
            
        }
    });
}

function deleteEmployee(id) {
    alert(id);
    var table = $('#table-employees').DataTable();
    $.ajax({
        url: "/Employee/Delete",
        data: {
            Id: id 
        },
        type: "DELETE",
        dataType: "json",
        success: function (result) {
            table.row(".selected").remove().draw();
        },
        error: function (errorMessage) {
            
        }
    });
}