<%--
  Created by IntelliJ IDEA.
  User: arjun
  Date: 1/28/2021
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Product List</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/views/layout/header.jsp" %>
<div class="content">
    <!--nav-->
    <%@include file="/WEB-INF/views/layout/nav.jsp" %>

    <section id="checkout_container">
        <div class="container">

                <div class="page-header">
                    <h1 >Check out now</h1>
                </div>


            <form method="post" action="/checkout">
                <h3>Ship to</h3>
                <div class="row">
                    <div class="col-3">
                        <label>
                            Name:
                            <input type="text" name="name" required>
                        </label>
                    </div>
                </div>

                <h3>Shipping address</h3>
                <div class="row">
                    <div class="col-3">
                        <label>
                            Line 1:
                            <input type="text" name="line1" value="1000 4th Str, Fairfield" required>
                        </label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-3">
                        <label>
                            Country:
                            <input type="text" name="country" value="USA" required>
                        </label>
                    </div>
                </div>

                <div class="row">
                    <h3>Payment Method</h3>
                    <p>Cash On Delivery</p>
                </div>

                <div class="row">
                    <h3>Delivery Time</h3>
                    <input type="number" value="3" min="3" max="30" name="day_delivery"> Days
                </div>

                <div class="row">
                    <h3>Review Item</h3>

                    <table class="col-6">
                        <tr>
                            <th>Quantity</th>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Subtotal</th>
                        </tr>

                        <c:forEach var="item" items="${cart.lineItems}">
                            <tr>
                                <td>${item.quantity}</td>
                                <td>${item.product.name}</td>
                                <td>${item.product.price}</td>
                                <td>${item.product.price * item.quantity}$</td>
                                    <%--                            <td><input class="remove" type="submit" value="Remove"></td>--%>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" class="text-right">Total:</td>
                            <td >${cart.totalPrice}$</td>
                        </tr>
                    </table>
                </div>
                <div class="row">
                    <input class="normal" type="submit" value="Complete Order">
                </div>

            </form>

        </div>

    </section>


</div>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>
</html>
