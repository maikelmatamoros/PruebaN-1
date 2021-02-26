$("#note-form").submit(function (e) {
    e.preventDefault();
    var interval = null;
    $.ajax({
        url: "/Note/Insert",
        cache: false,
        type: "Post",
        data: {
            ReportNumber: sessionStorage.ReportNumber,
            Description: $(this).find("#note-description").val()
        },
        beforeSend: function () {
            i = 0;
            $("#btn-save-note").prop("disabled", true);
            interval = setInterval(function () {
                i = ++i % 4;
                $("#btn-save-note").html("Processing" + Array(i + 1).join("."));
            }, 500);
        },
        beforeSend: function () {
            i = 0;
            $("#btn-save-note").prop("disabled", true);
            interval = setInterval(function () {
                i = ++i % 4;
                $("#btn-save-note").html("Processing" + Array(i + 1).join("."));
            }, 500);
        },
        success: function (data) {
            clearInterval(interval);
            $("#btn-save-note").prop("disabled", false);
            $("#btn-save-note").html("Add Note");
            $('#content-note').append(data);
            
        },
        error: function (error) {

            clearInterval(interval);
            $("#btn-save-note").html("Error, Try Again!");
            $("#btn-save-note").css("background-color", "red");
            setTimeout(function () {
                $("#btn-save-note").prop("disabled", false);
                $("#btn-save-note").html("Add Note");
                $("#btn-save-note").css("background-color", "#7f5feb");
            }, 4000);

        }
    });
});

$("#notes-tab").on("click", function () {
    $.ajax({
        url: "/Note/NotesByIssue",
        cache: false,
        type: "GET",
        data: {
            id: sessionStorage.ReportNumber
        },
        beforeSend: function () {
            $("#content-note").empty();
            
        },
        success: function (data) {
            
            $('#content-note').html(data);
            
        },
        error: function (error) {
            
           
        }
    });

});

function deleteNote(id) {
    $.ajax({
        url: "/Note/Delete",
        cache: false,
        type: "Delete",
        data: {
            NoteId: id
        },
        beforeSend: function () {
            $("#note-" + id).find(".card-header").find(".btn-div").html(
                '<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>'
            );

        },
        success: function (data) {
            $("#note-" + id).parent().remove();
        },
        error: function (error) {
            $("#note-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="saveNote( ' + id + ' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="backNote( ' + id + ' );"><span><i class="fa fa-undo"></i></span></a>'
            );
        }
    });
}

function editNote(id) {
    sessionStorage.setItem("note", $("#note-" + id).find(".card-body").find('p').html());
    $("#note-" + id).find(".card-header").find(".btn-div").html(
        '<a href="javascript: void(0)" onclick="saveNote( ' + id +' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
        '<a href="javascript: void(0)" onclick="backNote( ' + id +' );"><span><i class="fa fa-undo"></i></span></a>'
    );
    
    $("#note-" + id).find(".card-body").html(
        '<textarea id="note-description" class="form-control" rows="3">' +
            sessionStorage.note+
        '</textarea>');
}

function saveNote(id) {
    var newDescription = $("#note-" + id).find(".card-body").find("#note-description").val();
   
    $.ajax({
        url: "/Note/Update",
        cache: false,
        type: "PUT",
        dataType: "json",
        data: {
            NoteId: id,
            Description: newDescription
        },
        beforeSend: function () {
            $("#note-" + id).find(".card-header").find(".btn-div").html(
                '<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>'
            );
            
        },
        success: function (data) {
            $("#note-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="editNote( ' + id + ' );"><span><i class="fa fa-pencil"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="deleteNote( ' + id + ' );"><span><i class="fa fa-trash"></i></span></a>'
            );
            $("#note-" + id).find(".card-body").html('<p class="card-text">' + newDescription + '</p>');            
        },
        error: function (error) {
            $("#note-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="saveNote( ' + id + ' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="backNote( ' + id + ' );"><span><i class="fa fa-undo"></i></span></a>'
            );
        }
    });
}

function backNote(id) {
    $("#note-" + id).find(".card-header").find(".btn-div").html(
        '<a href="javascript: void(0)" onclick="editNote( '+id+' );"><span><i class="fa fa-pencil"></i></span></a>' +
        '<a href="javascript: void(0)" onclick="deleteNote( ' + id +' );"><span><i class="fa fa-trash"></i></span></a>'
    );
    $("#note-" + id).find(".card-body").html('<p class="card-text">'+sessionStorage.note+'</p>');
}