<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <spring:message code="queryCenter" text="Query Center"/>
    </title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class="content-header"><!-- Content Header (Page header) -->
        <h1><spring:message code="queryCenter" text="Query Center"/></h1>
    </section>
    <!-- /.content-header -->

    <section class="content-messages">
        <jsp:include page="/views/layouts/_flashMessage.jsp"/>
    </section>
    <!-- /.flesh-message -->

    <section class="content"><!-- Main content -->
        <div class="box box-primary">

            <div class="box-body">
                <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
                    <div class='form-group'>
                        <label class='required' for='query'>
                            <span><spring:message code='query' text='Query'/></span>
                        </label>

                                <textarea class='form-control'
                                          style="white-space:pre-wrap;font-family:Courier New;font-weight:bold;"
                                          id="query" rows="4" cols="50"
                                          required='required'>SELECT x, p, m
FROM AdmSubModule m INNER JOIN m.admMenuCommons x, AdmMenuPattern p
WHERE 1=1
AND p.admMenuCommon = x
AND p.menuOrientation = 'MENU_TOP'
AND m.isActive = true 
ORDER BY x.slNo, x.fullName</textarea>
                    </div>
                </div>

                <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
                    <div class='form-group'>
                        <label class='required' for='returnRecs'>
                            <span><spring:message code='returnRecs' text='Return Rec(s)'/></span>
                        </label>
                        <select name='returnRecs'
                                id='returnRecs'
                                required='required'
                                class='form-control'>
                            <option value='5'>5</option>
                            <option value='10'>10</option>
                            <option value='15' selected='selected'>15</option>
                            <option value='30'>30</option>
                            <option value='50'>50</option>
                            <option value='100'>100</option>
                            <option value='200'>200</option>
                            <option value='500'>500</option>
                            <option value='1000'>1000</option>
                            <option value='2000'>2000</option>
                        </select>
                    </div>
                </div>

                <div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>
                    <div id="buttonContent" class="btn-group">
                        <button class="btn btn-primary" onclick="execOrmQuery()"><spring:message code='executeORM'
                                                                                                 text='Execute ORM/HQL/JPQL'/></button>
                        <button class="btn btn-warning" onclick="execNativeQuery()"><spring:message code='executeNative'
                                                                                                    text='Execute Native'/></button>
                        <button class="btn btn-danger" onclick="execPureNativeQuery()"><spring:message
                                code='executePureNative' text='Execute Pure Native'/></button>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->

            <div style="height: 400px; width: 995px; margin: auto; overflow-x: auto;">
                <p id="datatab"></p>
            </div>
        </div>
        <!-- /.create-zxLookup -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
    function execPureNativeQuery() {

        var elem = document.getElementById("query");
        var query = elem.value;
        var elemRR = document.getElementById("returnRecs");
        var returnRecs = elemRR.value;

        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/queryCenter/execPureNativeQuery',
            data: {query: query, returnRecs: returnRecs},
            success: function (d) {
                //  alert("ok:"+d.id)
                document.getElementById('datatab').innerHTML = d;
            },
            error: function (err) {
                //alert("err mac:"+err)
                document.getElementById('datatab').innerHTML = err;
            }
        });
    }
    function execNativeQuery() {

        var elem = document.getElementById("query");
        var query = elem.value;
        var elemRR = document.getElementById("returnRecs");
        var returnRecs = elemRR.value;

        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/queryCenter/execNativeQuery',
            data: {query: query, returnRecs: returnRecs},
            success: function (d) {
                //  alert("ok:"+d.id)
                document.getElementById('datatab').innerHTML = d;
            },
            error: function (err) {
                //alert("err mac:"+err)
                document.getElementById('datatab').innerHTML = err;
            }
        });
    }
    function execOrmQuery() {

        var elem = document.getElementById("query");
        var query = elem.value;
        var elemRR = document.getElementById("returnRecs");
        var returnRecs = elemRR.value;

        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/queryCenter/execOrmQuery',
            data: {query: query, returnRecs: returnRecs},
            success: function (d) {
                //  alert("ok:"+d.id)
                document.getElementById('datatab').innerHTML = d;
            },
            error: function (err) {
                // alert("query"+err+">>>")
                //alert("err mac:"+err)
                document.getElementById('datatab').innerHTML = err;
            }
        });
    }
</script>
</body>
</html>
