<%@ page contentType='text/html;charset=utf-8' language='java' %>

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
    <title><spring:message code='voucher' text='Voucher'/></title>
</head>
<body>
<div class='content-wrapper'><!-- Content Wrapper. Contains page content -->

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

            <form:form action='${context}/accVoucher/createLight' modelAttribute="acVoucherMst" method='POST'>
            <div class='box-body'>

                <form:hidden path="id"/>

                <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                    <div class='form-group'>
                        <form:label path="voucherType" class='required'><spring:message code='voucherType'
                                                                                        text='Voucher Type'/></form:label>
                        <form:select path="voucherType" items="${voucherTypes}" class='form-control'/>
                        <form:errors path="voucherType" cssClass='error' element='div'/>
                    </div>
                </div>
                <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                    <div class='form-group'>
                        <form:label path="code" class='required'><spring:message code='code' text='Code'/></form:label>
                        <form:input path="code" class='form-control' required='required' size='30' maxlength='20'/>
                        <form:errors path="code" cssClass='error' element='div'/>
                    </div>
                </div>
                <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                    <div class='form-group'>
                        <form:label path="voucherDate" class='required'><spring:message code='documentDate'
                                                                                        text='Voucher Date'/></form:label>
                        <form:input path="voucherDate" class='form-control dtp-date' name='voucherDate'
                                    required='required'/>
                        <form:errors path="voucherDate" cssClass='error' element='div'/>
                    </div>
                </div>
                <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                    <div class='form-group'>
                        <form:label class='required' path='osLegalEntityEntity'><spring:message
                                code='osLegalEntityEntity'
                                text='Company/Legal Entity'/></form:label>
                        <form:select class='form-control' path='osLegalEntityEntity' items="${osLegalEntityEntities}"
                                     itemValue="id"
                                     required='true'/>
                        <form:errors path='osLegalEntityEntity' cssClass='error' element='div'/>
                    </div>
                </div>

                <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                    <div class='form-group'>
                        <form:label path="narration"><spring:message code='narration' text='Narration'/></form:label>
                        <form:textarea path="narration" class='form-control' maxlength='500'/>
                        <form:errors path="narration" cssClass='error' element='div'/>
                    </div>
                </div>
            </div>

            <c:forEach items='${acVoucherMst.acVoucherDtls}' var='acVoucherDtl' varStatus='varStatus'>
                <div class="box box-info childOneDetails">
                    <div class="box-body">
                        <form:hidden path="acVoucherDtls[${varStatus.index}].id"/>

                        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <form:label path="acVoucherDtls[${varStatus.index}].acAccountDr"
                                            class='required'><spring:message code='acAccountDr'
                                                                             text='Account Dr'/></form:label>
                                <form:select path="acVoucherDtls[${varStatus.index}].acAccountDr" items="${acAccounts}"
                                             itemValue="id" class='form-control'/>
                                <form:errors path="acVoucherDtls[${varStatus.index}].acAccountDr" cssClass='error'
                                             element='div'/>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <form:label path="acVoucherDtls[${varStatus.index}].enteredAmount"
                                            class='required'><spring:message code='enteredAmount'
                                                                             text='Entered Amount'/></form:label>
                                <form:input path="acVoucherDtls[${varStatus.index}].enteredAmount" class='form-control'
                                            required='required' align="left"/>
                                <form:errors path="acVoucherDtls[${varStatus.index}].enteredAmount" cssClass='error'
                                             element='div'/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <form:label path="acVoucherDtls[${varStatus.index}].acAccountCr"
                                            class='required'><spring:message code='acAccountCr'
                                                                             text='Account Cr'/></form:label>
                                <form:select path="acVoucherDtls[${varStatus.index}].acAccountCr" items="${acAccounts}"
                                             itemValue="id" class='form-control'/>
                                <form:errors path="acVoucherDtls[${varStatus.index}].acAccountCr" cssClass='error'
                                             element='div'/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <form:label path="acVoucherDtls[${varStatus.index}].narration"
                                            class='required'><spring:message code='narration'
                                                                             text='Narration'/></form:label>
                                <form:textarea path="acVoucherDtls[${varStatus.index}].narration" class='form-control'/>
                                <form:errors path="acVoucherDtls[${varStatus.index}].narration" cssClass='error'
                                             element='div'/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class='col-xs-1 col-sm-1 col-md-1 col-lg-12'>
                <div class='form-group'>
                    <a class="btn btn-primary btn-xs" onclick="dtlAdd('childOneDetails');">
                        <i class="fa fa-plus"></i>
                        Add More
                    </a>
                </div>
            </div>

            <div class='col-xs-1 col-sm-1 col-md-1 col-lg-12'>
                <div class='form-group'>
                    <button type='submit' class='btn btn-warning'>
                        <i class='fa fa-save'></i>&nbsp;<spring:message code='default.button.save.label' text='Save'/>
                    </button>
                </div>

                </form:form>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.box box-primary -->

<!--</div> /.content-wrapper -->

<script type='text/javascript'>
    function dtlRemove(selectorClass, _this) {
        $(_this).closest("." + selectorClass).remove();
        return true;
    }
    function dtlAdd(selectorClass) {
        var selectorIndex = $("." + selectorClass).length, cloneElement = "." + selectorClass + ":last";
        $(cloneElement).clone().insertAfter(cloneElement).show().find("*").each(function () {
            var name = this.name || "";
            this.name = name.replace(/\d+/, selectorIndex)
        });
        var removeBtn = '<div class="box-footer"><div class="pull-right"><a class="btn btn-block btn-danger btn-xs" onclick="dtlRemove(\'' + selectorClass + '\', this);"><i class="fa fa-minus"></i>Remove</a></div></div>';
        if ($(cloneElement + " > .box-footer").length == 0) {
            $(cloneElement + " > .box-body").after(removeBtn);
        }
        /*------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*var removeBtn = '<div class="pull-right" style="padding-right: 10px"><a class="btn btn-block btn-danger btn-xs" onclick="dtlRemove(\'' + selectorClass + '\', this);"><i class="fa fa-minus">Remove</i></a></div>';if ($(cloneElement + " > .box-footer").length == 0) {alert('dkjasldfj');$(cloneElement).after(removeBtn);}*/
        /*------------------------------------------------------------------------------------------------------------------------------------------------------*/
        $(cloneElement).find('input').val('');
        $(cloneElement).find('textArea').val('');
        $(cloneElement).find('select').val('');
        selectorIndex++;
        return true;
    }
</script>
</body>
</html>