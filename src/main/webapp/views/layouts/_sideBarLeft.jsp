<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel -->
        <%@ include file='/views/layouts/_userPanelLeft.jsp' %>

        <!-- search form -->
        <%--<%@ include file='/views/layouts/_searchPanel.jsp' %>--%>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class='active treeview'>
                <a href='#'>
                    <i class='fa fa-gears'></i> 
                    <span>
                        <spring:message code='controllersStatic' text='Common'/>
                    </span> 
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class='treeview-menu'>
                    <li><a href='<%=request.getContextPath()%>/refresh'><i class='fa fa-refresh'></i> <spring:message
                            code='refresh' text='Refresh'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/posBc'><i class='fa fa-sellsy'></i> <spring:message
                            code='posBc' text='POS Barcode'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/posSs'><i class='fa fa-calculator'></i> <spring:message
                            code='posSs' text='POS Slow Sale'/></a></li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Admin</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posBranch'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosBranch' text='Branch'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posOpenBalance'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosOpenBalance' text='Opening Balance'/></a></li>
                        </ul>
                    </li>
                    <%--purchase--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Purchase</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posSupplier'><i class='fa fa-calculator'></i> <spring:message
                                    code='posSs' text='Supplier'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posSs'><i class='fa fa-calculator'></i> <spring:message
                                    code='posSs' text='Purchase'/></a></li>
                        </ul>
                    </li>
                    <%--sales--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Sales</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posSalesMaster'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosSalesMaster' text='Sales'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posCustomer'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosCustomer' text='Customer'/></a></li>
                        </ul>
                    </li>
                    <%--Inventory--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Inventory</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posAdjustmentMaster'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosAdjustmentMaster' text='Adjustment'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posInvMovement'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosInvMovement' text='Inventory Movement'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posWarehouse'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosWarehouse' text='Warehouse'/></a></li>
                        </ul>
                    </li>
                    <%--Common--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Common</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posProductAc'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosProductAc' text='Product Analysis Code'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posProduct'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosProduct' text='Product'/></a></li>
                        </ul>
                    </li>

                    <%--accounts--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Accounts</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/posPayment'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosPayment' text='Payment'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posCollection'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosCollection' text='Collection'/></a></li>
                        </ul>
                    </li>

                    <li><a href='<%=request.getContextPath()%>/xmlFileUpload'><i class='fa fa-file'></i> <spring:message
                            code='xmlFileUpload' text='XML File Upload'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/excelFileUpload'><i class='fa fa-file'></i>
                        <spring:message code='excelFileUpload' text='Excel File Upload'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/queryCenter'><i class='fa fa-search'></i> <spring:message
                            code='queryCenter' text='Query Center'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/processCenter'><i class='fa fa-gears'></i>
                        <spring:message code='processCenter' text='Process Center'/></a></li>
                    <li><a href='<%=request.getContextPath()%>/reportCenter'><i class='fa fa-info'></i> <spring:message
                            code='reportCenter' text='Report Center'/></a></li>
                </ul>
            </li>
            <!--<li class="header">MAIN NAVIGATION</li>-->
            <!--<menu:loadMenu type="MAIN_MENU"/>-->
            <sec:authorize access='isAuthenticated()'>
                <sec:authentication var='completeMenu' property='principal.completeMenu'/>
            </sec:authorize>
            ${completeMenu}
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<!-- /.main-sidebar -->