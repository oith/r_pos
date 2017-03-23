<script src="${pageContext.request.contextPath}/webjars/jquery/2.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src='${pageContext.request.contextPath}/resources/plugins/moment.js-2.11.2/moment.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/plugins/daterangepicker/daterangepicker.js'></script>
<!-- bootstrap datepicker -->
<script src='${pageContext.request.contextPath}/resources/plugins/datepicker/bootstrap-datepicker.js'></script>
<!-- bootstrap color picker -->
<script src='${pageContext.request.contextPath}/resources/plugins/colorpicker/bootstrap-colorpicker.min.js'></script>
<!-- bootstrap time picker -->
<script src='${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.js'></script>
<!-- SlimScroll 1.3.0 -->
<script src='${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js'></script>

<!-- DataTables -->
<script src='${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js'></script>
<script src='${pageContext.request.contextPath}/resources/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js'></script>

<!-- iCheck -->
<script src='${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js'></script>

<!-- SlimScroll -->
<script src='${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js'></script>

<!-- FastClick -->
<script src='${pageContext.request.contextPath}/resources/plugins/fastclick/fastclick.js'></script>

<!-- AdminLTE App -->
<script src='${pageContext.request.contextPath}/resources/plugins/admin-lte-2.3.3/js/app.min.js'></script>

<!--<script src='< %=request.getContextPath()%>/${pageContext.request.contextPath}/resources/plugins/reflection-1.0.0/reflection.js'></script>-->
<script src='${pageContext.request.contextPath}/resources/plugins/reflection-1.0.0/customInput.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/main.js'></script>
<script src='${pageContext.request.contextPath}/resources/plugins/jquery-confirm/jquery-confirm.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/custom-confirm.js'></script>

<!-- AdminLTE for demo purposes -->
<script src='${pageContext.request.contextPath}/resources/plugins/admin-lte-2.3.3/js/demo.js'></script>

<script type='text/javascript' charset='utf-8'>
    var contextPath = '${pageContext.request.contextPath}';
    var DATE_FORMAT = 'dd/mm/yyyy';

    $('.dtp-date').prop('placeholder', DATE_FORMAT);

    //Date picker
    $('.dtp-date').datepicker({
        format: DATE_FORMAT,
        autoclose: true
    });
</script>

