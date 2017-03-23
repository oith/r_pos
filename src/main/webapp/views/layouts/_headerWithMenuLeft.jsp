<!-- Main Header -->
<header class="main-header">
    <!-- Logo -->
    <a href="${request.contextPath}/" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>R</b>-<i>L</i></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Reflection</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <th:block layout:include="layouts/_listMessage"/>
                <th:block layout:include="layouts/_listNotification"/>
                <th:block layout:include="layouts/_listTask"/>
                <th:block layout:include="layouts/_userPanelTop"/>

                <!-- Control Sidebar Toggle Button -->
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<th:block layout:include="layouts/_sideBarLeft"/>
