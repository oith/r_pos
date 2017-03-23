<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<div class='col-xs-1 col-sm-1 col-md-1 col-lg-12'>
    <div class='form-group'>

        <a class="btn btn-primary btn-xs" onclick="dtlAdd('childOneDetails');">
            <i class="fa fa-plus"></i>
            <spring:message code='default.button.add.more.detail.label' text='Add More'/>
        </a>

        <c:choose>
            <c:when test="${admModule.admSubModules.size() > 0}">
                <c:forEach items='${admModule.admSubModules}' var='admSubModules' varStatus='varStatus'>
                    <div class="box box-info childOneDetails">
                        <div class="box-body">

                            <jsp:include page='form_AdmSubModules.jsp'>
                                <jsp:param name="idx" value="${varStatus.index}"/>
                            </jsp:include>

                            <%--MAC_VU_FORM_DIV_DETS--%>
                            <%--<form:hidden path="acVoucherDtls[${varStatus.index}].id"/>--%>
                            <%--<form:label path="acVoucherDtls[${varStatus.index}].osBusArea" class='required' ><spring:message code='osBusArea' text='Bus Area'/></form:label>--%>

                        </div>
                    </div>
                </c:forEach>
            </c:when>    
            <c:otherwise>
                <div class="box box-info childOneDetails">
                    <div class="box-body">

                        <jsp:include page='form_AdmSubModules.jsp'>
                            <jsp:param name="idx" value="0"/>
                        </jsp:include>

                        <%--MAC_VU_FORM_DIV_DETS--%>
                        <%--<form:hidden path="acVoucherDtls[0].id" class="form-control"/>--%>
                        <%--<form:select path="acVoucherDtls[0].osBusArea" class="form-control">--%>
                        <%--<form:option value="${null}">--SELECT--</form:option>--%>
                        <%--<form:options items="${osBusAreas}" itemValue="id" itemLabel="fullName" />--%>
                        <%--</form:select>--%>

                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div> 
</div> 