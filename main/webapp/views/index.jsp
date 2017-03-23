<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="this is demo"/>
    <meta name="author" content="manik"/>
    <title>Reflection - Welcome Page</title>

    <jsp:include page="layouts/_css.jsp"/>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/site/hms/css/hms.css" rel="stylesheet"/>
    <!-- Animation CSS -->
    <link href="${pageContext.request.contextPath}/resources/plugins/animate.css/animate.css" rel="stylesheet"/>
</head>
<body id="page-top" class="landing-page">
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
                    <a class="navbar-brand" href="main"><spring:message code='dashboard' text='Dashboard'/>
                        - ${displayName}</a>
                </sec:authorize>
                <sec:authorize access='isAnonymous()'>
                    <a class="navbar-brand" href="login">
                        <spring:message code='login' text='Login'/>
                    </a><br/>
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
                        <li><a class="page-scroll" href="logout">Logout</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
        <li data-target="#inSlider" data-slide-to="1"></li>
        <li data-target="#inSlider" data-slide-to="2"></li>
        <li data-target="#inSlider" data-slide-to="3"></li>
    </ol>
    <div class="carousel-inner" role="listbox">

        <div class="item active">
            <!--                    <div class="container">
                                    <div class="carousel-caption">
                                        <h1>We craft<br/>
                                            brands, web apps,<br/>
                                            and user interfaces<br/>
                                            we are IN+ studio</h1>
                                        <p>Lorem Ipsum is simply dummy text of the printing.</p>
                                        <p>
                                            <a class="btn btn-lg btn-primary" href="#" role="button">READ MORE</a>
                                            <a class="caption-link" href="#" role="button">Reflection Theme</a>
                                        </p>
                                    </div>
                                    <div class="carousel-image wow zoomIn">
                                        <%--<img src="${pageContext.request.contextPath}/resources/site/img/landing/laptop.png" alt="laptop"/>--%>
                                    </div>
                                </div>-->
            <!-- Set background for slide in css -->
            <div class="header-back one"></div>
        </div>

        <div class="item">
            <!--                    <div class="container">
                                    <div class="carousel-caption blank">
                                        <h1>We create meaningful <br/> interfaces that inspire.</h1>
                                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
                                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                                    </div>
                                </div>-->
            <!-- Set background for slide in css -->
            <div class="header-back two"></div>
        </div>

        <div class="item">
            <!--                    <div class="container">
                                    <div class="carousel-caption blank">
                                        <h1>We create meaningful <br/> interfaces that inspire.</h1>
                                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
                                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                                    </div>
                                </div>-->
            <!-- Set background for slide in css -->
            <div class="header-back three"></div>
        </div>

        <div class="item">
            <!--                    <div class="container">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="carousel-caption blank">
                                            <h1>We create meaningful <br/> interfaces that inspire.</h1>
                                            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
                                            <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                                        </div>
                                    </div>
                                </div>-->
            <!-- Set background for slide in css -->
            <div class="header-back four"></div>
        </div>

    </div>
    <a class="left carousel-control" href="#inSlider" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#inSlider" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<section id="features" class="container services">
    <div class="row">
        <div class="col-sm-3">
            <h2>Full responsive</h2>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                vehicula ut id elit. Morbi leo risus.</p>

            <p><a class="navy-link" href="#" role="button">Details &raquo;</a></p>
        </div>
        <div class="col-sm-3">
            <h2>LESS/SASS Files</h2>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                vehicula ut id elit. Morbi leo risus.</p>

            <p><a class="navy-link" href="#" role="button">Details &raquo;</a></p>
        </div>
        <div class="col-sm-3">
            <h2>6 Charts Library</h2>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                vehicula ut id elit. Morbi leo risus.</p>

            <p><a class="navy-link" href="#" role="button">Details &raquo;</a></p>
        </div>
        <div class="col-sm-3">
            <h2>Advanced Forms</h2>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                vehicula ut id elit. Morbi leo risus.</p>

            <p><a class="navy-link" href="#" role="button">Details &raquo;</a></p>
        </div>
    </div>
</section>

