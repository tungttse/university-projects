package io.github.akkhadka.webstore.controller.admin.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authenticate-filter",
    servletNames = {"admin-manage-products", "api-products", "api-users"}
)
public class AuthenticationAdminFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("su") == null){
            //System.out.println("Session is invalid");
            response.sendRedirect("/admin");
        }
        else {
            //System.out.println("Session is valid");
            chain.doFilter(request, response);
        }
    }

}
