<aside class="control-sidebar control-sidebar-dark"><!-- Control Sidebar -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs"><!-- Create the tabs -->
        <li>
            <a href="#control-sidebar-settings-tab" data-toggle="tab">
                <i class="fa fa-gears"></i>
            </a>
        </li>
        <li>
            <a href="#control-sidebar-home-tab" data-toggle="tab">
                <i class="fa fa-home"></i>
            </a>
        </li>
    </ul>
    <div class="tab-content"><!-- Tab panes -->
        <!-- Settings tab content -->
        <div th:include="layouts/_tabPaneGeneralSettings :: _tabPaneGeneralSettings"></div>

        <!-- Home tab content -->
        <div th:include="layouts/_tabPaneHome :: _tabPaneHome"></div>

        <!-- Stats tab content -->
        <div th:include="layouts/_tabPaneStatus :: _tabPaneStatus"></div>


    </div>
</aside>
<!-- /.control-sidebar -->
<div class="control-sidebar-bg"></div>
<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->