<section class="container features">
    <div class="row">
        <div class="col-lg-12 text-center">
            <div class="navy-line"></div>
            <h1>Over 40+ unique view<br/> <span class="navy"> with many custom components</span></h1>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 text-center wow fadeInLeft">
            <div>
                <i class="fa fa-mobile features-icon"></i>

                <h2>Full responsive</h2>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                    vehicula ut id elit. Morbi leo risus.</p>
            </div>
            <div class="m-t-lg">
                <i class="fa fa-bar-chart features-icon"></i>

                <h2>6 Charts Library</h2>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                    vehicula ut id elit. Morbi leo risus.</p>
            </div>
        </div>
        <div class="col-md-6 text-center  wow zoomIn">
            <%--<!--<img src="${pageContext.request.contextPath}/resources/site/img/landing/perspective.png" alt="dashboard" class="img-responsive">-->--%>
            <img src="${pageContext.request.contextPath}/resources/site/hms/images/b12.jpg" alt="dashboard"
                 class="img-responsive">
        </div>
        <div class="col-md-3 text-center wow fadeInRight">
            <div>
                <i class="fa fa-envelope features-icon"></i>

                <h2>Mail pages</h2>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                    vehicula ut id elit. Morbi leo risus.</p>
            </div>
            <div class="m-t-lg">
                <i class="fa fa-google features-icon"></i>

                <h2>AngularJS version</h2>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies
                    vehicula ut id elit. Morbi leo risus.</p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 text-center">
            <div class="navy-line"></div>
            <h1>Discover great feautres</h1>

            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
        </div>
    </div>
    <div class="row features-block">
        <div class="col-lg-6 features-text wow fadeInLeft">
            <small>Reflection</small>
            <h2>Perfectly designed </h2>

            <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It
                has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
            <a href="#" class="btn btn-primary">Learn more</a>
        </div>
        <div class="col-lg-6 text-right wow fadeInRight">
            <%--<!--<img src="${pageContext.request.contextPath}/resources/site/img/landing/dashboard.png" alt="dashboard" class="img-responsive pull-right">-->--%>
            <img src="${pageContext.request.contextPath}/resources/site/hms/images/b7.jpg" alt="dashboard"
                 class="img-responsive pull-right">
        </div>
    </div>
</section>

<section id="team" class="gray-section team">
    <div class="container">
        <div class="row m-b-lg">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>Our Team</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4 wow fadeInLeft">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/images/user_images/saif_khan.jpg"
                         class="img-responsive img-circle img-small"
                         alt="saif_khan">
                    <h4><span class="navy">Saif</span> Khan</h4>

                    <p>Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus. </p>
                    <ul class="list-inline social-icon">
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="https://www.facebook.com/saif.hmk" target="_blank"><i
                                class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="team-member wow zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/user_images/badiuzzaman.jpg"
                         class="img-responsive img-circle"
                         alt="badiuzzaman">
                    <h4><span class="navy">Badiuzzaman</span> Manik</h4>

                    <p>Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus.</p>
                    <ul class="list-inline social-icon">
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="https://www.facebook.com/mohammad.badiuzzaman" target="_blank"><i
                                class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-4 wow fadeInRight">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/images/user_images/anisur_rahman.jpg"
                         class="img-responsive img-circle img-small" alt="anisur_rahman">
                    <h4><span class="navy">Anisur Rahman</span> Khan</h4>

                    <p>Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus.</p>
                    <ul class="list-inline social-icon">
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="https://www.facebook.com/akash.babu.359" target="_blank"><i
                                class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg m-b-lg">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos non
                    quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
            </div>
        </div>
    </div>
</section>

<section class="features">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>Even more great feautres</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
            </div>
        </div>
        <div class="row features-block">
            <div class="col-lg-3 features-text wow fadeInLeft">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
                <a href="#" class="btn btn-primary">Learn more</a>
            </div>
            <div class="col-lg-6 text-right m-t-n-lg wow zoomIn">
                <%--<!--<img src="${pageContext.request.contextPath}/resources/site/img/landing/iphone.jpg" class="img-responsive" alt="dashboard">-->--%>
                <img src="${pageContext.request.contextPath}/resources/site/hms/images/b15.jpg" class="img-responsive"
                     alt="dashboard">
            </div>
            <div class="col-lg-3 features-text text-right wow fadeInRight">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
                <a href="#" class="btn btn-primary">Learn more</a>
            </div>
        </div>
    </div>

</section>

