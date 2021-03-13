package io.github.akkhadka.webstore.controller.admin.api;

import com.google.gson.Gson;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/users"}, name = "api-users")
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<User> users = userService.getUsers();
        ResponseJsonHelper.responseByJson(resp, users);
    }

}



