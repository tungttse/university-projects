package io.github.akkhadka.webstore.controller.filters;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session =req.getSession(false);
       if(session!=null && session.getAttribute("username")!=null && (session.getAttribute("cart")!=null)){
           chain.doFilter(request,response);
       }else{
           HttpServletResponse out = (HttpServletResponse) response;
           if(session!=null){
               session.setAttribute("returnurl",req.getRequestURI());
           }
           out.sendRedirect("/login");
       }

    }
}
