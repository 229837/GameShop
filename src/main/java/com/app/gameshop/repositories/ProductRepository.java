package com.app.gameshop.repositories;

import com.app.gameshop.model.Product;
import com.app.gameshop.repositories.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository
public class ProductRepository implements Repository<Product> {

    private List<Product> products = new ArrayList<>();

    @Override
    public Product get(int id) {
        return products.get(id);
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public boolean add(Product newObject) {
        return products.add(newObject);
    }

    @Override
    public boolean remove(Product object) {
        return products.remove(object);
    }

    @Override
    public int size() {
        return products.size();
    }
}
