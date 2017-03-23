<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' %>
<%@ page isELIgnored="false" %>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring' %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>

    <title>Reflection | Log in</title>

    <jsp:include page="_css.jsp"/>
</head>
<!--<body class="hold-transition" style="background-color: red">-->
<!--<body class="hold-transition" style="background-image: url(../../../webapp/resources/site/hms/images/l1.jpg)">-->
<body class="hold-transition" style="background: url(../../../webapp/resources/site/hms/images/l1.jpg)">
<div class="login-box">
    <div class="login-logo">
        <!--<a href="../../samplePages/home/index2.jsp"><b>Reflection</b></a>-->
        <a href="${pageContext.request.contextPath}/index"><b>Reflection</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">

        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p><spring:message code="default.message.invalidLogin.label" text="Invalid username or password."/></p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p><spring:message code="default.message.logoutSuccess.label"
                                   text="You have been logged out successfully."/></p>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" class="form-horizontal">

            <div class="input-group input-sm p-l-0 p-r-0">
                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username"
                       required>
            </div>
            <div class="input-group input-sm p-l-0 p-r-0">
                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password"
                       required>
            </div>

            <div class="row p-t-15">
                <fieldset>
                    <div class="col-xs-8">
                        <div class="checkbox icheck">
                            <label>
                                <input id="remember-me" name="remember-me" type="checkbox">
                                <spring:message code="default.message.remember-me.label" text="Remember Me"/>
                            </label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <!-- /.col -->
                    <div class="col-xs-4" style="text-align: right">
                        <button type="submit" class="btn btn-primary"><spring:message code="default.message.logIn.label"
                                                                                      text="Log In"/></button>
                    </div>
                    <!-- /.col -->
                </fieldset>
            </div>
        </form>
        <div class="text-center p-t-15">
            <a class="btn btn-sm btn-dropbox" href="#"><spring:message code="default.message.forgotPassword.label"
                                                                       text="I forgot my password"/></a>
            <a class="btn btn-sm btn-info text-center" href="#"><spring:message code="default.message.register.label"
                                                                                text="Register as a new member"/></a>
        </div>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<jsp:include page="_js.jsp"/>

<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>