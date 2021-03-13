<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>WebStore-Product List</title>
    <%@include file="/WEB-INF/views/layout/staticresource.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/views/layout/header.jsp" %>
<div class="content">

    <%@include file="/WEB-INF/views/layout/nav.jsp" %>
    <section>

            <div class="page-header">
                <h1 >Products</h1>
            </div>

        <div class="products">
            <c:forEach var="product" items="${productList.products}">
                <div class="product">
                    <div class="bg-faded">
                        <input type="hidden" name="productId" value="${product.productId}"/>
                        <h1 class="name"> ${product.name}</h1>
                        <span class="price">$${product.price}</span>
                    </div>
                    <div>
                        <span class="description">${product.description}</span>
                        <input type="submit"
                               class="add_to_cart"
                               value="Add to Cart"
                               data-id="${product.productId}"
                               data-name="${product.name}"
                               data-price="${product.price}"
                               data-description="${product.description}"
                        >
                    </div>
                </div>
            </c:forEach>
        </div>

    </section>
</div>

<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<script src="<c:url value='/resources/js/list.js'/>"></script>
</body>
</html>
