package com.app.gameshop.services;

import com.app.gameshop.model.Basket;
import com.app.gameshop.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public boolean add(Basket basket){
        return basketRepository.add(basket);
    }

    public List<Basket> getAll() {
        return basketRepository.getAll();
    }


    public Basket getByID(int id) {
        if(id < 0 || id > basketRepository.size()-1) {
            return null;
        }
        else {
            return basketRepository.get(id);
        }
    }
}
