<%--
  Created by IntelliJ IDEA.
  User: sms.cole
  Date: 1/30/2021
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login Form</title>
</head>
<body>

    <div id="div-login">
        <form method="post">
            <p>
                <label>Username
                    <input name="uid">
                </label>
            </p>
            <p>
                <label>Password
                    <input name="pwd" type="password">
                </label>
            </p>
            <p>
                <label>Remember Me
                    <input name="remember" type="checkbox">
                </label>
            </p>
            <p>
                <button>Login</button>
            </p>
        </form>
    </div>

</body>
</html>
