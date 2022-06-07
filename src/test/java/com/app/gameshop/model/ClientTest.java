package com.app.gameshop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ClientTest {

    @Test
    public void buyGames(){
        Product product = new Product();
        product.setPrice(10);
        Product product2 = new Product();
        product2.setPrice(30);
        Product product3 = new Product();
        product3.setPrice(5);
        Client client = new Client(UUID.randomUUID(),"test","1234",1,1,1999);
        client.getBasket().getProductList().add(product);
        client.getBasket().getProductList().add(product2);
        client.getBasket().getProductList().add(product3);
        client.getAccount().getWallet().setBalance(50);
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),3);
        Assertions.assertEquals(client.getAccount().getMyGames().getAll().size(),0);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),45);
        client.BuyGames();
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),0);
        Assertions.assertEquals(client.getAccount().getMyGames().getAll().size(),3);
        Assertions.assertEquals(client.getAccount().getWallet().getBalance(),5);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),0);
    }
}
