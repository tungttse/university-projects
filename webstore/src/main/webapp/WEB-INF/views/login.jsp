<%--
  Created by IntelliJ IDEA.
  User: arjun
  Date: 1/27/2021
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>
    <script src="/resources/js/login.js"></script>

</head>
<body>
<%--<%--%>
<%--    String error="";--%>
<%--    if(request.getSession().getAttribute("error")!=null) error=(String) request.getSession().getAttribute("error");--%>
<%--    String remember =request.getAttribute("remember")!=null?(String) request.getAttribute("remember"):null;--%>
<%--%>--%>
<div class="mainContainer">

    <div class="loginContainer">
        <form method="post" id="frmLogin">
            <div class="row error text-center">

            ${error}

            </div>
            <div class="row">
                <div class="col-12">
                    <label for="username">User Name:</label>
                        <input title="Username is required." id="username" value="${cookie.username.value}" name="username" type="text"  >
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="password">Password: </label>
                        <input id="password" name="password" type="password">

                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label >
                        <input id="remember" name="remember" <c:if test="${cookie.containsKey('username')}">checked</c:if> type="checkbox">
                        remember me
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-right">
                    <input class="btn btn-primary" type="submit" value="Login">
                </div>
            </div>
            <div class="row loginSigup">
                <div class="col-12 text-center" >
                    <a href="/user/signup" >Sign up</a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
