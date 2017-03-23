<ul class="nav navbar-nav">

    <li class="dropdown">
        <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#settings">
            <i class="fa fa-cog"></i>
            <spring:message code="authMenu.settings.label" text="Common"/>
            <span class="caret pull-right"></span>
        </a>
        <ul class="dropdown-menu" role="menu">
            <!--<ul class='treeview-menu'>-->
            <li><a href='/refresh'><i class='fa fa-refresh'></i> <spring:message
                    code='refresh' text='Refresh'/></a></li>
            <li><a href='/xmlFileUpload'><i class='fa fa-file'></i> <spring:message
                    code='xmlFileUpload' text='XML File Upload'/></a></li>
            <li><a href='/excelFileUpload'><i class='fa fa-file'></i> <spring:message
                    code='excelFileUpload' text='Excel File Upload'/></a></li>
            <li><a href='/queryCenter'><i class='fa fa-search'></i> <spring:message
                    code='queryCenter' text='Query Center'/></a></li>
            <li><a href='/processCenter'><i class='fa fa-gears'></i> <spring:message
                    code='processCenter' text='Process Center'/></a></li>
            <li><a href='/reportCenter'><i class='fa fa-info'></i> <spring:message
                    code='reportCenter' text='Report Center'/></a></li>
        </ul>
    </li>

    <sec:authorize access='isAuthenticated()'>
        <sec:authentication var='completeMenu' property='principal.completeMenu'/>
    </sec:authorize>
    ${completeMenu}


    <sec:authorize url="/#settings">
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#settings">
                <i class="fa fa-cog"></i>
                <spring:message code="authMenu.settings.label" text="Settings"/>
                <span class="caret pull-right"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <sec:authorize url="/#user">
                    <li>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#user">
                            <i class="fa fa-circle-o text-red"></i>
                            <spring:message code="authMenu.user.label" text="User"/>
                            <span class="caret pull-right"></span>
                        </a>
                        <ul role="menu">
                            <sec:authorize url="/user/index">
                                <li>
                                    <a href="${contextPath}/user/index">
                                        <i class="fa fa-circle-o text-primary"></i>
                                        <spring:message code="authMenu.userList.label" text="User - List"/>
                                    </a>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/user/create">
                                <li>
                                    <a href="${contextPath}/user/create">
                                        <i class="fa fa-circle-o"></i>
                                        <spring:message code="authMenu.userCreate.label" text="User - Create"/>
                                    </a>
                                </li>
                            </sec:authorize>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/#role">
                    <li>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#role">
                            <i class="fa fa-cog"></i>
                            <spring:message code="authMenu.role.label" text="Role"/>
                            <span class="caret pull-right"></span>
                        </a>
                        <ul role="menu">
                            <sec:authorize url="/role/index">
                                <li>
                                    <a href="${contextPath}/role/index">
                                        <i class="fa fa-circle-o text-primary"></i>
                                        <spring:message code="authMenu.roleList.label" text="Role - List"/>
                                    </a>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/role/create">
                                <li>
                                    <a href="${contextPath}/role/create">
                                        <i class="fa fa-circle-o"></i>
                                        <spring:message code="authMenu.roleCreate.label" text="Role - Create"/>
                                    </a>
                                </li>
                            </sec:authorize>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/#menu">
                    <li>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#menu">
                            <i class="fa fa-cog"></i>
                            <spring:message code="authMenu.menu.label" text="Menu"/>
                            <span class="caret pull-right"></span>
                        </a>
                        <ul role="menu">
                            <sec:authorize url="/menu/index">
                                <li>
                                    <a href="${contextPath}/menu/index">
                                        <i class="fa fa-circle-o text-primary"></i>
                                        <spring:message code="authMenu.menuList.label" text="Menu - List"/>
                                    </a>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/menu/create">
                                <li>
                                    <a href="${contextPath}/menu/create">
                                        <i class="fa fa-circle-o"></i>
                                        <spring:message code="authMenu.menuCreate.label" text="Menu - Create"/>
                                    </a>
                                </li>
                            </sec:authorize>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/#mapping">
                    <li>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#mapping">
                            <i class="fa fa-cog"></i>
                            <spring:message code="authMenu.mapping.label" text="Mapping"/>
                            <span class="caret pull-right"></span>
                        </a>
                        <ul role="menu">
                            <sec:authorize url="/#userRole">
                                <li>
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#userRole">
                                        <i class="fa fa-cog"></i>
                                        #{"authMenu.userRole.label"}
                                        <span class="caret pull-right"></span>
                                    </a>
                                    <ul role="menu">
                                        <sec:authorize url="/userRole/index">
                                            <li>
                                                <a href="${contextPath}/userRole/index">
                                                    <i class="fa fa-circle-o text-primary"></i>
                                                    <spring:message code="authMenu.userRoleList.label"
                                                                    text="UserRole - List"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                        <sec:authorize url="/userRole/create">
                                            <li>
                                                <a href="${contextPath}/userRole/create">
                                                    <i class="fa fa-circle-o"></i>
                                                    <spring:message code="authMenu.userRoleCreate.label"
                                                                    text="UserRole - Create"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/#reportRole">
                                <li>
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#reportRole">
                                        <i class="fa fa-cog"></i>
                                        #{authMenu.reportRole.label}
                                        <span class="caret pull-right"></span>
                                    </a>
                                    <ul role="menu">
                                        <sec:authorize url="/reportRole/index">
                                            <li>
                                                <a href="${contextPath}/reportRole/index">
                                                    <i class="fa fa-circle-o text-primary"></i>
                                                    <spring:message code="authMenu.reportRoleList.label"
                                                                    text="ReportRole - List"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                        <sec:authorize url="/reportRole/create">
                                            <li>
                                                <a href="${contextPath}/reportRole/create">
                                                    <i class="fa fa-circle-o"></i>
                                                    <spring:message code="authMenu.reportRoleCreate.label"
                                                                    text="ReportRole - Create"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/#requestMap">
                                <li>
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="${contextPath}/#requestMap">
                                        <i class="fa fa-cog"></i>
                                        <spring:message code="authMenu.urlMapping.label" text="Request/URL"/>
                                        <span class="caret pull-right"></span>
                                    </a>
                                    <ul role="menu">
                                        <sec:authorize url="/requestMap/index">
                                            <li>
                                                <a href="${contextPath}/requestMap/index">
                                                    <i class="fa fa-circle-o text-primary"></i>
                                                    <spring:message code="authMenu.requestMapList.label"
                                                                    text="Request Map - List"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                        <sec:authorize url="/requestMap/create">
                                            <li>
                                                <a href="${contextPath}/requestMap/create">
                                                    <i class="fa fa-circle-o"></i>
                                                    <spring:message code="authMenu.requestMapCreate.label"
                                                                    text="Request Map - Create"/>
                                                </a>
                                            </li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </sec:authorize>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </li>
    </sec:authorize>

    <sec:authorize url="/#reporting">
        <li class="dropdown">
            <a href="${contextPath}/#reporting" class="dropdown-toggle" data-toggle="dropdown">
                <i class="fa fa-flag-o"></i>
                <spring:message code="authMenu.reporting.label" text="Reporting"/>
                <span class="caret pull-right"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <sec:authorize url="/_AdmRpt/index">
                    <li><a href="${contextPath}/_AdmRpt/index"><i class="fa fa-circle-o"></i><span>All Report</span></a>
                    </li>
                </sec:authorize>
            </ul>
        </li>
    </sec:authorize>
</ul>