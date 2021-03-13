<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sms.cole
  Date: 1/30/2021
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Management</title>

    <script src="/resources/js/admin-js.js" type="text/javascript"></script>
    <link href="/resources/styles/webstore.css" rel="stylesheet" type="text/css" />
    <link href="/resources/styles/admin-style.css" rel="stylesheet" type="text/css" />

</head>
<body>

    <div id="div-admin-home">

        <div id="top">
            <h1>Welcome to admin page</h1>
            <div id="user-info">
                <p>Hello ${user.firstName} ${user.lastName}</p>
                <form action="/admin">
                    <input type="submit" value="Logout" class="btn btn-primary">
                </form>
            </div>
        </div>

        <div id="mid">
            <div id="navigate">
                <!--content product and users-->
                <p><a href="#" id="product-link">Products</a></p>
                <p><a href="#" id="user-link">Users</a></p>
            </div>

            <div id="right">

            </div>

        </div>
    </div>

</body>
</html>
