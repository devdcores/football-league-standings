$(document).ready(function () {

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });

});

function fire_ajax_submit() {

    var countryName = $("#countryName").val();
    var leagueName = $("#leagueName").val();
    var teamName = $("#teamName").val();

    var data = 'countryName='
        + encodeURIComponent(countryName)
        + '&leagueName='
        + encodeURIComponent(leagueName)
        + '&teamName='
        + encodeURIComponent(teamName);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getLeagueStanding?"+data,
        cache: false,
        timeout: 600000,
        success: function (res) {
            var json = "<h4>Result</h4>"
                + JSON.stringify(res, null, 4);
            $('#result').html(json);
            $('#result').css('background', 'darkgreen');
            console.log("SUCCESS : ", res);
        },
        error: function (e) {
            var json = "<h4>Result</h4>"
                + JSON.stringify(e.responseJSON, null, 4);
            $('#result').html(json);
            $('#result').css('background', 'darkred');
            console.log("ERROR : ", e);
        }
    });

}