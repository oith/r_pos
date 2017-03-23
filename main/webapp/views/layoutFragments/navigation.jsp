

<sec:authorize access='isAuthenticated()'>
    <sec:authentication var='menuOrientation' property='principal.menuOrientation'/>
</sec:authorize>

<c:choose>
    <c:when test="${menuOrientation == 'MENU_TOP'}">
        <c:set var="bodyClass" value="hold-transition skin-blue layout-top-nav"/>
    </c:when>
    <c:when test="${menuOrientation == 'MENU_LEFT'}">
        <c:set var="bodyClass" value="hold-transition skin-blue sidebar-collapse sidebar-mini"/>
    </c:when>
    <c:when test="${menuOrientation == 'MENU_NONE'}">
        <c:set var="bodyClass" value="hold-transition skin-blue layout-top-nav"/>
    </c:when>
</c:choose>


<div class="wrapper">
    <c:choose>
        <c:when test="${menuOrientation == 'MENU_TOP'}">
            <th:block layout:include="layouts/_headerWithMenuTop"/>

        </c:when>
        <!--/*
        <%--<c:when test="${menuOrientation == 'MENU_LEFT'}">--%>
        <%--<th:block layout:include="layouts/_headerWithMenuLeft"/>--%>

        <%--</c:when>--%>
        <%--<c:when test="${menuOrientation == 'MENU_NONE'}">--%>
        <%--<th:block layout:include="layouts/_headerWithMenuNone"/>--%>

        <%--</c:when>--%>
        */-->
    </c:choose>
</div>