package io.github.akkhadka.webstore.service;

import io.github.akkhadka.webstore.controller.admin.exception.ApiException;
import io.github.akkhadka.webstore.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    void addProduct(Product p) throws ApiException;
    void removeProduct(int productId) ;
    void updateProduct(Product p) throws ApiException;
}