<section class="timeline gray-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>Our workflow</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
            </div>
        </div>
        <div class="row features-block">

            <div class="col-lg-12">
                <div id="vertical-timeline" class="vertical-container light-timeline center-orientation">
                    <div class="vertical-timeline-block">
                        <div class="vertical-timeline-icon navy-bg">
                            <i class="fa fa-briefcase"></i>
                        </div>

                        <div class="vertical-timeline-content">
                            <h2>Meeting</h2>

                            <p>Conference on the sales results for the previous year. Monica please examine sales trends
                                in marketing and products. Below please find the current status of the sale.
                            </p>
                            <a href="#" class="btn btn-xs btn-primary"> More info</a>
                            <span class="vertical-date"> Today <br/> <small>Dec 24</small> </span>
                        </div>
                    </div>

                    <div class="vertical-timeline-block">
                        <div class="vertical-timeline-icon navy-bg">
                            <i class="fa fa-file-text"></i>
                        </div>

                        <div class="vertical-timeline-content">
                            <h2>Decision</h2>

                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                                has been the industry's standard dummy text ever since.</p>
                            <a href="#" class="btn btn-xs btn-primary"> More info</a>
                            <span class="vertical-date"> Tomorrow <br/> <small>Dec 26</small> </span>
                        </div>
                    </div>

                    <div class="vertical-timeline-block">
                        <div class="vertical-timeline-icon navy-bg">
                            <i class="fa fa-cogs"></i>
                        </div>

                        <div class="vertical-timeline-content">
                            <h2>Implementation</h2>

                            <p>Go to shop and find some products. Lorem Ipsum is simply dummy text of the printing and
                                typesetting industry. Lorem Ipsum has been the industry's. </p>
                            <a href="#" class="btn btn-xs btn-primary"> More info</a>
                            <span class="vertical-date"> Monday <br/> <small>Jan 02</small> </span>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>

</section>

<section id="testimonials" class="navy-section testimonials" style="margin-top: 0">

    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center wow zoomIn">
                <i class="fa fa-comment big-icon"></i>

                <h1>
                    What our users say
                </h1>

                <div class="testimonials-text">
                    <i>"Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model
                        text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various
                        versions have evolved over the years, sometimes by accident, sometimes on purpose (injected
                        humour and the like)."</i>
                </div>
                <small>
                    <strong>12.02.2014 - Andy Smith</strong>
                </small>
            </div>
        </div>
    </div>

</section>

<section class="comments gray-section" style="margin-top: 0">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>What our partners say</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada. </p>
            </div>
        </div>
        <div class="row features-block">
            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years,
                    sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="#" class="pull-left">
                        <img alt="image"
                             src="${pageContext.request.contextPath}/resources/images/user_images/m_saifullah.jpg">
                    </a>

                    <div class="media-body">
                        <div class="commens-name">
                            Muhammed Saifullah
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years,
                    sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="#" class="pull-left">
                        <img alt="image"
                             src="${pageContext.request.contextPath}/resources/images/user_images/asif_mahmud.jpg">
                    </a>

                    <div class="media-body">
                        <div class="commens-name">
                            Asif Mahmud
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years,
                    sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="#" class="pull-left">
                        <img alt="image"
                             src="${pageContext.request.contextPath}/resources/images/user_images/dk_dakua.jpg">
                    </a>

                    <div class="media-body">
                        <div class="commens-name">
                            Debashis Kumar Dakua
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<section class="features">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>More and more extra great feautres</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>
                <i class="fa fa-bar-chart big-icon pull-right"></i>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with.</p>
            </div>
            <div class="col-lg-5 features-text">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>
                <i class="fa fa-bolt big-icon pull-right"></i>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>
                <i class="fa fa-clock-o big-icon pull-right"></i>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with.</p>
            </div>
            <div class="col-lg-5 features-text">
                <small>Reflection</small>
                <h2>Perfectly designed </h2>
                <i class="fa fa-users big-icon pull-right"></i>

                <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully
                    responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query.
                    It has a huge collection of reusable UI components and integrated with.</p>
            </div>
        </div>
    </div>

