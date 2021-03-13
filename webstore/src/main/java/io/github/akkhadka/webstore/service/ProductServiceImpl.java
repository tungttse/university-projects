package io.github.akkhadka.webstore.service;

import io.github.akkhadka.webstore.controller.admin.exception.ApiException;
import io.github.akkhadka.webstore.model.Product;
import io.github.akkhadka.webstore.model.ProductRepository;
import io.github.akkhadka.webstore.repository.FakeProductRepository;

import java.util.Collection;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(){
        productRepository = new FakeProductRepository();
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product p) throws ApiException {
        validate(p);
        productRepository.save(p);
    }

    @Override
    public void removeProduct(int productId) {
        productRepository.removeById(productId);
    }

    @Override
    public void updateProduct(Product p) throws ApiException {
        if (!p.isValid()){
            throw new ApiException("Invalid data");
        }

        for (Product product : getProducts()){
            if (p.getProductId() != product.getProductId() && product.equals(p)) throw new ApiException("Duplicate data");
        }

        productRepository.update(p);
    }

    private void validate(Product p) throws ApiException{
        if (!p.isValid()){
            throw new ApiException("Invalid data");
        }

        if (isDuplicated(p)){
            throw new ApiException("Duplicate data");
        }
    }

    private boolean isDuplicated(Product p){
        for (Product product : getProducts()){
            if (product.equals(p)) return true;
        }

        return false;
    }
}
