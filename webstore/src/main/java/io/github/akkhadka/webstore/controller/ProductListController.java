package io.github.akkhadka.webstore.controller;

import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.controller.viewmodels.ProductListViewModel;
import io.github.akkhadka.webstore.service.ProductService;
import io.github.akkhadka.webstore.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListController extends HttpServlet {
    private ProductService productService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       var products = productService.getProducts();
       var productListVM = new ProductListViewModel(products);
       req.setAttribute("productList",productListVM);
        req.getRequestDispatcher(PathVariable.productListPage)
                .forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
}
