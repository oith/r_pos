<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="header">
    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/main" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>R</b><i>i</i><b>T</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Reflection</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/index"><i class="fa fa-globe"></i></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/refresh"><i class="fa fa-refresh"></i></a>
                    </li>

                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">

                        <sec:authorize access='isAuthenticated()'>
                            <sec:authentication var='fullName' property='principal.fullName'/>
                            <sec:authentication var='pic' property='principal.pic'/>
                            <sec:authentication var='email' property='principal.email'/>
                            <sec:authentication var='doj' property='principal.doj'/>

                            <!--<i class="fa fa-user" aria-hidden="true"></i>-->
                            <c:url var='macImage' value='/authUser/getPhoto/${pic}'/>

                            <!-- Menu Toggle Button -->
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <!-- The user image in the navbar-->
                                <img src="${macImage}" class="user-image" alt="">
                                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                <span class="hidden-xs">${fullName}</span>
                            </a>
                        </sec:authorize>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class='user-header'>
                                <img src='${macImage}' class='img-circle' alt=''>

                                <p>
                                    ${fullName}
                                    ${email}
                                    <small>Birthday ${dob}</small>
                                    <small>${pageContext.response.locale}</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class='col-xs-6 text-center'>
                                        <a href='?lang='><spring:message code='locale.default' text='English'/></a>
                                    </div>
                                    <div class='col-xs-6 text-center'>
                                        <a href='?lang=native'><spring:message code='locale.native' text='Native'/></a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${pageContext.request.contextPath}/logout"
                                       class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
</div>