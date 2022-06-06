package com.app.GameShop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Basket {
    private List<Product> productList;

    public Basket(List<Product> productList) {
        this.productList = productList;
    }
    public void addProduct(Product product){
        getProductList().add(product);
    }
    public void removeProduct(Product product){
        for(Product product1 : getProductList()){
            if (product1 == product) {
                getProductList().remove(product);
                return;
            }
        }
    }

    public float calculateBasketPrice(){
        float money = 0;
        for(Product product : getProductList()){
            money = money + product.getPrice();
        }
        return money;
    }
}
