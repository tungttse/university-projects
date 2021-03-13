<%--
  Created by IntelliJ IDEA.
  User: arjun
  Date: 1/29/2021
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>
    <link href="/resources/styles/loader.css" rel="stylesheet">
    <script src="/resources/js/loader.js" type="text/javascript"></script>
    <script src="/resources/js/signup.js" type="text/javascript"></script>

</head>
<body>
<%@include file="/WEB-INF/views/layout/header.jsp" %>
<div class="content">
<%@include file="/WEB-INF/views/layout/nav.jsp" %>

<section>
    <div id="loader" style="display: none;"></div>
    <div class="page-header">
        <h1 >Sign Up</h1>
    </div>



    <div class="form">
        <div  id="message"> </div>
        <form method="post" action="/user/signup" id="frmSignup">
            <div class="row">
                <div class="col-12">
                    <label for="firstName">First Name:
                        <input id="firstName" name="firstName" type="text">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="lastName">Last Name:
                        <input id="lastName" name="lastName" type="text">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="username">Username:
                        <input id="username" name="username" type="text" >
                    </label>
                    <span class="error" id="spnMsg"></span>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="password">Password:
                        <input id="password" name="password" type="password" >
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="confirmPassword">Confirm Password:
                        <input id="confirmPassword" name="confirmPassword" type="password" >
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="email">Email:
                        <input id="email" name="email" type="text" >
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-right">
                    <input type="submit" class="btn btn-primary" value="Sign up">
                </div>
            </div>

        </form>
    </div>


</section>
</div>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>
</html>
