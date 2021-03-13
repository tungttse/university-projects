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
    <title>Webstore- Profile</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>
    <link href="/resources/styles/loader.css" rel="stylesheet">
    <script src="/resources/js/loader.js" type="text/javascript"></script>
    <script src="/resources/js/profile.js" type="text/javascript"></script>
</head>
<body>
<%@include file="/WEB-INF/views/layout/header.jsp" %>
<%@include file="/WEB-INF/views/layout/nav.jsp" %>


<section>
    <div id="loader" style="display: none;"></div>

    <div class="page-header">
        <h1 ><c:out value="${user.firstName} ${user.lastName}(${user.username})"/></h1>
    </div>


    <div class="form disabled">
        <div  id="message">        </div>
        <form method="post"  id="frmSignup">
            <input type="hidden" name="username" value="<c:out value="${user.username}"/>" id="username"  >
            <div class="row">
                <div class="col-12">
                    <label for="firstName">First Name:
                        <input id="firstName" value="<c:out value="${user.firstName}"/>" name="firstName" type="text">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="lastName">Last Name:
                        <input id="lastName" name="lastName" type="text" value="<c:out value="${user.lastName}"/>">
                    </label>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <label for="password">Password:
                        <input id="password" name="password" type="password" value="<c:out value="${user.password}"/>">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="confirmPassword">Confirm Password:
                        <input id="confirmPassword" name="confirmPassword" type="password" value="<c:out value="${user.confirmPassword}"/>">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <label for="email">Email:
                        <input id="email" name="email" type="text" value="<c:out value="${user.email}"/>">
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-right">
                    <input  class="btn btn-primary" type="button" value="Edit" id="btnSubmit">
                    <input  class="btn btn-primary" type="button" value="Cancel" id="btnCancel">
                </div>
            </div>

        </form>
    </div>


</section>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>
</html>
