package io.github.akkhadka.webstore.controller;

import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.model.Cart;
import io.github.akkhadka.webstore.model.OrderItem;
import io.github.akkhadka.webstore.service.ProductService;
import io.github.akkhadka.webstore.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class OrderController extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();

        for (int i = 0; i < cookies.length; ++i) {
            System.out.println(cookies[i].getName());
            if (cookies[i].getName().equals("cart_data")) {
                String cartData = cookies[i].getValue();
                if(cartData.equals("") || cartData == null) continue;
                String[] pairProduct = cartData.split("_");

                for (int item = 0; item < pairProduct.length; item++) {
                    String[] idAndQuantity = pairProduct[item].split("-");
                    if (idAndQuantity.length > 0) {
                        var productId = Integer.parseInt(idAndQuantity[0]);
                        var productQuantity = Integer.parseInt(idAndQuantity[1]);
                        var product = productService
                                .getProducts()
                                .stream()
                                .filter(x -> x.getProductId() == productId)
                                .findFirst()
                                .orElse(null);

                        if (product != null) {
                            var cart = (Cart) session.getAttribute("cart");
                            if (cart == null) {
                                cart = new Cart();
                            }
                            cart.setItem(product, productQuantity);
                        }
                    }
                }
            }
        }

        req.getRequestDispatcher(PathVariable.checkoutPage)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String shippedto = req.getParameter("name");
        String line1 = req.getParameter("line1");
        String country = req.getParameter("country");
        String dayDelivery = req.getParameter("day_delivery");

        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();

        var cart = (Cart) session.getAttribute("cart");
        var useId = (String) session.getAttribute("username");

        for (int i = 0; i < cookies.length; ++i) {
            System.out.println(cookies[i].getName());
            if (cookies[i].getName().equals("cart_data")) {
                String cartData = cookies[i].getValue();
                if(cartData.equals("") || cartData == null) continue;
                String[] pairProduct = cartData.split("_");

                for (int item = 0; item < pairProduct.length; item++) {
                    String[] idAndQuantity = pairProduct[item].split("-");
                    if (idAndQuantity.length > 0) {
                        var productId = Integer.parseInt(idAndQuantity[0]);
                        var productQuantity = Integer.parseInt(idAndQuantity[1]);
                        var product = productService
                                .getProducts()
                                .stream()
                                .filter(x -> x.getProductId() == productId)
                                .findFirst()
                                .orElse(null);

                        if (product != null) {
                            if (cart == null) {
                                cart = new Cart();
                            }
                            cart.setItem(product, productQuantity);
                        }
                    }
                }
            }
        }

        OrderItem newOrderItem = new OrderItem(
                name,
                cart,
                shippedto,
                line1,
                country,
                dayDelivery,
                useId
        );
        //delete cookie here.
        Cookie cookieCartData = new Cookie("cart_data", "");
        resp.addCookie(cookieCartData);

        Cookie cookieCart = new Cookie("cart", "");
        resp.addCookie(cookieCart);

        session.setAttribute("order", newOrderItem);
        resp.sendRedirect("/orderplaced");
    }

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
}
