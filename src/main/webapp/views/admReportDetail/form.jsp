<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Code Building">
        <meta name="keywords" content="oith,reflection,spring,java">
        <meta name="author" content="oith">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var='context' value='${pageContext.request.contextPath}'/>
        <spring:message code="admReportDetail.label" text='Adm Report Detail' var="entityName"/>
        <title><spring:message code="default.${_action}.label" arguments="${entityName}"/></title>
        
        <style>
            .error {
                color: #ff0000;
            }
            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
        </style>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1>
                    <spring:message code="default.${_action}.label" arguments="${entityName}"/>
                    <c:if test="${_action =='create'}">
                        &nbsp;<small class='disabled'>(${_menuCode})</small>
                    </c:if>
                </h1>
                <ul class='top-links'>
                    <c:if test="${admReportDetail.id !=null}">
                        <sec:authorize url='/admReportDetail/create'>
                            <li>
                                <a href='${context}/admReportDetail/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i>
                                    <spring:message code='default.new.label' arguments="${entityName}"/>
                                </a>
                            </li>
                        </sec:authorize>
                    </c:if>
                    <sec:authorize url='/admReportDetail/index'>
                        <li>
                            <a href='${context}/admReportDetail/index' class='btn btn-block btn-info btn-xs'>
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
                    <form:form action='${context}/admReportDetail/${_action}/${admReportDetail.id}' enctype='multipart/form-data' commandName='admReportDetail' method='POST'>
                        <div class='box-body'>
                            <form:errors path='*' cssClass='errorblock' element='div' />
                            <form:hidden path='id'/>
                            <div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' min='-128' max='127'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='admReport'><spring:message code='admReport' text='Adm Report'/></form:label>
            <form:input class='form-control' path='admReport.code' required='true' onkeyup='getCodableDTOAdmReport(this.value,"admReport_caption")' type='text' size='8'/>
            <label id='admReport_caption'>${admReportDetail.admReport.fullName}</label>
            <form:errors path='admReport' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='admParam'><spring:message code='admParam' text='Adm Param'/></form:label>
            <form:select class='form-control' path='admParam' name='admParam' id='admParam' required='true' >
                <form:options items='${admParams}' itemValue='id'/>
            </form:select>
            <form:errors path='admParam' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isActive'><spring:message code='isActive' text='Is Active'/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path='isActive' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isMandatory'><spring:message code='isMandatory' text='Is Mandatory'/></form:label>
            <br><form:checkbox class='cb' path='isMandatory'/>
            <form:errors path='isMandatory' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='defaultVal'><spring:message code='defaultVal' text='Default Val'/></form:label>
            <form:textarea class='form-control' path='defaultVal' type='text' size='30' maxlength='255'/>
            <form:errors path='defaultVal' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='helpText'><spring:message code='helpText' text='Help Text'/></form:label>
            <form:textarea class='form-control' path='helpText' type='text' size='30' maxlength='255'/>
            <form:errors path='helpText' cssClass='error' element='div'/>
        </div>
    </div>

                            </div>
                        </div>

                        <div class='box-footer'>
                            <c:choose>
                                <c:when test="${admReportDetail.id !=null}">
                                    <sec:authorize url='/admReportDetail/show'>
                                        <a href='${context}/admReportDetail/show/${admReportDetail.id}' class='btn btn-primary'>
                                            <i class='fa fa-info-circle'></i>
                                            <spring:message code='default.button.show.label' text='Show'/>
                                        </a> 
                                    </sec:authorize> 
                                    <c:if test="${_action =='edit'}">
                                        <sec:authorize url='/admReportDetail/edit/${admReportDetail.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-save'></i>
                                                <spring:message code='default.button.update.label' text='Update'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                    <c:if test="${_action =='copy'}">
                                        <sec:authorize url='/admReportDetail/copy/${admReportDetail.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-clone' aria-hidden='true'></i>
                                                <spring:message code='default.button.copy.label' text='Copy'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <button type='reset' class='btn btn-danger'>
                                        <i class='fa fa-refresh'></i>
                                        <spring:message code='default.button.reset.label' text='Reset'/>
                                    </button>
                                    <sec:authorize url='/admReportDetail/create'>
                                        <button type='submit' class='btn btn-primary'>
                                            <i class='fa fa-save'></i>
                                            <spring:message code='default.button.save.label' text='Save'/>
                                        </button>
                                    </sec:authorize>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form:form>
                </div>
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
        <script type='text/javascript'>
    function getCodableDTOAdmReport(code, lblCaption){
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/admReport/getCodableDTO',
            data: {code: code},
            success: function (d) {
                //alert('ok codeable: '+d);
                $('#' + lblCaption).text(d);
            },
            error: function (err) {
                //alert('err mac: '+err);
                $('#' + lblCaption).text(err);
            }
        });
    }
        </script>
    </body>
</html>