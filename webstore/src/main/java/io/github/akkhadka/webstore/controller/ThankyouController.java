package io.github.akkhadka.webstore.controller;

import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.model.Cart;
import io.github.akkhadka.webstore.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThankyouController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderItem orderItem = (OrderItem) req.getSession(false).getAttribute("order");
        Cart cart = orderItem.getCart();
        req.setAttribute("order",cart);
        req.setAttribute("shippedto",orderItem.getShipTo());
        req.setAttribute("line1",orderItem.getAddLine());
        req.setAttribute("country",orderItem.getCountry());
        req.setAttribute("day_delivery",orderItem.getDayDelivery());
        req.getSession(false).setAttribute("cart", null);

        req.getRequestDispatcher(PathVariable.orderPlacedPage)
                .forward(req,resp);
    }
}
