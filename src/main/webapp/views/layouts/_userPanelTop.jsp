<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access='isAuthenticated()'>
    <sec:authentication var='displayName' property='principal.displayName'/>
    <sec:authentication var='fullName' property='principal.fullName'/>
    <sec:authentication var='dob' property='principal.dob'/>
    <sec:authentication var='pic' property='principal.pic'/>
    <sec:authentication var='email' property='principal.email'/>
    <sec:authentication var='gender' property='principal.gender'/>
</sec:authorize>

<!-- User Account: style can be found in dropdown.less -->
<li class="dropdown user user-menu">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

        <!--todo image need to set with gender -->

        <c:choose>
            <c:when test='${pic == null}'>

                <c:choose>
                    <c:when test="${gender =='MALE'}">
                        <img src='${context}/resources/images/no_pic_male.png' class="user-image" alt='NO IMAGE MALE'/>
                    </c:when>
                    <c:when test="${gender =='FEMALE'}">
                        <img src='${context}/resources/images/no_pic_female.png' class="user-image"
                             alt='NO IMAGE FEMALE'/>
                    </c:when>
                    <c:otherwise>
                        <img src='${context}/resources/images/no_pic_other.png' class="user-image" alt='NO IMAGE OTHER'/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <img src='${context}/authUser/getPhotoMedium/${pic}' class='user-image' alt='NO IMAGE'/>
            </c:otherwise>
        </c:choose>
        <span class="hidden-xs">${displayName}</span>
    </a>
    <ul class="dropdown-menu">
        <!-- User image -->
        <li class="user-header">
            <c:choose>
                <c:when test='${pic == null}'>
                    <c:choose>
                        <c:when test="${gender =='MALE'}">
                            <img src='${context}/resources/images/no_pic_male.png' class="user-image"
                                 alt='NO IMAGE MALE'/>
                        </c:when>
                        <c:when test="${gender =='FEMALE'}">
                            <img src='${context}/resources/images/no_pic_female.png' class="user-image"
                                 alt='NO IMAGE FEMALE'/>
                        </c:when>
                        <c:otherwise>
                            <img src='${context}/resources/images/no_pic_other.png' class="user-image"
                                 alt='NO IMAGE OTHER'/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <img src='${context}/authUser/getPhotoMedium/${pic}' class='user-image' alt='NO IMAGE'/>
                </c:otherwise>
            </c:choose>

            <p>
                <span class='hidden-xs'>${fullName}</span>
                <span class='hidden-xs'>${email}</span>

                <small>Birthday ${dob}</small>
                <small>${pageContext.response.locale}</small>
            </p>
        </li>
        <!-- Menu Body -->
        <li class='user-body'>
            <div class='row'>
                <div class='col-xs-4 text-center'>
                    <a href='?lang='><spring:message code='default.locale' text='Default (English)'/></a>
                </div>
                <div class='col-xs-4 text-center'>
                    <a href='?lang=native'><spring:message code='locale.native' text='Native'/></a>
                </div>
            </div>
            <!-- /.row -->
        </li>
        <!-- Menu Footer-->
        <li class="user-footer">
            <div class="pull-left">
                <a href='${context}/profile/show' class='btn btn-primary'>
                    <i class="fa fa-empire"></i>
                    <spring:message code='profile' text='Profile'/>
                </a>
                <!--                
                                <a href='$ {context}/changePassword' class='btn btn-block btn-success'>
                                    <i class="fa fa-cog"></i>
                                    < spring:message code='changePassword' text='Change Password'/>
                                </a>-->
            </div>

            <div class="pull-right">
                <a href='${context}/logout' class='btn btn-danger'>
                    <i class="fa fa-power-off"></i>
                    <spring:message code='signOut' text='Sign Out'/>
                </a>
            </div>
        </li>
    </ul>
</li>
<li>
    <a href="javascript:void(0);" class="waves-effect waves-button waves-classic toggle-fullscreen">
        <i class="fa fa-expand"></i>
    </a>
</li>
<!-- Control Sidebar Toggle Button -->