package io.github.akkhadka.webstore.controller.admin;
import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.model.Role;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin")
public class LoginController extends HttpServlet {

    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null){
            session.invalidate();
        }

        String error = req.getParameter("error");
        req.setAttribute("error", error);

        var dispatcher = req.getRequestDispatcher(PathVariable.loginPage);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("username");
        String pwd = req.getParameter("password");

        User superUser = userService.findbyUserName(uid);
        StringBuilder error = new StringBuilder();
        if (authenticateSuperUser(superUser, pwd, error) == false) { //login fail
            resp.sendRedirect("/admin?error=" + error.toString());
            return;
        }

        if(req.getParameter("remember") != null){
            Cookie remember = new Cookie("username", uid);
            remember.setMaxAge(3600 * 24 *30);
            resp.addCookie(remember);
        }
        else {
            Cookie remember = new Cookie("username", uid);
            remember.setMaxAge(0);
            resp.addCookie(remember);
        }

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60*60);
        session.setAttribute("su", superUser);
        resp.sendRedirect("/admin/management");
    }

    private boolean authenticateSuperUser(User user, String pwd, StringBuilder error){
        //TODO need to check role as well
        if (user == null) { //login fail
            error.append("User is invalid");
            return false;
        }

        if (!user.getPassword().equals(pwd)){ //wrong pass
            error.append("User is invalid");
            return false;
        }

        if (user.getRole() != Role.ADMIN){ //access denied
            error.append("Access denied");
            return false;
        }

        return true;
    }
}
