var SERVER = "http://localhost:8081";
jQuery(document).ready(function($) {
    SERVER = $("#server").val();
    $(".js-view-tmretreatAndChecking").on("click", viewTmRetreatAndChecking);
    $(".js-save-tmretreatAndChecking").on("click", saveTmRetreatAndChecking); // save to DB

    // bind callback
    $('#tblResult tbody').on('click','.js-retreat-checking-edit',selectEditTmRetreatAndChecking);
    $('#tblResult tbody').on('click','.js-retreat-checking-delete',deleteTmRetreatAndChecking);
    $('ul.pagination').on('click','.js-retreat-checking-viewByPageable',viewTmRetreatAndCheckingByPageable);

    studentIdAutocomplete();

}
);

function studentIdAutocomplete() {
    let studentIdKey = "";
    let studentIdLabel = "";
    $("#lblStudentID").autocomplete({

        source: function (request, response) {
            if ($("#lblStudentID").val().length > 2) {
                let url = SERVER + "/student-lookup?q=" + $("#lblStudentID").val();
                console.log(url);
                $.ajax({
                    type: "GET",
                    url: url,
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        response($.map(data, function (item) {
                            return {
                                label: item.studentId,
                                value: item.id
                            };
                        }));

                        make_hidden('errors');
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error=======");
                        $("#errors").html("There is an issue, please patient to try again. Thank you.");
                    }
                });
            }
        },
        select: function (event, ui) {
            console.log(ui.item ?
                "Selected: " + ui.item.label :
                "Nothing selected, input was " + this.value);
            $("#studentIdKey").val(ui.item.value);
            $("#lblStudentID").val(ui.item.label);
            studentIdKey = ui.item.value;
            studentIdLabel = ui.item.label;
        },
        open: function () {
            console.log("open");
            $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
        },
        close: function (event, ui) {

            $("#studentIdKey").val(studentIdKey);
            $("#lblStudentID").val(studentIdLabel);
            $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
        }
    });

}

