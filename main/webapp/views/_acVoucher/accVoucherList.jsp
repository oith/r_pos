<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
<c:set var='context' value='${pageContext.request.contextPath}'/>
<head>
    <title><spring:message code='default.button.list.label' text='List'/>&nbsp;<spring:message code='acVoucherMst'
                                                                                               text='Ac Voucher Mst'/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='default.button.list.label' text='List'/>&nbsp;<spring:message code='acVoucherMst'
                                                                                                text='Ac Voucher Mst'/>&nbsp;
            <small class='disabled'>(${MENU_CODE})</small>
        </h1>

        <ul class='top-links'>
            <sec:authorize url='/accVoucher/create'>
                <li>
                    <a href='${context}/accVoucher/create' class='btn btn-primary btn-xs'><i
                            class='fa fa-plus-circle'></i>&nbsp;<spring:message code='default.button.create.label'
                                                                                text='New'/></a>
                </li>
            </sec:authorize>
        </ul>
    </section>
    <!-- /.content-header -->

    <section class='content-messages'>
        <jsp:include page='/views/layouts/_flashMessage.jsp'/>
    </section>
    <!-- /.flesh-message -->

    <section class='content'><!-- Main content -->
        <div class='box box-primary'>
            <!--<div class='box-body' style='overflow-x: auto'>-->
            <div class='box-body'>
                <table class='table dt-default table-bordered table-striped table-hover table-condensed display nowrap th-center'>

                    <!--<table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>-->
                    <thead>
                    <th><spring:message code='code' text='Code'/></th>
                    <th><spring:message code='voucherDate' text='Voucher Date'/></th>
                    <th><spring:message code='postingDate' text='Posting Date'/></th>
                    <th><spring:message code='isDeleted' text='Is Deleted'/></th>
                    <th><spring:message code='narration' text='Narration'/></th>
                    <th><spring:message code='default.button.action.label' text='Action'/></th>
                    </thead>
                    <tbody>
                    <c:if test='${not empty acVoucherMsts}'>
                        <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                                        var='confirmToDelete'/>
                        <c:forEach items='${acVoucherMsts}' var='acVoucherMst' varStatus='loopStatus'>
                            <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                <td><c:out value='${acVoucherMst.code}'/></td>
                                <td class='center'><fmt:formatDate value='${acVoucherMst.voucherDate}' type='date'
                                                                   pattern='dd/MM/yyyy'/></td>
                                <td class='center'><fmt:formatDate value='${acVoucherMst.postingDate}' type='date'
                                                                   pattern='dd/MM/yyyy'/></td>
                                    <%--<td class='center'><c:out value='${acVoucherMst.emDocType}'/></td>--%>
                                <td class='center'><c:if test='${acVoucherMst.isDeleted}'><spring:message
                                        code='default.boolean.true' text='YES'/></c:if><c:if
                                        test='${!acVoucherMst.isDeleted}'><spring:message code='default.boolean.false'
                                                                                          text='NO'/></c:if></td>
                                <td><c:out value='${acVoucherMst.narration}'/></td>
                                <td class='center action'>
                                    <sec:authorize url='/accVoucher/show'>
                                        <a class='btn btn-info btn-xs'
                                           href='${context}/accVoucher/show/${acVoucherMst.id}'>
                                            <i class='fa fa-info-circle'></i>&nbsp;<spring:message
                                                code='show.link.label'/>
                                        </a>
                                    </sec:authorize>
                                    <sec:authorize url='/accVoucher/edit'>
                                        <a class='btn btn-primary btn-xs'
                                           href='${context}/accVoucher/edit/${acVoucherMst.id}'>
                                            <i class='fa fa-edit'></i>&nbsp;<spring:message
                                                code='default.button.edit.label'/>
                                        </a>
                                    </sec:authorize>
                                        <%--<sec:authorize url='/accVoucher/copy'>
                                            <a class='btn btn-warning btn-xs' href='${context}/accVoucher/copy/${acVoucherMst.id}'>
                                                <i class='fa fa-clone' aria-hidden='true'></i>&nbsp;<spring:message code='default.button.copy.label'/>
                                            </a>
                                        </sec:authorize>--%>
                                    <sec:authorize url='/accVoucher/delete'>
                                        <a class='btn btn-danger btn-xs'
                                           href='${context}/accVoucher/delete/${acVoucherMst.id}'
                                           onclick="return confirm('${confirmToDelete}');">
                                            <i class='fa fa-remove'></i>&nbsp;<spring:message
                                                code='default.button.delete.label'/>
                                        </a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <!--<div class='pagination'>-->
                <!--<g:paginate total='{testInstanceCount ?: 0}'/>-->
                <!--</div>-->
            </div>
            <!-- /.box-body table-responsive no-padding -->
        </div>
        <!-- /.box box-primary -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
</body>
</html>