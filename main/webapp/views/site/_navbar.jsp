<%-- 
    Document   : _navbar
    Created on : Jun 3, 2016, 10:56:31 PM
    Author     : hoshen.mahmud
--%>
<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <sec:authorize access='isAuthenticated()'>
                    <sec:authentication var='displayName' property='principal.displayName'/>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/main">Dashboard
                        - ${displayName}</a>
                </sec:authorize>
                <sec:authorize access='isAnonymous()'>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/login">Login</a><br/>
                </sec:authorize>

            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" href="#page-top">Home</a></li>
                    <li><a class="page-scroll" href="#features">Features</a></li>
                    <li><a class="page-scroll" href="#team">Team</a></li>
                    <li><a class="page-scroll" href="#testimonials">Testimonials</a></li>
                    <li><a class="page-scroll" href="#pricing">Pricing</a></li>
                    <li><a class="page-scroll" href="#contact">Contact</a></li>
                    <sec:authorize access='isAuthenticated()'>
                        <li>
                            <a class="page-scroll" href="${pageContext.request.contextPath}/logout"
                               onclick="prompt('Are you sure...?')">Logout</a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</div>