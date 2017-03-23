<%@ page language='java' contentType='text/html;charset=UTF-8' pageEncoding='UTF-8' %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<html>
<head>
    <title>Reflection - Access Denied</title>
</head>
<body>
<div class="generic-container">
    <div class="authbar">
                <span>
                    <spring:message code="default.message.notAuthorised.label"
                                    text="Sorry, You are not authorized to access that page...!"/>
                </span> 
                <span class="floatRight">
                    <a href="<c:url value="/logout"/>"><spring:message code="default.message.logout.label"
                                                                       text="Logout"/></a>
                </span>
    </div>
</div>
</body>
</html>