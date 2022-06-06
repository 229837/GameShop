package com.app.GameShop.model;

import com.app.GameShop.repositories.ProductRepository;
import com.app.GameShop.services.ProductService;
import com.app.gameshop.model.Wallet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private Wallet wallet;
    private ProductService myGames;

    public Account() {
        this.wallet = new Wallet();
        this.myGames = new ProductService(new ProductRepository());
    }
}
