<th:block xmlns:th="http://www.thymeleaf.org"
          xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
          layout:fragment="js">



    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!--<script type='text/javascript' src='resources/plugins/moment.js/moment.min.js'></script>-->
    <script type='text/javascript' src='resources/plugins/daterangepicker/moment.min.js'></script>
    <!-- NB : moment should be defined before dateRangePicker -->
    <script type='text/javascript' src='resources/plugins/daterangepicker/daterangepicker.js'></script>
    <!-- NB : moment should be defined before dateRangePicker -->
    <script type='text/javascript' src='resources/plugins/fullcalendar/fullcalendar.min.js'></script>

    <!-- bootstrap datepicker -->
    <script type='text/javascript' src='resources/plugins/datepicker/bootstrap-datepicker.js'></script>

    <!-- Slimscroll -->
    <script type='text/javascript' src='resources/plugins/slimScroll/jquery.slimscroll.min.js'></script>

    <!-- FastClick -->
    <script type='text/javascript' src='resources/plugins/fastclick/fastclick.js'></script>

    <!-- AdminLTE App -->
    <script type='text/javascript' src='resources/plugins/admin-lte/js/app.js'></script>

    <!-- PACE -->
    <script type='text/javascript' src='resources/plugins/pace/pace.min.js'></script>

    <!-- AdminLTE for demo purposes -->
    <script type='text/javascript' src='resources/plugins/admin-lte/js/config.js'></script>

    <!-- DataTables -->
    <script type='text/javascript' src='resources/plugins/datatables/jquery.dataTables.js'></script>
    <script type='text/javascript' src='resources/plugins/datatables/dataTables.bootstrap.min.js'></script>
    <script type='text/javascript'
            src='resources/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js'></script>
    <script type='text/javascript'
            src='resources/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js'></script>

    <!-- iCheck -->
    <script type='text/javascript' src='resources/plugins/iCheck/icheck.min.js'></script>

    <!-- Select2 -->
    <script type='text/javascript' src='resources/plugins/select2/select2.min.js'></script>

    <!-- jquery-confirm -->
    <script type='text/javascript' src='resources/plugins/jquery-confirm/jquery-confirm.min.js'></script>

    <!-- Reflection; todo-marge with main : after final test. -->
    <script type='text/javascript' src='resources/plugins/reflection/reflection.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/slide-to-top.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/skin-switcher.js'></script>
    <script type="text/javascript" src='resources/plugins/reflection/toggle-full-screen.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/custom-data-table.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/custom-master-details.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/custom-file-upload.js'></script>
    <script type='text/javascript' src='resources/plugins/reflection/custom-confirm.js'></script>

    <script type='text/javascript' src='resources/plugins/html5shiv/html5shiv.min.js'></script>

    <script type='text/javascript' src='resources/plugins/respond/respond.min.js'></script>


    <script type='text/javascript'>

        var contextPath = "${request.contextPath}";

        jQuery(document).ready(function () {
            contextPath = "${request.contextPath}";

//        alert('hi to ' + contextPath);

            $('.cb').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                increaseArea: '70%' // optional
            });
            $('.rb').iCheck({
                radioClass: 'iradio_square-blue',
                increaseArea: '70%' // optional
            });
        });

        var DATE_FORMAT = 'dd/mm/yyyy';

        $('.dtp-date').prop('placeholder', DATE_FORMAT);

        //Date picker
        $('.dtp-date').datepicker({
            format: DATE_FORMAT,
            autoclose: true
        });
    </script>

</th:block>