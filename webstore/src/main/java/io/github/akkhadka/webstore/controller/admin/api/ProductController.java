package io.github.akkhadka.webstore.controller.admin.api;

import com.google.gson.Gson;
import io.github.akkhadka.webstore.controller.admin.exception.ApiException;
import io.github.akkhadka.webstore.model.ErrorApi;
import io.github.akkhadka.webstore.model.Product;
import io.github.akkhadka.webstore.service.ProductService;
import io.github.akkhadka.webstore.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet( urlPatterns = {"/products"}, name = "api-products")
public class ProductController extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getProducts();
        ResponseJsonHelper.responseByJson(resp, products);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = convertPayloadToProduct(req);
        System.out.println("Add product : " + product);

        try {
            productService.addProduct(product);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            ResponseJsonHelper.responseByJson(resp, product);

        } catch (ApiException e) {
            responseError(resp, "Cannot add new Product", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = convertPayloadToProduct(req);
        System.out.println("Update product : " + product);

        try {
            productService.updateProduct(product);
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);

        } catch (ApiException e) {
            responseError(resp, "Cannot update Product", e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("Delete product with Id: " + id);

        productService.removeProduct(id);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    private void responseError(HttpServletResponse resp, String errorMessage, ApiException e) throws IOException {
        ErrorApi err = new ErrorApi();
        err.setError(errorMessage);
        err.setDetail(e.getMessage());
        err.setCode(HttpServletResponse.SC_BAD_REQUEST);

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        ResponseJsonHelper.responseByJson(resp, err);
    }


    private Product convertPayloadToProduct(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader reader = req.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        try {
            Gson gson = new Gson();
            Product product = gson.fromJson(sb.toString(), Product.class);
            return product;
        }
        catch (Exception e){
            //dummy product
            return new Product();
        }
    }

}
