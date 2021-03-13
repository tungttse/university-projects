package io.github.akkhadka.webstore.controller.listener;

import io.github.akkhadka.webstore.model.Cart;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Cartlistener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        //HttpSession session =req.getSession();
        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession();
        }
        if(session!=null && session.getAttribute("cart")==null){
            var cart = (Cart) session.getAttribute("cart");
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
    }
}
