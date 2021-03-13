package io.github.akkhadka.webstore.controller.admin;

import io.github.akkhadka.webstore.model.Product;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.ProductService;
import io.github.akkhadka.webstore.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/management"}, name = "admin-manage-products")
public class ManagementController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        req.setAttribute("user", (User)httpSession.getAttribute("su"));

        var dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp");
        dispatcher.forward(req, resp);
    }
}
