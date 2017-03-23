<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>

<!-- Sidebar user panel -->
<div class='user-panel'>

    <sec:authorize access='isAuthenticated()'>
        <sec:authentication var='displayName' property='principal.displayName'/>
        <sec:authentication var='pic' property='principal.pic'/>
        <sec:authentication var='gender' property='principal.gender'/>
    </sec:authorize>

    <div class='pull-left image'>
        <!--<img src='${context}/resources/images/user/saif_khan.jpg' class='img-circle' alt='User Image'>-->


        <!--todo image need to set with gender -->

        <c:choose>
            <c:when test='${pic == null}'>

                <c:choose>
                    <c:when test="${gender =='MALE'}">
                        <img src='${context}/resources/images/no_pic_male.png' class='img-circle' alt='NO IMAGE MALE'>
                    </c:when>
                    <c:when test="${gender =='FEMALE'}">
                        <img src='${context}/resources/images/no_pic_female.png' class='img-circle'
                             alt='NO IMAGE FEMALE'>
                    </c:when>
                    <c:otherwise>
                        <img src='${context}/resources/images/no_pic_other.png' class='img-circle' alt='NO IMAGE OTHER'>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <img src='${context}/authUser/getPhotoMedium/${pic}' class='img-circle' alt='NO IMAGE'>
            </c:otherwise>
        </c:choose>
    </div>

    <div class='pull-left info'>
        <p>${displayName}</p>
        <a href='#'><i class='fa fa-circle text-success'></i> Online</a>
    </div>
</div>