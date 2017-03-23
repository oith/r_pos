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
        <spring:message code="zxEmp.label" text='Zx Emp' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/zxEmp/create'>
                        <li>
                            <a href='${context}/zxEmp/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/zxEmp/index'>
                        <li>
                            <a href='${context}/zxEmp/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${zxEmp.code!=null && !zxEmp.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${zxEmp.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.tagCode!=null && !zxEmp.tagCode.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='tagCode' text='Tag Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='tagCode'>
                                            <c:out value='${zxEmp.tagCode}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.pic!=null && !zxEmp.pic.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='pic' text='Pic'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='pic'>
                                            <c:url var='pic' value='/zxEmp/getPhotoMedium/${zxEmp.pic}'/>
<img alt='${zxEmp.pic}' src='${pic}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.fullName!=null && !zxEmp.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${zxEmp.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.gender!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='gender' text='Gender'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='gender'>
                                            <c:out value='${zxEmp.gender}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.dob!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='dob' text='Dob'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='dob'>
                                            <fmt:formatDate value='${zxEmp.dob}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.salary!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='salary' text='Salary'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='salary'>
                                            <c:out value='${zxEmp.salary}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.taxPaid!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='taxPaid' text='Tax Paid'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='taxPaid'>
                                            <c:out value='${zxEmp.taxPaid}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.email!=null && !zxEmp.email.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='email' text='Email'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='email'>
                                            <c:out value='${zxEmp.email}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.webAddress!=null && !zxEmp.webAddress.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='webAddress' text='Web Address'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='webAddress'>
                                            <c:url var='webAddress' value='/zxEmp/getFile/${zxEmp.webAddress}'/>
<a target='_blank' href='${webAddress}'>${zxEmp.webAddress}</a>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.document!=null && !zxEmp.document.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='document' text='Document'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='document'>
                                            <c:url var='document' value='/zxEmp/getFile/${zxEmp.document}'/>
<a target='_blank' href='${document}'>${zxEmp.document}</a>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${zxEmp.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!zxEmp.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.remarks!=null && !zxEmp.remarks.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='remarks' text='Remarks'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='remarks'>
                                            <c:out value='${zxEmp.remarks}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.zxDept!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxDept' text='Zx Dept'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxDept'>
                                            <c:out value='${zxEmp.zxDept}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.zxDesg!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxDesg' text='Zx Desg'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxDesg'>
                                            <c:out value='${zxEmp.zxDesg}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.zxLookupBloodGroup!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxLookupBloodGroup' text='Zx Lookup Blood Group'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxLookupBloodGroup'>
                                            <c:out value='${zxEmp.zxLookupBloodGroup}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${zxEmp.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.phoneNumbers!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='phoneNumbers' text='Phone Numbers'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='phoneNumbers'>
                                            <c:out value='${zxEmp.phoneNumbers}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.attributes!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='attributes' text='Attributes'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='attributes'>
                                            <c:out value='${zxEmp.attributes}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.vacationBookings!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='vacationBookings' text='Vacation Bookings'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='vacationBookings'>
                                            <c:out value='${zxEmp.vacationBookings}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.nickNames!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='nickNames' text='Nick Names'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='nickNames'>
                                            <c:out value='${zxEmp.nickNames}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${zxEmp.zxChooses!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='zxChooses' text='Zx Chooses'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='zxChooses'>
                                            <c:out value='${zxEmp.zxChooses}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>
                            <div><jsp:include page='showZxEmpEduDtls.jsp' /></div>
                            <div><jsp:include page='showZxEmpTrainingDtls.jsp' /></div>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/zxEmp/edit'>
                            <a href='${context}/zxEmp/edit/${zxEmp.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/zxEmp/copy'>
                            <a href='${context}/zxEmp/copy/${zxEmp.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/zxEmp/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/zxEmp/delete/${zxEmp.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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