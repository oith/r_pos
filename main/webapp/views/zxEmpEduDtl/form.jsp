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
        <spring:message code="zxEmpEduDtl.label" text='Zx Emp Edu Dtl' var="entityName"/>
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
                    <c:if test="${zxEmpEduDtl.id !=null}">
                        <sec:authorize url='/zxEmpEduDtl/create'>
                            <li>
                                <a href='${context}/zxEmpEduDtl/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i>
                                    <spring:message code='default.new.label' arguments="${entityName}"/>
                                </a>
                            </li>
                        </sec:authorize>
                    </c:if>
                    <sec:authorize url='/zxEmpEduDtl/index'>
                        <li>
                            <a href='${context}/zxEmpEduDtl/index' class='btn btn-block btn-info btn-xs'>
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
                    <form:form action='${context}/zxEmpEduDtl/${_action}/${zxEmpEduDtl.id}' enctype='multipart/form-data' commandName='zxEmpEduDtl' method='POST'>
                        <div class='box-body'>
                            <form:errors path='*' cssClass='errorblock' element='div' />
                            <form:hidden path='id'/>
                            <div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='zxEmp'><spring:message code='zxEmp' text='Zx Emp'/></form:label>
            <form:input class='form-control' path='zxEmp.code' required='true' onkeyup='getCodableDTOZxEmp(this.value,"zxEmp_caption")' type='text' size='8'/>
            <label id='zxEmp_caption'>${zxEmpEduDtl.zxEmp.fullName}</label>
            <form:errors path='zxEmp' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='zxLookupEduLvl'><spring:message code='zxLookupEduLvl' text='Zx Lookup Edu Lvl'/></form:label>
            <form:select class='form-control' path='zxLookupEduLvl' name='zxLookupEduLvl' id='zxLookupEduLvl' required='true' >
                <form:options items='${zxLookupEduLvls}' itemValue='id'/>
            </form:select>
            <form:errors path='zxLookupEduLvl' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='eduOrg'><spring:message code='eduOrg' text='Edu Org'/></form:label>
            <form:input class='form-control' path='eduOrg' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='eduOrg' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='fromDate'><spring:message code='fromDate' text='From Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='fromDate'  />
             </div>
            <form:errors path='fromDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='toDate'><spring:message code='toDate' text='To Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='toDate'  />
             </div>
            <form:errors path='toDate' cssClass='error' element='div'/>
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
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' min='-128' max='127'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='certificate'><spring:message code='certificate' text='Certificate'/></form:label>
<c:url var='certificate' value='/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificate}'/>
                <a target='_blank' href='${certificate}'>${zxEmpEduDtl.certificate}</a>
                <form:hidden path='certificate'/>
                <input id='certificateOBJ' name='certificateOBJ' type='file'/>
            
            <form:errors path='certificate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='pic'><spring:message code='pic' text='Pic'/></form:label>
                <c:url var='pic' value='/zxEmpEduDtl/getPhotoMedium/${zxEmpEduDtl.pic}'/>
                <img alt='${zxEmpEduDtl.pic}' src='${pic}'/>
                <form:hidden path='pic'/>
                <input id='picOBJ' name='picOBJ' type='file' accept='image/*'/>
            <form:errors path='pic' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='zxEmpWhoCheckedBy'><spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/></form:label>
            <form:input class='form-control' path='zxEmpWhoCheckedBy.code' onkeyup='getCodableDTOZxEmp(this.value,"zxEmpWhoCheckedBy_caption")' type='text' size='8'/>
            <label id='zxEmpWhoCheckedBy_caption'>${zxEmpEduDtl.zxEmpWhoCheckedBy.fullName}</label>
            <form:errors path='zxEmpWhoCheckedBy' cssClass='error' element='div'/>
        </div>
    </div>

                            </div>
                        </div>

                        <div class='box-footer'>
                            <c:choose>
                                <c:when test="${zxEmpEduDtl.id !=null}">
                                    <sec:authorize url='/zxEmpEduDtl/show'>
                                        <a href='${context}/zxEmpEduDtl/show/${zxEmpEduDtl.id}' class='btn btn-primary'>
                                            <i class='fa fa-info-circle'></i>
                                            <spring:message code='default.button.show.label' text='Show'/>
                                        </a> 
                                    </sec:authorize> 
                                    <c:if test="${_action =='edit'}">
                                        <sec:authorize url='/zxEmpEduDtl/edit/${zxEmpEduDtl.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-save'></i>
                                                <spring:message code='default.button.update.label' text='Update'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                    <c:if test="${_action =='copy'}">
                                        <sec:authorize url='/zxEmpEduDtl/copy/${zxEmpEduDtl.id}'>
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
                                    <sec:authorize url='/zxEmpEduDtl/create'>
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
    function getCodableDTOZxEmp(code, lblCaption){
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/zxEmp/getCodableDTO',
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