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
        <spring:message code="posProduct.label" text='Pos Product' var="entityName"/>
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
                    <c:if test="${posProduct.id !=null}">
                        <sec:authorize url='/posProduct/create'>
                            <li>
                                <a href='${context}/posProduct/create' class='btn btn-block btn-primary btn-xs'>
                                    <i class='fa fa-plus-circle'></i>
                                    <spring:message code='default.new.label' arguments="${entityName}"/>
                                </a>
                            </li>
                        </sec:authorize>
                    </c:if>
                    <sec:authorize url='/posProduct/index'>
                        <li>
                            <a href='${context}/posProduct/index' class='btn btn-block btn-info btn-xs'>
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
                    <form:form action='${context}/posProduct/${_action}/${posProduct.id}' enctype='multipart/form-data' commandName='posProduct' method='POST'>
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
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullNameNative'><spring:message code='fullNameNative' text='Full Name Native'/></form:label>
            <form:input class='form-control' path='fullNameNative' type='text' required='true' size='30' maxlength='50'/>
            <form:errors path='fullNameNative' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='tagCode'><spring:message code='tagCode' text='Tag Code'/></form:label>
            <form:input class='form-control' path='tagCode' type='text' size='30' maxlength='10'/>
            <form:errors path='tagCode' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isVatCalc'><spring:message code='isVatCalc' text='Is Vat Calc'/></form:label>
            <br><form:checkbox class='cb' path='isVatCalc'/>
            <form:errors path='isVatCalc' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='limitMax'><spring:message code='limitMax' text='Limit Max'/></form:label>
            <form:input class='form-control' path='limitMax' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='limitMax' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='limitStd'><spring:message code='limitStd' text='Limit Std'/></form:label>
            <form:input class='form-control' path='limitStd' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='limitStd' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='limitMin'><spring:message code='limitMin' text='Limit Min'/></form:label>
            <form:input class='form-control' path='limitMin' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='limitMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='unitPricePurchaseStd'><spring:message code='unitPricePurchaseStd' text='Unit Price Purchase Std'/></form:label>
            <form:input class='form-control' path='unitPricePurchaseStd' type='number' required='true' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPricePurchaseStd' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='unitPricePurchaseMin'><spring:message code='unitPricePurchaseMin' text='Unit Price Purchase Min'/></form:label>
            <form:input class='form-control' path='unitPricePurchaseMin' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPricePurchaseMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='unitPricePurchaseMax'><spring:message code='unitPricePurchaseMax' text='Unit Price Purchase Max'/></form:label>
            <form:input class='form-control' path='unitPricePurchaseMax' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPricePurchaseMax' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='unitPriceSalesStd'><spring:message code='unitPriceSalesStd' text='Unit Price Sales Std'/></form:label>
            <form:input class='form-control' path='unitPriceSalesStd' type='number' required='true' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPriceSalesStd' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='unitPriceSalesMin'><spring:message code='unitPriceSalesMin' text='Unit Price Sales Min'/></form:label>
            <form:input class='form-control' path='unitPriceSalesMin' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPriceSalesMin' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='unitPriceSalesMax'><spring:message code='unitPriceSalesMax' text='Unit Price Sales Max'/></form:label>
            <form:input class='form-control' path='unitPriceSalesMax' type='number' min='-9223372036854775808' max='9223372036854775807' step='any'/>
            <form:errors path='unitPriceSalesMax' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='description'><spring:message code='description' text='Description'/></form:label>
            <form:textarea class='form-control' path='description' type='text' size='30' maxlength='500'/>
            <form:errors path='description' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='acOne'><spring:message code='acOne' text='Ac One'/></form:label>
            <form:select class='form-control' path='acOne' name='acOne' id='acOne' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${acOnes}' itemValue='id'/>
            </form:select>
            <form:errors path='acOne' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='acTwo'><spring:message code='acTwo' text='Ac Two'/></form:label>
            <form:select class='form-control' path='acTwo' name='acTwo' id='acTwo' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${acTwos}' itemValue='id'/>
            </form:select>
            <form:errors path='acTwo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='acThree'><spring:message code='acThree' text='Ac Three'/></form:label>
            <form:select class='form-control' path='acThree' name='acThree' id='acThree' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${acThrees}' itemValue='id'/>
            </form:select>
            <form:errors path='acThree' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='acFour'><spring:message code='acFour' text='Ac Four'/></form:label>
            <form:select class='form-control' path='acFour' name='acFour' id='acFour' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${acFours}' itemValue='id'/>
            </form:select>
            <form:errors path='acFour' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='acFive'><spring:message code='acFive' text='Ac Five'/></form:label>
            <form:select class='form-control' path='acFive' name='acFive' id='acFive' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${acFives}' itemValue='id'/>
            </form:select>
            <form:errors path='acFive' cssClass='error' element='div'/>
        </div>
    </div>

                            </div>
                        </div>

                        <div class='box-footer'>
                            <c:choose>
                                <c:when test="${posProduct.id !=null}">
                                    <sec:authorize url='/posProduct/show'>
                                        <a href='${context}/posProduct/show/${posProduct.id}' class='btn btn-primary'>
                                            <i class='fa fa-info-circle'></i>
                                            <spring:message code='default.button.show.label' text='Show'/>
                                        </a> 
                                    </sec:authorize> 
                                    <c:if test="${_action =='edit'}">
                                        <sec:authorize url='/posProduct/edit/${posProduct.id}'>
                                            <button type='submit' class='btn btn-warning' >
                                                <i class='fa fa-save'></i>
                                                <spring:message code='default.button.update.label' text='Update'/>
                                            </button>
                                        </sec:authorize>
                                    </c:if>
                                    <c:if test="${_action =='copy'}">
                                        <sec:authorize url='/posProduct/copy/${posProduct.id}'>
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
                                    <sec:authorize url='/posProduct/create'>
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