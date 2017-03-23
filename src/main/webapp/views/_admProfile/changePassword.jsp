<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
<c:set var='context' value='${pageContext.request.contextPath}'/>
<head>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
    <title>Password Change</title>
</head>

<body>

<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='default.button.show.label' text='Show'/>&nbsp;<spring:message code='passwordChange'
                                                                                                text='Password Change'/></h1>

        <ul class='top-links'>
            <li>
                <a href='${context}/profile/show' class='btn btn-block btn-primary btn-xs'><i
                        class='fa fa-plus-circle'></i>&nbsp;<spring:message code='profile' text='Profile'/></a>
            </li>
        </ul>
    </section>
    <!-- /.content-header -->

    <section class='content-messages'>
        <jsp:include page='/views/layouts/_flashMessage.jsp'/>
    </section>
    <!-- /.flesh-message -->

    <section class='content'><!-- Main content -->
        <div class='box box-primary'>
            <div class='box-body'>

                <form:form action="${pageContext.request.contextPath}/profile/changePassword" commandName="_GenDTO"
                           method="POST">
                    <form:errors path="*" cssClass="errorblock" element="div"/>
                    <table>
                        <tr>
                            <td>Current Password :</td>
                            <td><form:password path="data1"/>
                            </td>
                            <td><form:errors path="data1" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>New Password :</td>
                            <td><form:password path="data2"/>
                            </td>
                            <td><form:errors path="data2" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Confirm Password :</td>
                            <td><form:password path="data3"/>
                            </td>
                            <td><form:errors path="data3" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <button type='submit' class='btn btn-primary'>
                                    <i class='fa fa-save'></i>&nbsp;<spring:message code='changePassword'
                                                                                    text='Change Password'/>
                                </button>

                            </td>
                        </tr>
                    </table>
                </form:form>
                <div class='box-footer'>
                    <!--<a href='${context}/profile/edit' class='btn btn-primary'><i class='fa fa-edit'></i>&nbsp;<spring:message code='default.button.edit.label'/></a>-->
                </div>
                <!--.box-footer-->
            </div>
            <!--.box .box-primary-->
    </section>
    <!--.content-->
</div>
<!-- /.content-wrapper -->
</body>
</html>