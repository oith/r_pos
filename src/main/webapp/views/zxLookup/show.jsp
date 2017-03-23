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
        <spring:message code="zxLookup.label" text='Zx Lookup' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/zxLookup/create'>
                        <li>
                            <a href='${context}/zxLookup/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/zxLookup/index'>
                        <li>
                            <a href='${context}/zxLookup/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${zxLookup.code!=null && !zxLookup.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${zxLookup.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.title!=null && !zxLookup.title.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='title' text='Title'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='title'>
                                            <c:out value='${zxLookup.title}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.titleNative!=null && !zxLookup.titleNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='titleNative' text='Title Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='titleNative'>
                                            <c:out value='${zxLookup.titleNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${zxLookup.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!zxLookup.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${zxLookup.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.remarks!=null && !zxLookup.remarks.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='remarks' text='Remarks'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='remarks'>
                                            <c:out value='${zxLookup.remarks}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.zxLookupKeyword!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxLookupKeyword' text='Zx Lookup Keyword'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxLookupKeyword'>
                                            <c:out value='${zxLookup.zxLookupKeyword}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.entryBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.entryBy' text='Entry By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.entryBy'>
                                            <c:out value='${zxLookup.embdAuditable.entryBy!=null ? zxLookup.embdAuditable.entryBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.entryDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.entryDate' text='Entry Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.entryDate'>
                                            <fmt:formatDate value='${zxLookup.embdAuditable.entryDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.editBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.editBy' text='Edit By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.editBy'>
                                            <c:out value='${zxLookup.embdAuditable.editBy!=null ? zxLookup.embdAuditable.editBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.editDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.editDate' text='Edit Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.editDate'>
                                            <fmt:formatDate value='${zxLookup.embdAuditable.editDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.copyBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.copyBy' text='Copy By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.copyBy'>
                                            <c:out value='${zxLookup.embdAuditable.copyBy!=null ? zxLookup.embdAuditable.copyBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxLookup.embdAuditable.copyDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.copyDate' text='Copy Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.copyDate'>
                                            <fmt:formatDate value='${zxLookup.embdAuditable.copyDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/zxLookup/edit'>
                            <a href='${context}/zxLookup/edit/${zxLookup.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/zxLookup/copy'>
                            <a href='${context}/zxLookup/copy/${zxLookup.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/zxLookup/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/zxLookup/delete/${zxLookup.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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