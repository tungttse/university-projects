package io.github.akkhadka.webstore.repository;

import io.github.akkhadka.webstore.model.Product;
import io.github.akkhadka.webstore.model.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FakeProductRepository implements ProductRepository{

    private static List<Product> products;

    public FakeProductRepository() {
        if (products == null) {
            synchronized (FakeProductRepository.class) {
                products = new ArrayList<Product>();
                products.add(new Product(1,"FootBall","Primium football and high quality",25));
                products.add(new Product(2,"Human Chess Board","A fun game for the family",10));
                products.add(new Product(3,"Kayak","A boat for one person",30));
            }
        }
    }

    @Override
    public void update(Product entity) {
        for (int i = 0; i < this.products.size(); ++i){
            Product p = this.products.get(i);
            if (p.getProductId() == entity.getProductId()){
                products.set(i, entity);
                break;
            }
        }
    }

    @Override
    public void save(Product entity) {
        if (entity.getProductId() <= 0){
            if (products.size() > 0) {
                entity.setProductId(products.get(products.size() - 1).getProductId() + 1);
            }
            else {
                entity.setProductId(1);
            }
        }
        products.add(entity);
    }

    @Override
    public void remove(Product entity) {
        for (int i = 0; i < this.products.size(); ++i){
            Product p = this.products.get(i);
            if (p.getProductId() == entity.getProductId()){
                products.remove(i);
                break;
            }
        }
    }

    @Override
    public Product find(Integer id) {
        return products.stream().filter(x->x.getProductId()==id).findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }


    @Override
    public int getLastId() {
        return 0;
    }

    @Override
    public void removeById(Integer id) {
        for (int i = 0; i < this.products.size(); ++i){
            Product p = this.products.get(i);
            if (p.getProductId() == id){
                products.remove(i);
                break;
            }
        }
    }
}
