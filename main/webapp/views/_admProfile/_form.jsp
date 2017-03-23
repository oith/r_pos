<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

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

<form:errors path='*' cssClass='errorblock' element='div'/>
<form:hidden path='id'/>
<div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='username'><spring:message code='username' text='Username'/></form:label>
            <form:input class='form-control' path='username' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='username' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='password'><spring:message code='password' text='Password'/></form:label>
            <form:password class='form-control' path='password' showPassword='true' required='true' size='30'
                           maxlength='128'/>
            <form:errors path='password' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='displayName'><spring:message code='displayName'
                                                                            text='Display Name'/></form:label>
            <form:input class='form-control' path='displayName' type='text' required='true' size='30' maxlength='10'/>
            <form:errors path='displayName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='50'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='gender'><spring:message code='gender' text='Gender'/></form:label>
            <form:select class='form-control' path='gender' name='gender' id='gender' required='true'>
                <form:option value='MALE' label='Male'/>
                <form:option value='FEMALE' label='Female'/>
                <form:option value='OTHER' label='Other'/>
            </form:select>
            <form:errors path='gender' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='dob'><spring:message code='dob' text='Dob'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='dob' required='true'/>
            </div>
            <form:errors path='dob' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='doj'><spring:message code='doj' text='Doj'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='doj'/>
            </div>
            <form:errors path='doj' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='email' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='pic'><spring:message code='pic' text='Pic'/></form:label>
            <c:url var='pic' value='/authUser/getPhotoMedium/${authUser.pic}'/>
            <img alt='${authUser.pic}' src='${pic}'/>
            <form:hidden path='pic'/>
            <input id='picOBJ' name='picOBJ' type='file' accept='image/*'/>
            <form:errors path='pic' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='language'><spring:message code='language' text='Language'/></form:label>
            <form:select class='form-control' path='language' name='language' id='language' required='true'>
                <form:option value='bn' label='bn'/>
                <form:option value='hi' label='hi'/>
                <form:option value='en' label='en'/>
            </form:select>
            <form:errors path='language' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='locale'><spring:message code='locale' text='Locale'/></form:label>
            <form:select class='form-control' path='locale' name='locale' id='locale' required='true'>
                <form:option value='bn_BD' label='Bangladeshi Bangla'/>
                <form:option value='bn_IN' label='Indian Bangla'/>
                <form:option value='en' label='English Global'/>
                <form:option value='es' label='Spanish'/>
                <form:option value='fr' label='Frnch'/>
                <form:option value='hi_IN' label='Indian Hindi'/>
                <form:option value='it' label='Italyian'/>
            </form:select>
            <form:errors path='locale' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='country'><spring:message code='country' text='Country'/></form:label>
            <form:select class='form-control' path='country' name='country' id='country' required='true'>
                <form:option value='BD' label='BD'/>
                <form:option value='IT' label='IT'/>
                <form:option value='IN' label='IN'/>
            </form:select>
            <form:errors path='country' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='currency'><spring:message code='currency' text='Currency'/></form:label>
            <form:select class='form-control' path='currency' name='currency' id='currency' required='true'>
                <form:option value='BDT' label='BDT'/>
                <form:option value='INR' label='INR'/>
                <form:option value='EURO' label='EURO'/>
                <form:option value='USD' label='USD'/>
            </form:select>
            <form:errors path='currency' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='menuOrientation'><spring:message code='menuOrientation'
                                                                                text='Menu Orientation'/></form:label>
            <form:select class='form-control' path='menuOrientation' name='menuOrientation' id='menuOrientation'
                         required='true'>
                <form:option value='MENU_NONE' label='MENU NONE'/>
                <form:option value='MENU_LEFT' label='MENU LEFT'/>
                <form:option value='MENU_TOP' label='MENU TOP'/>
            </form:select>
            <form:errors path='menuOrientation' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='authGroups'><spring:message code='authGroups' text='Auth Groups'/></form:label>
            <form:select class='form-control' path='authGroups' multiple='true'>
                <form:options items='${authGroups}' itemValue='id'/>
            </form:select>
            <form:errors path='authGroups' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='enabled'><spring:message code='enabled' text='Enabled'/></form:label>
            <br><form:checkbox class='cb' path='enabled'/>
            <form:errors path='enabled' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='accountNonExpired'><spring:message code='accountNonExpired'
                                                                 text='Account Non Expired'/></form:label>
            <br><form:checkbox class='cb' path='accountNonExpired'/>
            <form:errors path='accountNonExpired' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='accountNonLocked'><spring:message code='accountNonLocked'
                                                                text='Account Non Locked'/></form:label>
            <br><form:checkbox class='cb' path='accountNonLocked'/>
            <form:errors path='accountNonLocked' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='credentialsNonExpired'><spring:message code='credentialsNonExpired'
                                                                     text='Credentials Non Expired'/></form:label>
            <br><form:checkbox class='cb' path='credentialsNonExpired'/>
            <form:errors path='credentialsNonExpired' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='openInNewPage'><spring:message code='openInNewPage' text='Open In New Page'/></form:label>
            <br><form:checkbox class='cb' path='openInNewPage'/>
            <form:errors path='openInNewPage' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='params'><spring:message code='params' text='Params'/></form:label>
            <form:textarea class='form-control' path='params' type='text' size='30' maxlength='2000'/>
            <form:errors path='params' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='favorite'><spring:message code='favorite' text='Favorite'/></form:label>
            <form:textarea class='form-control' path='favorite' type='text' size='30' maxlength='2000'/>
            <form:errors path='favorite' cssClass='error' element='div'/>
        </div>
    </div>

</div>

<script type='text/javascript'>

</script>