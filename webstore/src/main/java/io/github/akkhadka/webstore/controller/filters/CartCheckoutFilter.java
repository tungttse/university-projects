package io.github.akkhadka.webstore.controller.filters;

 import io.github.akkhadka.webstore.model.Cart;

 import javax.servlet.*;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import java.io.IOException;

public class CartCheckoutFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();

        Boolean cannotGo = false;
        Boolean hasCartData = false;

        for (int i = 0; i < cookies.length; ++i) {
            if (cookies[i].getName().equals("cart_data")) {
                if(cookies[i].getValue().equals("") || cookies[i].getValue() == null) {
                    cannotGo = true;
                } else {
                    hasCartData = true;
                }
            }
        }

        if(!hasCartData) {
            cannotGo = true;
        }

        if (!cannotGo) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse out = (HttpServletResponse) response;
            out.sendRedirect("/");
        }
    }
}