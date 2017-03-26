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

                    <li><a href='<%=request.getContextPath()%>/PosAdjustmentMaster'><i class='fa fa-calculator'></i> <spring:message
                            code='PosAdjustmentMaster' text='POS Adjustment Master'/></a></li>

                    <li><a href='<%=request.getContextPath()%>/posSs'><i class='fa fa-calculator'></i> <spring:message
                            code='posSs' text='POS Collection'/></a></li>



                    <%--<li><a href='<%=request.getContextPath()%>/PosInvMovement'><i class='fa fa-calculator'></i> <spring:message
                            code='PosInvMovement' text='POS Inventory Movement'/></a></li>--%>

                    <%--Admin menu--%>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-recycle"></i> <span>Admin</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href='<%=request.getContextPath()%>/PosBranch'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosBranch' text='POS Branch'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/PosOpenBalance'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosOpenBalance' text='POS Opening Balance'/></a></li>
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
                            <li><a href='<%=request.getContextPath()%>/PosSupplier'><i class='fa fa-calculator'></i> <spring:message
                                    code='posSs' text='POS Supplier'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/posSs'><i class='fa fa-calculator'></i> <spring:message
                                    code='posSs' text='POS Purchase Master'/></a></li>
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
                            <li><a href='<%=request.getContextPath()%>/PosSalesMaster'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosSalesMaster' text='POS Sales Master'/></a></li>
                            <%-- <li><a href='<%=request.getContextPath()%>/PosCustomer'><i class='fa fa-calculator'></i> <spring:message
                                     code='PosCustomer' text='POS Customer'/></a></li>--%>
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
                            <li><a href='<%=request.getContextPath()%>/PosSalesMaster'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosSalesMaster' text='POS Sales Master'/></a></li>
                            <%-- <li><a href='<%=request.getContextPath()%>/PosCustomer'><i class='fa fa-calculator'></i> <spring:message
                                     code='PosCustomer' text='POS Customer'/></a></li>--%>
                            <li><a href='<%=request.getContextPath()%>/Adjustment'><i class='fa fa-calculator'></i> <spring:message
                                    code='Adjustment' text='POS Adjustment'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/PosInvMovement'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosInvMovement' text='POS Inventory Movement'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/PosWarehouse'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosWarehouse' text='POS Warehouse'/></a></li>

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
                            <li><a href='<%=request.getContextPath()%>/PosProductAc'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosProductAc' text='POS Product Analysis Code'/></a></li>

                            <li><a href='<%=request.getContextPath()%>/PosProduct'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosProduct' text='POS Product'/></a></li>
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
                            <li><a href='<%=request.getContextPath()%>/PosPayment'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosPayment' text='POS Payment'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/PosCollection'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosCollection' text='POS Collection'/></a></li>
                            <li><a href='<%=request.getContextPath()%>/PosCustomer'><i class='fa fa-calculator'></i> <spring:message
                                    code='PosCustomer' text='POS Customer'/></a></li>
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