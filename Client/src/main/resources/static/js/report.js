jQuery(document).ready(function($) {

        $(".js-faculty-report").on("click", extractFacultyReport);
        $(".js-entry-report").on("click", extractEntryReport)
    }
);

function extractFacultyReport(evt) {
    console.log("click");
    evt.preventDefault();
    if ($("#sectionId").val().length > 0) {
        let url = "http://localhost:8081/faculty-report/" + $("#sectionId").val();
        console.log(url);
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                console.log(data);
                allResult = data;
                $("#tblResult tbody").empty();
                $.each(allResult, function (index, item) {
                    $('#itemRow').tmpl(item).appendTo($('#tblResult tbody'));
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#errors").html("There is an issue, please patient to try again. Thank you.");
            }
        });
    } else {
        alert("Select section (course of block) to view report")
    }
}

function extractEntryReport(evt) {
    console.log("click");
    evt.preventDefault();
    if ($("#sectionId").val().length > 0) {
        let url = "http://localhost:8081/report/entries/" + $("#sectionId").val();
        console.log(url);
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                console.log(data);
                allResult = data;
                $("#tblResult tbody").empty();
                $.each(allResult, function (index, item) {
                    $('#itemRow').tmpl(item).appendTo($('#tblResult tbody'));
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#errors").html("There is an issue, please patient to try again. Thank you.");
            }
        });
    } else {
        alert("Select Entry to view report")
    }
}
