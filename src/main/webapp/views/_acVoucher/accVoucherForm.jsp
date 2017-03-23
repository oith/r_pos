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

            <div class='box-body'>
                <form:form action='${context}/accVoucher/${_action}/${acVoucherMst.id}' modelAttribute='acVoucherMst'
                           method='POST'>
                    <form:hidden path='id'/>
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
                            <form:label class='required' path='code'><spring:message code='code'
                                                                                     text='Code'/></form:label>
                            <form:input class='form-control' path='code' type='text' required='true' size='30'
                                        maxlength='20'/>
                            <form:errors path='code' cssClass='error' element='div'/>
                        </div>
                    </div>
                    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                        <div class='form-group'>
                            <form:label class='required' path='voucherDate'><spring:message code='documentDate'
                                                                                            text='Voucher Date'/></form:label>
                            <div class='input-group'>
                                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                                <form:input class='form-control dtp-date' path='voucherDate' required='true'/>
                            </div>
                            <form:errors path='voucherDate' cssClass='error' element='div'/>
                        </div>
                    </div>


                    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                        <div class='form-group'>
                            <form:label class='required' path='osLegalEntityEntity'><spring:message
                                    code='osLegalEntityEntity'
                                    text='Company/Legal Entity'/></form:label>
                            <form:select class='form-control' path='osLegalEntityEntity'
                                         items="${osLegalEntityEntities}"
                                         itemValue="id" required='true'/>
                            <form:errors path='osLegalEntityEntity' cssClass='error' element='div'/>
                        </div>
                    </div>

                    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
                        <div class='form-group'>
                            <form:label path='narration'><spring:message code='narration'
                                                                         text='Narration'/></form:label>
                            <form:textarea class='form-control' path='narration' type='text' size='30' maxlength='500'/>
                            <form:errors path='narration' cssClass='error' element='div'/>
                        </div>
                    </div>

                    <div class='col-xs-1 col-sm-1 col-md-1 col-lg-12'>
                        <div class='form-group'>

                            <a class="btn btn-primary btn-xs" onclick="dtlAdd('childOneDetails');">
                                <i class="fa fa-plus"></i>
                                Add More
                            </a>

                            <c:choose>
                                <c:when test="${acVoucherMst.acVoucherDtls.size() > 0}">
                                    <c:forEach items='${acVoucherMst.acVoucherDtls}' var='acVoucherDtls'
                                               varStatus='varStatus'>
                                        <div class="box box-info childOneDetails">
                                            <div class="box-body">
                                                <form:hidden path="acVoucherDtls[${varStatus.index}].id"/>

                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].osBusArea"
                                                                    class='required'><spring:message code='osBusArea'
                                                                                                     text='Bus Area'/></form:label>
                                                        <form:select path="acVoucherDtls[${varStatus.index}].osBusArea"
                                                                     class="form-control">
                                                            <form:option value="${null}">--SELECT--</form:option>
                                                            <form:options items="${osBusAreas}" itemValue="id"
                                                                          itemLabel="fullName"/>
                                                        </form:select>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].osBusArea"
                                                                     cssClass='error' element='div'/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].acAccount"
                                                                    class='required'><spring:message code='acAccount'
                                                                                                     text='Account'/></form:label>
                                                        <form:select path="acVoucherDtls[${varStatus.index}].acAccount"
                                                                     class="form-control">
                                                            <form:option value="${null}">--SELECT--</form:option>
                                                            <form:options items="${acAccounts}" itemValue="id"
                                                                          itemLabel="fullName"/>
                                                        </form:select>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].acAccount"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].emCurrency"
                                                                    class='required'><spring:message code='emCurrency'
                                                                                                     text='Currency'/></form:label>
                                                        <form:select class='form-control'
                                                                     path='acVoucherDtls[${varStatus.index}].emCurrency'
                                                                     required='true'>
                                                            <form:option value='BDT' label='BDT'/>
                                                            <form:option value='INR' label='INR'/>
                                                            <form:option value='EURO' label='EURO'/>
                                                            <form:option value='USD' label='USD'/>
                                                        </form:select>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].emCurrency"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label
                                                                path="acVoucherDtls[${varStatus.index}].osCostCenter"
                                                                class='required'><spring:message code='osCostCenter'
                                                                                                 text='Cost Center'/></form:label>

                                                        <form:select
                                                                path="acVoucherDtls[${varStatus.index}].osCostCenter"
                                                                class="form-control">
                                                            <form:option value="${null}">--SELECT--</form:option>
                                                            <form:options items="${osCostCenters}" itemValue="id"
                                                                          itemLabel="fullName"/>
                                                        </form:select>
                                                        <form:errors
                                                                path="acVoucherDtls[${varStatus.index}].osCostCenter"
                                                                cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].orderNo"
                                                                    class='required'><spring:message code='orderNo'
                                                                                                     text='order No'/></form:label>
                                                        <form:input type="text"
                                                                    path="acVoucherDtls[${varStatus.index}].orderNo"
                                                                    class="form-control" maxlength="20"/>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].orderNo"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].wbsElement"
                                                                    class='required'><spring:message code='wbsElement'
                                                                                                     text='wbs Element'/></form:label>
                                                        <form:input type="text"
                                                                    path="acVoucherDtls[${varStatus.index}].wbsElement"
                                                                    class="form-control" maxlength="20"/>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].wbsElement"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].narration"
                                                                    class='required'><spring:message code='narration'
                                                                                                     text='narration'/></form:label>
                                                        <form:input type="text"
                                                                    path="acVoucherDtls[${varStatus.index}].narration"
                                                                    class="form-control" maxlength="50"/>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].narration"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label
                                                                path="acVoucherDtls[${varStatus.index}].enteredAmount"
                                                                class='required'><spring:message code='enteredAmount'
                                                                                                 text='Entered Amount'/></form:label>
                                                        <form:input type="number"
                                                                    path="acVoucherDtls[${varStatus.index}].enteredAmount"
                                                                    class="form-control" align="left"/>
                                                        <form:errors
                                                                path="acVoucherDtls[${varStatus.index}].enteredAmount"
                                                                cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                    <div class="form-group">
                                                        <form:label path="acVoucherDtls[${varStatus.index}].emPosSign"
                                                                    class='required'><spring:message code='emPosSign'
                                                                                                     text='Sign'/></form:label>
                                                        <form:select path="acVoucherDtls[${varStatus.index}].emPosSign"
                                                                     class="form-control">
                                                            <form:option value='DR' label='Debit'/>
                                                            <form:option value='CR' label='Credit'/>
                                                        </form:select>
                                                        <form:errors path="acVoucherDtls[${varStatus.index}].emPosSign"
                                                                     cssClass='error' element='div'/>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="box box-info childOneDetails">
                                        <div class="box-body">

                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:hidden path="acVoucherDtls[0].id" class="form-control"/>
                                                        <%--<form:hidden path="acVoucherDtls[0].acVoucherMst.id" />--%>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:select path="acVoucherDtls[0].osBusArea" class="form-control">
                                                        <form:option value="${null}">--SELECT--</form:option>
                                                        <form:options items="${osBusAreas}" itemValue="id"
                                                                      itemLabel="fullName"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:select path="acVoucherDtls[0].acAccount" class="form-control">
                                                        <form:option value="${null}">--SELECT--</form:option>
                                                        <form:options items="${acAccounts}" itemValue="id"
                                                                      itemLabel="fullName"/>
                                                    </form:select>
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:select class='form-control' path='acVoucherDtls[0].emCurrency'
                                                                 required='true'>
                                                        <form:option value='BDT' label='BDT'/>
                                                        <form:option value='INR' label='INR'/>
                                                        <form:option value='EURO' label='EURO'/>
                                                        <form:option value='USD' label='USD'/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">

                                                    <form:select path="acVoucherDtls[0].osCostCenter"
                                                                 class="form-control">
                                                        <form:option value="${null}">--SELECT--</form:option>
                                                        <form:options items="${osCostCenters}" itemValue="id"
                                                                      itemLabel="fullName"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:input type="text" path="acVoucherDtls[0].orderNo"
                                                                class="form-control" maxlength="20"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:input type="text" path="acVoucherDtls[0].wbsElement"
                                                                class="form-control" maxlength="20"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:input type="text" path="acVoucherDtls[0].narration"
                                                                class="form-control" maxlength="50"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:input type="number" path="acVoucherDtls[0].enteredAmount"
                                                                class="form-control" align="left"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                                <div class="form-group">
                                                    <form:select path="acVoucherDtls[0].emPosSign" class="form-control">
                                                        <form:option value='DR' label='Debit'/>
                                                        <form:option value='CR' label='Credit'/>
                                                    </form:select>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>

                    <div class='col-xs-1 col-sm-1 col-md-1 col-lg-12'>
                        <div class='form-group'>
                            <button type='submit' class='btn btn-warning'>
                                <i class='fa fa-save'></i>&nbsp;<spring:message code='default.button.save.label'
                                                                                text='Save'/>
                            </button>
                        </div>
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

    function addRow(tableID) {

        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var colCount = table.rows[0].cells.length;

        for (var i = 0; i < colCount; i++) {

            var newcell = row.insertCell(i);

            newcell.innerHTML = table.rows[1].cells[i].innerHTML;
            //alert(newcell.childNodes);
            switch (newcell.childNodes[0].type) {
                case "text":
                    newcell.childNodes[0].value = "";
                    break;
                case "checkbox":
                    newcell.childNodes[0].checked = false;
                    break;
                case "select-one":
                    newcell.childNodes[0].selectedIndex = 0;
                    break;
            }
        }
    }

    function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;

            for (var i = 0; i < rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if (null != chkbox && true == chkbox.checked) {
                    if (rowCount <= 2) {
                        alert("Cannot delete all the rows.");
                        break;
                    }
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        } catch (e) {
            alert(e);
        }
    }
    function cls() {
        $('#salesLines').empty();

        if (!document.getElementById('isAdd').checked)
            document.getElementById('isAdd').checked = true;

        document.getElementById('mobile').value = '';
        document.getElementById('customerName').value = '';
        document.getElementById('customerName').disabled = 'true';
        document.getElementById('paidAmount').value = '';
        document.getElementById('salesTotal').value = '';
        document.getElementById('changeAmount').value = '';
        document.getElementById('quantity').value = 1;
        document.getElementById('barcode').value = '';
        document.getElementById('barcode').focus();
    }

    function posClear() {

        isClear = confirm('You really want to clear.');

        if (!isClear)
            return;

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/pos/clear',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val()
            },
            async: false,
            success: function (d) {
                if (d.status === 'ok')
                    cls();
                else
                    alert(d.message);
            }
            ,
            error: function (err) {
                alert('No Process Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function posSaveAndPrint() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/pos/saveAndPrint',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                mobile: $('#mobile').val(),
                customerName: $('#customerName').val(),
                paidAmount: $('#paidAmount').val()
            },
            async: false,
            success: function (d) {
                if (d.status === 'ok') {
                    cls();
                    pdfDoc.printAll();
                } else
                    alert(d.message);
            }
            ,
            error: function (err) {
                alert('No Process Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    document.getElementById('mobile')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    $.ajax({
                        type: 'GET',
                        url: '${pageContext.request.contextPath}/pos/getCustomer',
                        data: {
                            mobile: $('#mobile').val().trim()
                        },
                        async: false,
                        success: function (d) {
                            if (d === null || d === '') {
                                document.getElementById('customerName').value = '';
                                document.getElementById('customerName').disabled = '';
                                document.getElementById('customerName').focus();
                            } else {
                                document.getElementById('customerName').value = d.toString();
                                document.getElementById('customerName').disabled = 'true';
                            }
                        }
                        ,
                        error: function (err) {
                            alert('No Process Is Configured: ' + err);
                            hideAjaxLoadingImageProc();
                        }
                    });
                }
            });

    document.getElementById('paidAmount')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    chngAmnt = $('#paidAmount').val() - $('#salesTotal').val();
                    document.getElementById('changeAmount').value = chngAmnt;
                    document.getElementById('paidAmount').focus();
                }
            });

    document.getElementById('barcode')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    barcodeAction();
                }
            });

    window.onload = codeAddress;
    //$(document).ready(codeAddress);
    function barcodeActionSts() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/pos/barcodeActionSts',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val()
            },
            async: false,
            success: function (d) {
                //                        hideAjaxLoadingImageProc();

                if (d.status === 'ok') {
                    $('#salesLines').empty();

                    $('#salesLines').append(d.salesLines.toString());

                    document.getElementById('salesTotal').value = d.salesTotal;
                }
                if (!document.getElementById('isAdd').checked) {
                    document.getElementById('isAdd').checked = true;
                }
                document.getElementById('quantity').value = 1;
                document.getElementById('barcode').value = '';
                document.getElementById('barcode').focus();
            }
            ,
            error: function (err) {
                alert('No Process Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function barcodeAction() {
        barcode = $('#barcode').val().trim();
        quantity = $('#quantity').val().trim();

        if (!barcode) {
            return;
        }
        if (!quantity || quantity <= 0) {
            return;
        }

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/pos/barcodeAction',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                barcode: barcode,
                isAdd: document.getElementById('isAdd').checked,
                quantity: quantity
            },
            async: false,
            success: function (d) {
                //                        hideAjaxLoadingImageProc();

                if (d.message !== null && d.message !== '') {
                    alert(d.message);
                    return;
                }

                if (d.status === 'ok') {
                    $('#salesLines').empty();
                    $('#salesLines').append(d.salesLines.toString());
                    document.getElementById('salesTotal').value = d.salesTotal;
                }
                if (!document.getElementById('isAdd').checked) {
                    document.getElementById('isAdd').checked = true;
                }
                document.getElementById('quantity').value = 1;
                document.getElementById('barcode').value = '';
                document.getElementById('barcode').focus();
            }
            ,
            error: function (err) {
                alert('No Process Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }
</script>
</body>
</html>