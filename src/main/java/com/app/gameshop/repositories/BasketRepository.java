package com.app.gameshop.repositories;


import com.app.GameShop.model.Basket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository
public class BasketRepository implements Repository<Basket> {

    private List<Basket> baskets = new ArrayList<>();

    @Override
    public Basket get(int id) {
        return baskets.get(id);
    }

    @Override
    public List<Basket> getAll() {
        return Collections.unmodifiableList(baskets);
    }

    @Override
    public boolean add(Basket newObject) {
        return baskets.add(newObject);
    }

    @Override
    public boolean remove(Basket object) {
        return baskets.remove(object);
    }

    @Override
    public int size() {
        return baskets.size();
    }
}
