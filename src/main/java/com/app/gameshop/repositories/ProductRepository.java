package com.app.gameshop.repositories;

import com.app.gameshop.model.Genre;
import com.app.gameshop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository
public class ProductRepository implements Repository<Product> {

    private List<Product> products = new ArrayList<>();

    @Override
    public Product get(int id) {
        if(id < 0 || id >= products.size())
            return null;
        return products.get(id);
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public boolean add(Product newObject) {
        for(int i=0; i<products.size(); i++) {
            if(newObject == products.get(i)) {
                return false;
            }
        }
        products.add(newObject);
        return true;
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
