
jQuery(document).ready(function($) {

    $(".js-faculty-report").on("click", extractFacultyReport);

    $("#studentId").autocomplete({

        source: function (request, response) {
            if ($("#studentId").val().length > 3) {
                let url = "http://localhost:8081/student-lookup?q=" + $("#studentId").val();
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
        select: function( event, ui ) {
            console.log( ui.item ?
                "Selected: " + ui.item.label :
                "Nothing selected, input was " + this.value);
        },
        open: function() {
            $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        },
        close: function() {
            $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        }
    });
}
);

function saveTmRetreatAndChecking() {
    let json = serializeObject($("#tmRetreatAndCheckingForm"));
    json["student"] = {};
    json.student["id"] = $("#studentId").val();

    let data = JSON.stringify(json);
    console.log(data)
    $("#errors").empty();
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/retreat-checking",
        contentType: "application/json",
        dataType : "json",
        data: data,
        success: function( data ) {
            console.log(data);

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
                alert(errorObject.responseJSON.errors(0)); // "non" Validation
            }
        }

    });
}

toggle_visibility = function(id) {
    var element = document.getElementById(id);
    if(element.style.display == 'block')
        element.style.display = 'none';
    else
        element.style.display = 'block';
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

