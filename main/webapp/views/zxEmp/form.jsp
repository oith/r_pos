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
        <spring:message code="zxEmp.label" text='Zx Emp' var="entityName"/>
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
                    <c:if test="${zxEmp.id !=null}">
                        <sec:authorize url='/zxEmp/create'>
                            <li>
                                <a href='${context}/zxEmp/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i>
                                    <spring:message code='default.new.label' arguments="${entityName}"/>
                                </a>
                            </li>
                        </sec:authorize>
                    </c:if>
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
                    <form:form action='${context}/zxEmp/${_action}/${zxEmp.id}' enctype='multipart/form-data' commandName='zxEmp' method='POST'>
                        <div class='box-body'>
                            <form:errors path='*' cssClass='errorblock' element='div' />
                            <form:hidden path='id'/>
                            <div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='code' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='tagCode'><spring:message code='tagCode' text='Tag Code'/></form:label>
            <form:input class='form-control' path='tagCode' type='text' size='30' maxlength='20'/>
            <form:errors path='tagCode' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='pic'><spring:message code='pic' text='Pic'/></form:label>
                <c:url var='pic' value='/zxEmp/getPhotoMedium/${zxEmp.pic}'/>
                <img alt='${zxEmp.pic}' src='${pic}'/>
                <form:hidden path='pic'/>
                <input id='picOBJ' name='picOBJ' type='file' accept='image/*'/>
            <form:errors path='pic' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='50'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='gender'><spring:message code='gender' text='Gender'/></form:label>
            <form:select class='form-control' path='gender' name='gender' id='gender' required='true' >
                <form:option value='MALE' label='Male'/>
                <form:option value='FEMALE' label='Female'/>
                <form:option value='OTHER' label='Other'/>
            </form:select>
            <form:errors path='gender' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='dob'><spring:message code='dob' text='Dob'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='dob'  />
             </div>
            <form:errors path='dob' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='salary'><spring:message code='salary' text='Salary'/></form:label>
            <form:input class='form-control' path='salary' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='salary' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='taxPaid'><spring:message code='taxPaid' text='Tax Paid'/></form:label>
            <form:input class='form-control' path='taxPaid' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='taxPaid' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='email' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='pin'><spring:message code='pin' text='Pin'/></form:label>
            <form:password class='form-control' path='pin' showPassword='true' size='30' maxlength='30'/>
            <form:errors path='pin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='webAddress'><spring:message code='webAddress' text='Web Address'/></form:label>
            <form:input class='form-control' path='webAddress' type='text' size='30' maxlength='20'/>
            <form:errors path='webAddress' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='document'><spring:message code='document' text='Document'/></form:label>
<c:url var='document' value='/zxEmp/getFile/${zxEmp.document}'/>
                <a target='_blank' href='${document}'>${zxEmp.document}</a>
                <form:hidden path='document'/>
                <input id='documentOBJ' name='documentOBJ' type='file'/>
            
            <form:errors path='document' cssClass='error' element='div'/>
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
            <form:label path='remarks'><spring:message code='remarks' text='Remarks'/></form:label>
            <form:textarea class='form-control' path='remarks' type='text' size='30' maxlength='500'/>
            <form:errors path='remarks' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='zxDept'><spring:message code='zxDept' text='Zx Dept'/></form:label>
            <form:select class='form-control' path='zxDept' name='zxDept' id='zxDept' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${zxDepts}' itemValue='id'/>
            </form:select>
            <form:errors path='zxDept' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='zxDesg'><spring:message code='zxDesg' text='Zx Desg'/></form:label>
            <form:select class='form-control' path='zxDesg' name='zxDesg' id='zxDesg' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${zxDesgs}' itemValue='id'/>
            </form:select>
            <form:errors path='zxDesg' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='zxLookupBloodGroup'><spring:message code='zxLookupBloodGroup' text='Zx Lookup Blood Group'/></form:label>
            <form:select class='form-control' path='zxLookupBloodGroup' name='zxLookupBloodGroup' id='zxLookupBloodGroup' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${zxLookupBloodGroups}' itemValue='id'/>
            </form:select>
            <form:errors path='zxLookupBloodGroup' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' min='-128' max='127'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='zxChooses'><spring:message code='zxChooses' text='Zx Chooses'/></form:label>
            <form:select class='form-control' path='zxChooses' multiple='true' >
                <form:options items='${zxChooses}' itemValue='id'/>
            </form:select>
            <form:errors path='zxChooses' cssClass='error' element='div'/>
        </div>
    </div>

                            </div>
                        </div>
                            <div><jsp:include page='formZxEmpEduDtls.jsp' /></div>
                            <div><jsp:include page='formZxEmpTrainingDtls.jsp' /></div>

                        <div class='box-footer'>
                            <c:choose>
                                <c:when test="${zxEmp.id !=null}">
                                    <sec:authorize url='/zxEmp/show'>
                                        <a href='${context}/zxEmp/show/${zxEmp.id}' class='btn btn-primary'>
                                            <i class='fa fa-info-circle'></i>
                                            <spring:message code='default.button.show.label' text='Show'/>
                                        </a> 
                                    </sec:authorize> 
                                    <c:if test="${_action =='edit'}">
                                        <sec:authorize url='/zxEmp/edit/${zxEmp.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-save'></i>
                                                <spring:message code='default.button.update.label' text='Update'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                    <c:if test="${_action =='copy'}">
                                        <sec:authorize url='/zxEmp/copy/${zxEmp.id}'>
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
                                    <sec:authorize url='/zxEmp/create'>
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

        </script>
    </body>
</html>