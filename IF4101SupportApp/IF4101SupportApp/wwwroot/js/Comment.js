$("#comments-tab").on("click", function () {
    $.ajax({
        url: "/Comment/CommentsByIssue",
        cache: false,
        type: "GET",
        data: {
            id: sessionStorage.ReportNumber
        },
        beforeSend: function () {
            $("#content-comment").empty();
        },
        success: function (data) {

            $('#content-comment').html(data);

        },
        error: function (error) {

        }
    });

});

$("#comment-form").submit(function (e) {
    e.preventDefault();
    var interval = null;
    $.ajax({
        url: "/Comment/Insert",
        cache: false,
        type: "Post",
        data: {
            Report_Number: sessionStorage.ReportNumber,
            Description: $(this).find("#comment-description").val()
        },
        beforeSend: function () {
            i = 0;
            $("#btn-save-comment").prop("disabled", true);
            interval = setInterval(function () {
                i = ++i % 4;
                $("#btn-save-comment").html("Processing" + Array(i + 1).join("."));
            }, 500);
        },
        success: function (data) {
            clearInterval(interval);
            $("#btn-save-comment").prop("disabled", false);
            $("#btn-save-comment").html("Add Comment");
            $('#content-comment').append(data);

        },
        error: function (error) {
            clearInterval(interval);
            $("#btn-save-comment").html("Error, Try Again!");
            $("#btn-save-comment").css("background-color", "red");
            setTimeout(function () {
                $("#btn-save-comment").prop("disabled", false);
                $("#btn-save-comment").html("Add Comment");
                $("#btn-save-comment").css("background-color", "#7f5feb");
            }, 4000);


        }
    });
});



function deleteComment(id) {
    $.ajax({
        url: "/Comment/Delete",
        cache: false,
        type: "Delete",
        data: {
            Id: id
        },
        beforeSend: function () {
            $("#comment-" + id).find(".card-header").find(".btn-div").html(
                '<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>'
            );

        },
        success: function (data) {
            $("#comment-" + id).parent().remove();
        },
        error: function (error) {
            $("#comment-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="saveComment( ' + id + ' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="backComment( ' + id + ' );"><span><i class="fa fa-undo"></i></span></a>'
            );
        }
    });
}

function editComment(id) {
    sessionStorage.setItem("comment", $("#comment-" + id).find(".card-body").find('p').html());
    $("#comment-" + id).find(".card-header").find(".btn-div").html(
        '<a href="javascript: void(0)" onclick="saveComment( ' + id + ' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
        '<a href="javascript: void(0)" onclick="backComment( ' + id + ' );"><span><i class="fa fa-undo"></i></span></a>'
    );

    $("#comment-" + id).find(".card-body").html(
        '<textarea id="comment-description" class="form-control" rows="3">' +
        sessionStorage.comment +
        '</textarea>');
}

function saveComment(id) {
    var newDescription = $("#comment-" + id).find(".card-body").find("#comment-description").val();

    $.ajax({
        url: "/Comment/Update",
        cache: false,
        type: "PUT",
        dataType: "json",
        data: {
            Comment_Id: id,
            Description: newDescription
        },
        beforeSend: function () {
            $("#comment-" + id).find(".card-header").find(".btn-div").html(
                '<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>'
            );

        },
        success: function (data) {
            $("#comment-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="editComment( ' + id + ' );"><span><i class="fa fa-pencil"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="deleteComment( ' + id + ' );"><span><i class="fa fa-trash"></i></span></a>'
            );
            $("#comment-" + id).find(".card-body").html('<p class="card-text">' + newDescription + '</p>');
        },
        error: function (error) {
            $("#comment-" + id).find(".card-header").find(".btn-div").html(
                '<a href="javascript: void(0)" onclick="saveComment( ' + id + ' );"><span><i class="fa fa-floppy-o"></i></span></a>' +
                '<a href="javascript: void(0)" onclick="backComment( ' + id + ' );"><span><i class="fa fa-undo"></i></span></a>'
            );
        }
    });
}

function backComment(id) {
    $("#comment-" + id).find(".card-header").find(".btn-div").html(
        '<a href="javascript: void(0)" onclick="editComment( ' + id + ' );"><span><i class="fa fa-pencil"></i></span></a>' +
        '<a href="javascript: void(0)" onclick="deleteComment( ' + id + ' );"><span><i class="fa fa-trash"></i></span></a>'
    );
    $("#comment-" + id).find(".card-body").html('<p class="card-text">' + sessionStorage.comment + '</p>');
}