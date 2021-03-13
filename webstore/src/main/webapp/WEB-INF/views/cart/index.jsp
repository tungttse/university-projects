<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WebStore-Cart</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>

</head>
<body>
<%@include file="/WEB-INF/views/layout/header.jsp" %>
<div class="content">
    <!--nav-->
    <%@include file="/WEB-INF/views/layout/nav.jsp" %>
    <section>
        <div class="col-12">
            <h1 class="page-header">Your Shopping Cart</h1>
        </div>

        <table id="table_cartline">
            <tr>
                <th>Quantity</th>
                <th>Item</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${cart.lineItems}">
                <tr>
                    <td>${item.quantity}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.price}</td>
                    <td>${item.product.price * item.quantity}$</td>
                    <td>
                        <input
                            class="remove_item remove"
                            type="submit"
                            value="Remove"
                            data-id="${item.product.productId}"
                            data-quantity="${item.quantity}">

                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="4" class="text-right">Total:</td>
                <td id="total_price">${cart.totalPrice}$</td>
            </tr>

        </table>
    </section>
    <section>
        <div class="row">
            <div class="col-9">
            </div>

            <div class="col-2">
                <a class="btn normal" id="btn_checkout" ${cart.totalItems == 0 ? 'href="#"' : 'href="/checkout"'}  type="submit" value="Checkout" ${cart.totalItems == 0 ? "disabled" : ""}>Checkout</a>
            </div>
        </div>
    </section>
</div>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<script src="<c:url value='/resources/js/cart.js'/>"></script>
</body>
</html>
