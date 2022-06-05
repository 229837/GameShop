package com.app.gameshop.model;

import com.app.gameshop.repositories.ProductRepository;
import com.app.gameshop.services.ProductService;
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
