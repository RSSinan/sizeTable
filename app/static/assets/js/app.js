$(document).ready(function () {
    var order_id = 0
    var current_step_id = 0
    var last_step_id = 0
    $("#order_select").change(function () {
        order_id = $("#order_select").val()
        window.location = window.location.href.split("?")[0] + "?order_id=" + order_id;
    });

    var interval = 100;  // 1000 = 1 second, 3000 = 3 seconds
    function doGetCurrentStepId() {
        $.ajax({
            type: 'GET',
            url: 'get_current_step_id',
            data: JSON.stringify({"order_id": order_id}),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                current_step_id = data
                $('#' + current_step_id).addClass("current_measure");
                if (last_step_id !== 0) {
                    if (current_step_id !== last_step_id) {
                        setTimeout(doGetCurrentMeasurement, interval);
                    }
                } else {
                    last_step_id = current_step_id
                }
            },
            complete: function (data) {
                // Schedule the next
                setTimeout(doGetCurrentStepId, interval);
            }
        });
    }

    function doGetCurrentMeasurement() {
        $.ajax({
            type: 'GET',
            url: 'get_current_measurement',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (parseInt(data) !== 0) {
                    $('#' + last_step_id)
                        .html(data)
                        .removeClass("current_measure");
                }
            },
            complete: function (data) {
                // Schedule the next
                last_step_id = current_step_id
            }
        });
    }

    setTimeout(doGetCurrentStepId, interval);

});