</section>
<section id="pricing" class="pricing">
    <div class="container">
        <div class="row m-b-lg">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>App Pricing</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 wow zoomIn">
                <ul class="pricing-plan list-unstyled">
                    <li class="pricing-title">
                        Basic
                    </li>
                    <li class="pricing-desc">
                        Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus.
                    </li>
                    <li class="pricing-price">
                        <span>$16</span> / month
                    </li>
                    <li>
                        Dashboards
                    </li>
                    <li>
                        Projects view
                    </li>
                    <li>
                        Contacts
                    </li>
                    <li>
                        Calendar
                    </li>
                    <li>
                        AngularJs
                    </li>
                    <li>
                        <a class="btn btn-primary btn-xs" href="#">Signup</a>
                    </li>
                </ul>
            </div>

            <div class="col-lg-4 wow zoomIn">
                <ul class="pricing-plan list-unstyled selected">
                    <li class="pricing-title">
                        Standard
                    </li>
                    <li class="pricing-desc">
                        Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus.
                    </li>
                    <li class="pricing-price">
                        <span>$22</span> / month
                    </li>
                    <li>
                        Dashboards
                    </li>
                    <li>
                        Projects view
                    </li>
                    <li>
                        Contacts
                    </li>
                    <li>
                        Calendar
                    </li>
                    <li>
                        AngularJs
                    </li>
                    <li>
                        <strong>Support platform</strong>
                    </li>
                    <li class="plan-action">
                        <a class="btn btn-primary btn-xs" href="#">Signup</a>
                    </li>
                </ul>
            </div>

            <div class="col-lg-4 wow zoomIn">
                <ul class="pricing-plan list-unstyled">
                    <li class="pricing-title">
                        Premium
                    </li>
                    <li class="pricing-desc">
                        Lorem ipsum dolor sit amet, illum fastidii dissentias quo ne. Sea ne sint animal iisque, nam an
                        soluta sensibus.
                    </li>
                    <li class="pricing-price">
                        <span>$160</span> / month
                    </li>
                    <li>
                        Dashboards
                    </li>
                    <li>
                        Projects view
                    </li>
                    <li>
                        Contacts
                    </li>
                    <li>
                        Calendar
                    </li>
                    <li>
                        AngularJs
                    </li>
                    <li>
                        <a class="btn btn-primary btn-xs" href="#">Signup</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row m-t-lg">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg">
                <p>*Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model
                    text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. <span
                            class="navy">Various versions</span> have evolved over the years, sometimes by accident,
                    sometimes on purpose (injected humour and the like).</p>
            </div>
        </div>
    </div>

</section>

<section id="contact" class="gray-section contact">
    <div class="container">
        <div class="row m-b-lg">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>Contact Us</h1>

                <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.</p>
            </div>
        </div>
        <div class="row m-b-lg">
            <div class="col-lg-3 col-lg-offset-3">
                <address>
                    <strong><span class="navy">Maternal and Child Health Training Institute</span></strong><br/>
                    Azimpur Road<br/>
                    Dhaka-1205, Bangladesh<br/>
                    <abbr title="Phone">P:</abbr> (123) 456-7890
                </address>
            </div>
            <div class="col-lg-4">
                <p class="text-color">
                    Consectetur adipisicing elit. Aut eaque, totam corporis laboriosam veritatis quis ad perspiciatis,
                    totam corporis laboriosam veritatis, consectetur adipisicing elit quos non quis ad perspiciatis,
                    totam corporis ea,
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <a href="mailto:test@email.com" class="btn btn-primary">Send us mail</a>

                <p class="m-t-sm">
                    Or follow us on social platform
                </p>
                <ul class="list-inline social-icon">
                    <li><a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg m-b-lg">
                <p><strong>&copy; 2016 Maternal and Child Health Training Institute</strong><br/> consectetur
                    adipisicing elit. Aut eaque, laboriosam veritatis, quos non quis ad perspiciatis, totam corporis ea,
                    alias ut unde.</p>
            </div>
        </div>
    </div>
</section>

<jsp:include page="layouts/_js.jsp"/>
<script src="${pageContext.request.contextPath}/resources/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/wow/wow.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath}/resources/site/hms/js/hms.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $('body').scrollspy({
            target: '.navbar-fixed-top',
            offset: 80
        });
        // Page scrolling feature
        $('a.page-scroll').bind('click', function (event) {
            var link = $(this);
            $('html, body').stop().animate({
                scrollTop: $(link.attr('href')).offset().top - 50
            }, 500);
            event.preventDefault();
            $("#navbar").collapse('hide');
        });
    });

    var cbpAnimatedHeader = (function () {
        var docElem = document.documentElement,
                header = document.querySelector('.navbar-default'),
                didScroll = false,
                changeHeaderOn = 200;

        function init() {
            window.addEventListener('scroll', function (event) {
                if (!didScroll) {
                    didScroll = true;
                    setTimeout(scrollPage, 250);
                }
            }, false);
        }

        function scrollPage() {
            var sy = scrollY();
            if (sy >= changeHeaderOn) {
                $(header).addClass('navbar-scroll');
            } else {
                $(header).removeClass('navbar-scroll');
            }
            didScroll = false;
        }

        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }

        init();
    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init();
</script>
</body>
</html>
