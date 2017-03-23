<%-- 
    Document   : _carousel
    Created on : Jun 3, 2016, 10:57:00 PM
    Author     : hoshen.mahmud
--%>

<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
        <li data-target="#inSlider" data-slide-to="1"></li>
        <li data-target="#inSlider" data-slide-to="2"></li>
        <li data-target="#inSlider" data-slide-to="3"></li>
    </ol>
    <div class="carousel-inner" role="listbox">


        <div class="item active">
            <div class="container">
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
                    <!--<img src="${pageContext.request.contextPath}/resources/site/img/landing/laptop.png" alt="laptop"/>-->
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back one"></div>
        </div>

        <div class="item">
            <div class="container">
                <div class="carousel-caption blank">
                    <h1>We create meaningful <br/> interfaces that inspire.</h1>

                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>

                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back two"></div>
        </div>

        <div class="item">
            <div class="container">
                <div class="carousel-caption blank">
                    <h1 class="right">We create meaningful <br/> interfaces that inspire.</h1>

                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>

                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back three"></div>
        </div>

        <div class="item">
            <div class="container">
                <div class="carousel-caption blank">
                    <h1>We create meaningful <br/> interfaces that inspire.</h1>

                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>

                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                </div>
            </div>
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