package io.github.akkhadka.webstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.model.Cart;
import io.github.akkhadka.webstore.model.CartService;
import io.github.akkhadka.webstore.service.ProductService;
import io.github.akkhadka.webstore.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CartController extends HttpServlet {
    private ProductService productService;
    private CartService cartService;

    private HttpServletRequest _mapDataCartFromCookie(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; ++i) {
            System.out.println(cookies[i].getName());
            if (cookies[i].getName().equals("cart_data")) {
                String cartData = cookies[i].getValue();
                if(cartData.equals("") || cartData == null) continue;

                String[] pairProduct = cartData.split("_");

                for (int item = 0; item < pairProduct.length; item++) {
                    String[] idAndQuantity = pairProduct[item].split("-");
                    if (idAndQuantity.length > 0) {
                        var productId = Integer.parseInt(idAndQuantity[0]);
                        var productQuantity = Integer.parseInt(idAndQuantity[1]);
                        var product = productService
                                .getProducts()
                                .stream()
                                .filter(x -> x.getProductId() == productId)
                                .findFirst()
                                .orElse(null);

                        if (product != null) {
                            var cart = (Cart) session.getAttribute("cart");
                            if (cart == null) {
                                cart = new Cart();
                            }
                            cart.setItem(product, productQuantity);
                        }
                    }
                }
            }
        }
        return req;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = this._mapDataCartFromCookie(req);

        if(req.getHeader("X-Custom-Header") != null && req.getHeader("X-Custom-Header").equals("isAjax")){
            var cart = (Cart) req.getSession(false).getAttribute("cart");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JsonObject obj = new JsonObject();
            obj.addProperty("totalItem", cart.getTotalItems());
            obj.addProperty("totalPrice", cart.getTotalPrice());
            out.print(obj.toString());
        } else { // Not ajax
            req.getRequestDispatcher(PathVariable.cartPage)
                    .forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String action = data.get("action").getAsString();

        String pid = data.get("productId").getAsString();
        var productId = Integer.parseInt(pid);

        String result ="0"; //fail

        var product  = productService.getProducts().stream().filter(x->x.getProductId()==productId).findFirst().orElse(null);
        if(product!=null){
            var cart = (Cart) req.getSession(false).getAttribute("cart");
            if(cart==null){
                cart = new Cart();
            }
            cart.addItem(product,1);
            result = "1"; // successful.
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        JsonObject obj = new JsonObject();
        obj.addProperty("result", result);
        out.print(obj.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("go to doDelete");
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String action = data.get("action").getAsString();

        String pid = data.get("productId").getAsString();
        var productId = Integer.parseInt(pid);

        String result ="0"; //fail

        if(action.equals("delete_to_cart")) {
            var product  = productService.getProducts().stream().filter(x->x.getProductId()==productId).findFirst().orElse(null);
            if(product!=null){
                var cart = (Cart) req.getSession(false).getAttribute("cart");
                cart.removeLine(product);
                result = "1";
            }
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        JsonObject obj = new JsonObject();
        obj.addProperty("result", result);
        out.print(obj.toString());
    }

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
}
