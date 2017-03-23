/*
 * Document   : main
 * Created on : May 24, 2016, 9:55:17 AM
 * Author     : hoshen.mahmud
 */
//----------------------------------------------------------------------------------------------------------------------------------

//jQuery(document).ready(function () {

//==================================================================================================================================
//  Spinner Icon (Window.onLoad(), AJAX Request fire)
//----------------------------------------------------------------------------------------------------------------------------------
//    $(window).load(function () {/*$(".spinner").fadeIn("slow");*/ $(".spinner").fadeOut("slow");});
//    $(window).load(function () { Pace.restart();});

//[ "ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend" ]
$(document).ajaxStart(function () {
    Pace.restart();
}).ajaxStop(function () {
    Pace.stop();
});

//$(document)
//    .ajaxStart(function () {/*$(".spinner").fadeIn("slow");*/ $(".spinner").show();})
//    .ajaxStop(function () {$(".spinner").fadeOut("slow");});
//==================================================================================================================================
//  iCheck (Checkbox & Radio Button)
//----------------------------------------------------------------------------------------------------------------------------------
//    $('.cb, .rb').iCheck({
//        checkboxClass: 'icheckbox_square-green',
//        radioClass: 'iradio_square-green',
//    });
//==================================================================================================================================
//  Select      //todo-check: select2 plugin not working properly
//----------------------------------------------------------------------------------------------------------------------------------
$(".select-with-search").select2();
//    $(".select-multiple").select2();
//    $(".select-multiple").select2({
//        tags        : "true",
//        placeholder : "Select One",
//        allowClear  : true
//    });
//==================================================================================================================================
//  Date picker
//----------------------------------------------------------------------------------------------------------------------------------
var fd = "dd/mm/yyyy";
//dtp-date, dtp-time, dtp-range
$('.dtp-date').datepicker({format: fd, autoclose: true}).prop('placeholder', fd);
//var ft = "";
//==================================================================================================================================
//alert("main2");
//});