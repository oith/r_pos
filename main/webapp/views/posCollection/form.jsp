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
        <spring:message code="posCollection.label" text='Pos Collection' var="entityName"/>
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
                    <c:if test="${posCollection.id !=null}">
                        <sec:authorize url='/posCollection/create'>
                            <li>
                                <a href='${context}/posCollection/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i>
                                    <spring:message code='default.new.label' arguments="${entityName}"/>
                                </a>
                            </li>
                        </sec:authorize>
                    </c:if>
                    <sec:authorize url='/posCollection/index'>
                        <li>
                            <a href='${context}/posCollection/index' class='btn btn-block btn-info btn-xs'>
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
                    <form:form action='${context}/posCollection/${_action}/${posCollection.id}' enctype='multipart/form-data' commandName='posCollection' method='POST'>
                        <div class='box-body'>
                            <form:errors path='*' cssClass='errorblock' element='div' />
                            <form:hidden path='id'/>
                            <div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='10'/>
            <form:errors path='code' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='transDate'><spring:message code='transDate' text='Trans Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='transDate' required='true'  />
             </div>
            <form:errors path='transDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='originDate'><spring:message code='originDate' text='Origin Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='originDate' required='true'  />
             </div>
            <form:errors path='originDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='amount'><spring:message code='amount' text='Amount'/></form:label>
            <form:input class='form-control' path='amount' type='number' required='true' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='amount' cssClass='error' element='div'/>
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
            <form:label class='required' path='authUserTransBy'><spring:message code='authUserTransBy' text='Auth User Trans By'/></form:label>
            <form:input class='form-control' path='authUserTransBy.username' required='true' onkeyup='getCodableDTOAuthUser(this.value,"authUserTransBy_caption")' type='text' size='8'/>
            <label id='authUserTransBy_caption'>${posCollection.authUserTransBy.fullName}</label>
            <form:errors path='authUserTransBy' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='posSalesMaster'><spring:message code='posSalesMaster' text='Pos Sales Master'/></form:label>
            <form:input class='form-control' path='posSalesMaster.code' onkeyup='getCodableDTOPosSalesMaster(this.value,"posSalesMaster_caption")' type='text' size='8'/>
            <label id='posSalesMaster_caption'>${posCollection.posSalesMaster.remarks}</label>
            <form:errors path='posSalesMaster' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='posCustomer'><spring:message code='posCustomer' text='Pos Customer'/></form:label>
            <form:input class='form-control' path='posCustomer.mobile' required='true' onkeyup='getCodableDTOPosCustomer(this.value,"posCustomer_caption")' type='text' size='8'/>
            <label id='posCustomer_caption'>${posCollection.posCustomer.fullName}</label>
            <form:errors path='posCustomer' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='emPosCollectionType'><spring:message code='emPosCollectionType' text='Em Pos Collection Type'/></form:label>
            <form:select class='form-control' path='emPosCollectionType' name='emPosCollectionType' id='emPosCollectionType' required='true' >
                <form:option value='CASH' label='CASH'/>
                <form:option value='VISA' label='VISA'/>
                <form:option value='MASTER_CARD' label='MASTER CARD'/>
                <form:option value='GIFT_CARD' label='GIFT CARD'/>
            </form:select>
            <form:errors path='emPosCollectionType' cssClass='error' element='div'/>
        </div>
    </div>

                            </div>
                        </div>

                        <div class='box-footer'>
                            <c:choose>
                                <c:when test="${posCollection.id !=null}">
                                    <sec:authorize url='/posCollection/show'>
                                        <a href='${context}/posCollection/show/${posCollection.id}' class='btn btn-primary'>
                                            <i class='fa fa-info-circle'></i>
                                            <spring:message code='default.button.show.label' text='Show'/>
                                        </a> 
                                    </sec:authorize> 
                                    <c:if test="${_action =='edit'}">
                                        <sec:authorize url='/posCollection/edit/${posCollection.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-save'></i>
                                                <spring:message code='default.button.update.label' text='Update'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                    <c:if test="${_action =='copy'}">
                                        <sec:authorize url='/posCollection/copy/${posCollection.id}'>
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
                                    <sec:authorize url='/posCollection/create'>
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
    function getCodableDTOAuthUser(username, lblCaption){
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/authUser/getCodableDTO',
            data: {username: username},
            success: function (d) {
                //alert('ok codeable: '+d);
                $('#' + lblCaption).text(d);
            },
            error: function (err) {
                //alert('err mac: '+err);
                $('#' + lblCaption).text(err);
            }
        });
    }    function getCodableDTOPosSalesMaster(code, lblCaption){
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSalesMaster/getCodableDTO',
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
    }    function getCodableDTOPosCustomer(mobile, lblCaption){
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posCustomer/getCodableDTO',
            data: {mobile: mobile},
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