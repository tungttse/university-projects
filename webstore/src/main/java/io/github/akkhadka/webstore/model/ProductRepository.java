package io.github.akkhadka.webstore.model;

import java.util.List;

public interface ProductRepository extends Repository<Product,Integer> {
    int getLastId();
    void removeById(Integer id);
}
