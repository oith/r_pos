<!-- Main Header -->

<header class="main-header">
    <nav class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${context}/" class="navbar-brand">
                    <b>R</b><i>-L</i>
                </a>

                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse">
                    <i class="fa fa-bars"></i>
                </button>
            </div>

            <div class="collapse navbar-collapse pull-left dropDownMenu" id="navbar-collapse">

                <th:block layout:include="layouts/_authMenuTop"/>

                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" id="navbar-search-input" placeholder="Search"/>
                    </div>
                </form>
            </div>

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
            <!-- /.navbar-custom-menu -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</header>