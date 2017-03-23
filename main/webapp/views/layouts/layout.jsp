<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<!DOCTYPE html>
<html>
<c:set var="bodyClass" value="hold-transition skin-blue sidebar-collapse sidebar-mini"/>
<head>
    <meta http-equiv='Content-Type' content='text/html;charset=utf-8'/>
    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>
    <meta name='viewport' content='width=device-width,initial-scale=1'>

    <title><sitemesh:write property='title' default='Reflection'/></title>

    <!-- favicon -->
    <link rel='shortcut icon' type='image/x-icon' href='${pageContext.request.contextPath}/resources/images/favicon.ico'/>

    <%@ include file='_css.jsp' %>

    <sitemesh:write property='head'/>

</head>
<body class="${bodyClass}">

<%@ include file='_header.jsp' %>
<div class="wrapper">

    <%@ include file='_sideBarLeft.jsp' %>

    <sec:authorize access='isAuthenticated()'>
        <sec:authentication var='menuOrientation' property='principal.menuOrientation'/>
    </sec:authorize>

    <%--<c:set var="menuType" value="MENU_TOP"/>--%>
    <%--<c:set var="menuOrientation" value="MENU_LEFT"/>--%>
    <%--<c:set var="menuType" value="MENU_NONE"/>--%>

    <c:choose>
        <c:when test="${menuOrientation == 'MENU_TOP'}">
            <c:set var="bodyClass" value="hold-transition skin-blue sidebar-mini sidebar-collapse fixed"/>
            <%--<c:set var="bodyClass" value="hold-transition skin-blue layout-top-nav"/>--%>
        </c:when>
        <c:when test="${menuOrientation == 'MENU_LEFT'}">
            <c:set var="bodyClass" value="hold-transition skin-blue sidebar-collapse sidebar-mini"/>
        </c:when>
        <c:when test="${menuOrientation == 'MENU_NONE'}">
            <c:set var="bodyClass" value="hold-transition skin-blue layout-top-nav"/>
        </c:when>
    </c:choose>

    <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
        <%@ include file='_js.jsp' %>
        <sitemesh:write property='body'/>
    </div>
    <!-- /.content-wrapper -->
    <%@ include file='_sideBarRight.jsp' %>

    <div class="control-sidebar-bg"></div>
    <%@ include file='_footer.jsp' %>
</div>

</body>
</html>
