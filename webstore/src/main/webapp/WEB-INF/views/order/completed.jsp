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
    <section>
        <div class="row">
            <div class="col-12">
                <img class="thanks_img" src="/resources/images/thanks.jpg" />
                <h2> Thanks ${shippedto} for placing your order.</h2>
                <h2> Order will be shipped to ${line1} ${country} within ${day_delivery} days</h2>
                <h3>Your order details:</h3>
            </div>
        </div>

        <table>
            <tr>
                <th>Quantity</th>
                <th>Item</th>
                <th>Price</th>
                <th>Subtotal</th>

            </tr>

            <c:forEach var="item" items="${order.lineItems}">
                <tr>
                    <td>${item.quantity}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.price}</td>
                    <td>${item.product.price * item.quantity}$</td>

                </tr>


            </c:forEach>
            <tr>
                <td colspan="3" class="text-right">Total:</td>
                <td >${order.totalPrice}$</td>
            </tr>
        </table>

    </section>
</div>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>
</html>
