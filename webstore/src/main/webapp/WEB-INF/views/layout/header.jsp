<div class="topnav">
    <div class="navbar-company">WEB STORE</div>
    <div class="cart">
        <a href="/cart" id="top_cart_info"> <span id="cart_info_text">Your Cart:${cart.totalItems} items $${cart.totalPrice}</span> <span
                class="glyphicon glyphicon-shopping-cart"></span></a>
        <c:if test="${username!=null}">
            <div class="dropdown">
                <div class="dropbtn"><span class="glyphicon glyphicon-user"> </span><span class="username"><c:out
                        value="${username}"/></span> <span class="glyphicon glyphicon-triangle-bottom"></span></div>
                <div class="dropdown-content">
                    <a href="/user/profile">
                        <span class="glyphicon glyphicon-user"></span>
                        User Profile</a>
                    <a href="/logout">
                        <span class="glyphicon glyphicon-log-out"></span>
                        <span>Logout</span></a>
                </div>
            </div>
        </c:if>
    </div>
</div>