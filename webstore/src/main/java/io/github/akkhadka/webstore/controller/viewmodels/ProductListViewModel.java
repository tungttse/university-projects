package io.github.akkhadka.webstore.controller.viewmodels;

import io.github.akkhadka.webstore.model.Product;

import java.util.List;

public class ProductListViewModel
{
//    public Product[] getProducts() {
//        return products.toArray(new Product[0]);
//    }
public List<Product> getProducts() {
    return products;
}
    public ProductListViewModel(List<Product> products) {
        this.products = products;
    }

    private List<Product> products;

}
