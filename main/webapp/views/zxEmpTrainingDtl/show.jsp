<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <meta name="description" content="Code Building">
        <meta name="keywords" content="oith,reflection,spring,java">
        <meta name="author" content="oith">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var='context' value='${pageContext.request.contextPath}'/>
        <spring:message code="zxEmpTrainingDtl.label" text='Zx Emp Training Dtl' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/zxEmpTrainingDtl/create'>
                        <li>
                            <a href='${context}/zxEmpTrainingDtl/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/zxEmpTrainingDtl/index'>
                        <li>
                            <a href='${context}/zxEmpTrainingDtl/index' class='btn btn-block btn-info btn-xs'>
                                <i class='fa fa-reorder'></i>
                                <spring:message code='default.list.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <jsp:include page='/views/layouts/_flashMessage.jsp'/>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                            <form:hidden path='id'/>
                            <ol class='property-list'>

                                <c:if test='${zxEmpTrainingDtl.zxEmp!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxEmp' text='Zx Emp'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxEmp'>
                                            <c:out value='${zxEmpTrainingDtl.zxEmp!=null ? zxEmpTrainingDtl.zxEmp :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.zxLookupTraining!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxLookupTraining' text='Zx Lookup Training'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxLookupTraining'>
                                            <c:out value='${zxEmpTrainingDtl.zxLookupTraining}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.trainingOrg!=null && !zxEmpTrainingDtl.trainingOrg.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='trainingOrg' text='Training Org'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='trainingOrg'>
                                            <c:out value='${zxEmpTrainingDtl.trainingOrg}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.fromDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fromDate' text='From Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fromDate'>
                                            <fmt:formatDate value='${zxEmpTrainingDtl.fromDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.toDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='toDate' text='To Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='toDate'>
                                            <fmt:formatDate value='${zxEmpTrainingDtl.toDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.remarks!=null && !zxEmpTrainingDtl.remarks.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='remarks' text='Remarks'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='remarks'>
                                            <c:out value='${zxEmpTrainingDtl.remarks}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${zxEmpTrainingDtl.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.certificate!=null && !zxEmpTrainingDtl.certificate.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='certificate' text='Certificate'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='certificate'>
                                            <c:url var='certificate' value='/zxEmpTrainingDtl/getFile/${zxEmpTrainingDtl.certificate}'/>
<a target='_blank' href='${certificate}'>${zxEmpTrainingDtl.certificate}</a>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.pic!=null && !zxEmpTrainingDtl.pic.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='pic' text='Pic'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='pic'>
                                            <c:url var='pic' value='/zxEmpTrainingDtl/getPhotoMedium/${zxEmpTrainingDtl.pic}'/>
<img alt='${zxEmpTrainingDtl.pic}' src='${pic}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmpTrainingDtl.zxEmpWhoCheckedBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxEmpWhoCheckedBy'>
                                            <c:out value='${zxEmpTrainingDtl.zxEmpWhoCheckedBy!=null ? zxEmpTrainingDtl.zxEmpWhoCheckedBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/zxEmpTrainingDtl/edit'>
                            <a href='${context}/zxEmpTrainingDtl/edit/${zxEmpTrainingDtl.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/zxEmpTrainingDtl/copy'>
                            <a href='${context}/zxEmpTrainingDtl/copy/${zxEmpTrainingDtl.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/zxEmpTrainingDtl/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/zxEmpTrainingDtl/delete/${zxEmpTrainingDtl.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
                                <i class='fa fa-remove'></i>
                                <spring:message code='default.button.delete.label'/>
                            </a>
                        </sec:authorize>
                    </div><!--.box-footer-->
                </div><!--.box .box-primary-->
            </section><!--.content-->
        </div><!-- /.content-wrapper -->
    </body>
</html>