<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:if test="${flashSuccess!=null || flashMessage!=null || flashWarning!=null || flashError!=null}">

    <style type="text/css">
        .flash-message {
            margin-bottom: 25px;
        }

        .flash-message .messageBody {
            margin-left: 15px;
            margin-top: 10px;
            margin-right: 15px;
            margin-bottom: -32px;
        }
    </style>

    <div class='flashMessage'>
        <c:choose>
            <c:when test='${flashSuccess!=null}'>
                <div class='alert alert-success alert-dismissable success'>
                    <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
                    <i class='icon fa fa-check'></i>
                        ${flashSuccess}
                </div>
            </c:when>
            <c:when test='${flashMessage!=null}'>
                <div class='alert alert-info alert-dismissable message'>
                    <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
                    <i class='icon fa fa-info'></i>
                        ${flashMessage}
                </div>
            </c:when>
            <c:when test='${flashWarning!=null}'>
                <div class='alert alert-warning alert-dismissable warning'>
                    <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
                    <i class='icon fa fa-remove'></i>
                        ${flashWarning}
                </div>
            </c:when>
            <c:when test='${flashError!=null}'>
                <div class='alert alert-danger alert-dismissable error'>
                    <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
                    <i class='icon fa fa-remove'></i>
                        ${flashError}
                </div>
            </c:when>
        </c:choose>
    </div>
    <script type="text/javascript">
        setTimeout(function () {
            $('.success').fadeOut();
        }, 15000);
        setTimeout(function () {
            $('.message').fadeOut();
        }, 60000);
        setTimeout(function () {
            $('.warning').fadeOut();
        }, 30000);
        setTimeout(function () {
            $('.error').fadeOut();
        }, 120000);
    </script>
</c:if>