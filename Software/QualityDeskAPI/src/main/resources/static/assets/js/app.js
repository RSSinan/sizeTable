/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#IndexCardDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/CardDefinition"
        window.location = url;
    });

    $('#IndexMeasurePointDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/MeasurePointDefinition"
        window.location = url;
    });

    $('#IndexOrderDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/OrderDefinition"
        window.location = url;
    });

    $('#IndexSizeDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/SizeDefinition"
        window.location = url;
    });

    $('#IndexStepDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/StepDefinition"
        window.location = url;
    });

    $('#IndexUserCardRelationshipDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/UserCardRelationship"
        window.location = url;
    });

    $('#IndexUserDiv').click(function(e) {
        var host = window.location.href.split("/")[2]
        var url = "http://" + host + "/view/UserDefinition"
        window.location = url;
    });
});