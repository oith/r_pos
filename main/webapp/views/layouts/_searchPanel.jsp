
<%--<c:choose>
    <!-- search form -->
    <c:when test="${conditionForTopMenu}">
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" id="navbar-search-input" placeholder="Search">
            </div>
        </form>
    </c:when>
    <c:otherwise>--%>
<form action="#" method="get" class="sidebar-form">
    <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
    </div>
</form>
<%--    </c:otherwise>
</c:choose>--%>
<!-- /.search form -->