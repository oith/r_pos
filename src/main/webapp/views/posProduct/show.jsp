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
        <spring:message code="posProduct.label" text='Pos Product' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/posProduct/create'>
                        <li>
                            <a href='${context}/posProduct/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
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
                    <div class='box-body'>
                        <fieldset class='show-page'>
                            <form:hidden path='id'/>
                            <ol class='property-list'>

                                <c:if test='${posProduct.code!=null && !posProduct.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${posProduct.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.fullName!=null && !posProduct.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${posProduct.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.fullNameNative!=null && !posProduct.fullNameNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullNameNative' text='Full Name Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullNameNative'>
                                            <c:out value='${posProduct.fullNameNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.tagCode!=null && !posProduct.tagCode.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='tagCode' text='Tag Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='tagCode'>
                                            <c:out value='${posProduct.tagCode}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.isVatCalc!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isVatCalc' text='Is Vat Calc'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isVatCalc'>
                                            <c:if test='${posProduct.isVatCalc}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!posProduct.isVatCalc}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.limitMax!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='limitMax' text='Limit Max'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='limitMax'>
                                            <c:out value='${posProduct.limitMax}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.limitStd!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='limitStd' text='Limit Std'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='limitStd'>
                                            <c:out value='${posProduct.limitStd}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.limitMin!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='limitMin' text='Limit Min'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='limitMin'>
                                            <c:out value='${posProduct.limitMin}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPricePurchaseStd!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPricePurchaseStd' text='Unit Price Purchase Std'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPricePurchaseStd'>
                                            <c:out value='${posProduct.unitPricePurchaseStd}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPricePurchaseMin!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPricePurchaseMin' text='Unit Price Purchase Min'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPricePurchaseMin'>
                                            <c:out value='${posProduct.unitPricePurchaseMin}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPricePurchaseMax!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPricePurchaseMax' text='Unit Price Purchase Max'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPricePurchaseMax'>
                                            <c:out value='${posProduct.unitPricePurchaseMax}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPriceSalesStd!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPriceSalesStd' text='Unit Price Sales Std'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPriceSalesStd'>
                                            <c:out value='${posProduct.unitPriceSalesStd}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPriceSalesMin!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPriceSalesMin' text='Unit Price Sales Min'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPriceSalesMin'>
                                            <c:out value='${posProduct.unitPriceSalesMin}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.unitPriceSalesMax!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPriceSalesMax' text='Unit Price Sales Max'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPriceSalesMax'>
                                            <c:out value='${posProduct.unitPriceSalesMax}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.description!=null && !posProduct.description.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='description' text='Description'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='description'>
                                            <c:out value='${posProduct.description}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.acOne!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='acOne' text='Ac One'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='acOne'>
                                            <c:out value='${posProduct.acOne}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.acTwo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='acTwo' text='Ac Two'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='acTwo'>
                                            <c:out value='${posProduct.acTwo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.acThree!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='acThree' text='Ac Three'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='acThree'>
                                            <c:out value='${posProduct.acThree}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.acFour!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='acFour' text='Ac Four'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='acFour'>
                                            <c:out value='${posProduct.acFour}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posProduct.acFive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='acFive' text='Ac Five'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='acFive'>
                                            <c:out value='${posProduct.acFive}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/posProduct/edit'>
                            <a href='${context}/posProduct/edit/${posProduct.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/posProduct/copy'>
                            <a href='${context}/posProduct/copy/${posProduct.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/posProduct/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/posProduct/delete/${posProduct.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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