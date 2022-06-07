package com.app.gameshop.model;

import com.app.gameshop.repositories.ProductRepository;
import com.app.gameshop.services.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Basket {
    private ProductService productList;

    public Basket() {
        this.productList = new ProductService(new ProductRepository());
    }

    public float calculateBasketPrice(){
        float money = 0;
        for(Product product : getProductList().getAll()){
            money = money + product.getPrice();
        }
        return money;
    }
}