function saveTmRetreatAndChecking() {
    let json = serializeObject($("#tmRetreatAndCheckingForm"));
    json["student"] = {};
    json.student["id"] = $("#studentIdKey").val();

    let data = JSON.stringify(json);
    $("#errors").empty();
    $.ajax({
        type: "POST",
        url: SERVER + "/retreat-checking",
        contentType: "application/json",
        dataType : "json",
        data: data,
        success: function( data ) {
            console.log(data);
            // reset after updating
            $('#id').val('');
            $('#localDateTime').val('');

            viewTmRetreatAndChecking();
            make_hidden('errors');
        },

        error: function(XMLHttpRequest, textStatus, errorThrown){
            console.log("error=======");
            console.log(XMLHttpRequest.responseJSON);

            if (XMLHttpRequest.responseJSON.errorType == "ValidationError") {
                let errorMsg = '<h3> Error(s)!! </h3>';
                errorMsg += "<p>";
                var errorList = XMLHttpRequest.responseJSON.errors;
                $.each(errorList, function(i, error) {
                    errorMsg = errorMsg +error.message + '<br>';
                });
                errorMsg += '</p>';
                $('#errors').append(errorMsg);
                $('#errors').show();
            } else {
                console.log("error======= non Validation");
            }
        }

    });
}
function viewTmRetreatAndCheckingByPageable(evt) {
    console.log(evt);
   // evt.defaultPrevented();
    let page = $(evt.target).data("page");
    let pageSize = $(evt.target).data("pageSize");
    let link = $(evt.target).data("link");
    viewTmRetreatAndChecking(evt, page, pageSize, link);
}
function viewTmRetreatAndChecking(evt, currentPage, pageSize, link) {
    if (currentPage === undefined ){
        currentPage = '';
    }
    if (pageSize === undefined) {
        pageSize = '';
    }
    if ($("#studentIdKey").val().length > 0) {
        let url = SERVER + "/retreat-checking/student/" + $("#studentIdKey").val();
        let uri = SERVER + "/retreat-checking/student/" + $("#studentIdKey").val() + "?page=" + currentPage + "&pageSize=" + pageSize;
        if (link !== undefined) {
            uri = link;
        }
        console.log(url);
        $.ajax({
            type: "GET",
            url: uri,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                console.log(data);
                allResult = data.result;
                $("#tblResult tbody").empty();
                $.each(allResult, function (index, item) {
                    $('#itemRow').tmpl(item).appendTo($('#tblResult tbody'));
                });

                // pagination
                pageable = data.pageable;
                $("ul.pagination").empty();
                //$('#formInput').html("");

                if (pageable.pageNumber > 0) {
                    let pageLink = url + "?page=" + (pageable.pageNumber - 1) + "&pageSize=" + pageable.pageSize;
                    $("ul.pagination").append('<li> <a class="itemPage js-retreat-checking-viewByPageable" href="#" data-link="' + pageLink + '"' + '> Previous</a> </li>')
                }
                if (pageable.total > 0) {
                    let pageLink = url + "?page=" + (pageable.pageNumber ) + "&pageSize=" + pageable.pageSize;
                    $("ul.pagination").append('<li> <a  class="current itemPage js-retreat-checking-viewByPageable"  href="#" data-link="' + pageLink + '"' + '> ' + (pageable.pageNumber + 1) + '</a> </li>')
                }
                if (pageable.nextPage > 0) {
                    let pageLink = url + "?page=" + (pageable.pageNumber + 1) + '&amp;pageSize=' + pageable.pageSize;
                    $("ul.pagination").append('<li> <a class="itemPage js-retreat-checking-viewByPageable"   href="#" data-link="' + pageLink + '"' + '> Next</a> </li>')
                }


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#errors").html("There is an issue, please patient to try again. Thank you.");
            }
        });
    } else {
        alert("Select student to view data")
    }
}

function selectEditTmRetreatAndChecking(evt) {
    console.log("select edit");
    let retreatId = $(evt.target).data("id");
    loadTmRetreatAndCheckingFillForm(retreatId);
}

function loadTmRetreatAndCheckingFillForm(retreatId) {
    let url = SERVER + "/retreat-checking/" + retreatId;
    console.log(url);
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#id").val(data.id);
            $("#studentIdKey").val(data.student.id);
            $("#lblStudentID").val(data.student.studentId);
            $("#localDateTime").val(data.localDateTime);
            $("#retreat").prop( "checked", data.retreat );
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#errors").html("There is an issue, please patient to try again. Thank you.");
        }
    });
}

function deleteTmRetreatAndChecking(evt) {
    $("#dialog").dialog({
        buttons : {
            "Confirm" : function() {
                $(this).dialog("close");
                let retreatId = $(evt.target).data("id");
                console.log("delete ...." + retreatId);
                deleteTmRetreatAndCheckingById(retreatId);
            },
            "Cancel" : function() {
                $(this).dialog("close");
            }
        }
    });

    $("#dialog").dialog("open");
}

function deleteTmRetreatAndCheckingById(retreatId) {
    let url = SERVER + "/retreat-checking/" + retreatId;
    console.log(url);
    $.ajax({
        type: "DELETE",
        url: url,
        success: function (data) {
            console.log("delete successful");
            viewTmRetreatAndChecking();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#errors").html("There is an issue, please patient to try again. Thank you.");
        }
    });
}
make_hidden = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
}

make_visible = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
}

resetForm = function(id) {
    var element = document.getElementById(id);
    $(element)[0].reset();

}

// Translate form to array
// Then put in JSON format
function serializeObject (form)
{
    var jsonObject = {};
    var array = form.serializeArray();
    $.each(array, function() {
        jsonObject[this.name] = this.value;
    });
    return jsonObject;

};

