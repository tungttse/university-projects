package io.github.akkhadka.webstore.controller;


import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
//        for (Cookie cookie : req.getCookies()) {
//            if (cookie.getName().equals("remember")) {
//                req.setAttribute("remember", cookie.getValue());
//            }
//        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(PathVariable.loginPage);
        if(session.getAttribute("error")!=null) {
            req.setAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userName = req.getParameter("username");
        var password = req.getParameter("password");

        var user = userService.findbyUserName(userName);
        if(user!=null && user.getPassword().equals(password)){
            req.getSession().setAttribute("username",userName);
            if(req.getParameter("remember")!=null){
                Cookie remember = new Cookie("username",userName);
                remember.setMaxAge(3600 * 24 *30);
                resp.addCookie(remember);
            }
            else {
                Cookie c = new Cookie("username", null);
                c.setMaxAge(0);
                resp.addCookie(c);

            }
            String redirectUrl = "/";
            var returnUrl = req.getSession(false).getAttribute("returnurl");
            if(returnUrl!=null){
                redirectUrl =(String) returnUrl;
                req.getSession(false).removeAttribute("returnurl");
            }
            resp.sendRedirect(redirectUrl);
        }else{
            req.getSession().setAttribute("error","Invalid Username or Password");
            resp.sendRedirect("/login");
        }

    }

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
}
