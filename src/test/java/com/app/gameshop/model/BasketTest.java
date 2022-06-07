package com.app.gameshop.model;

import com.app.gameshop.model.Basket;
import com.app.gameshop.model.Client;
import com.app.gameshop.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BasketTest {
    @Test
    public void addProduct(){
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("asd");
        product.setPrice((10));
        Client client = new Client(UUID.randomUUID(),"test","1234",1,1,1999);
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),0);
        client.getBasket().getProductList().add(product);
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),1);
    }
    @Test
    public void removeProduct(){
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setName("asd");
        product1.setPrice((10));
        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setName("tyr");
        product2.setPrice((20));
        Client client = new Client(UUID.randomUUID(),"test","1234",1,1,1999);
        client.getBasket().getProductList().add(product1);
        client.getBasket().getProductList().add(product2);
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),2);
        client.getBasket().getProductList().remove(product1);
        Assertions.assertEquals(client.getBasket().getProductList().getAll().size(),1);
        Assertions.assertTrue(client.getBasket().getProductList().getAll().contains(product2));
        Assertions.assertFalse(client.getBasket().getProductList().getAll().contains(product1));
    }
    @Test
    public void calculateBasket(){
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setName("asd");
        product1.setPrice((10));
        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setName("ahb");
        product2.setPrice((50));
        Product product3 = new Product();
        product3.setId(UUID.randomUUID());
        product3.setName("yhb");
        product3.setPrice((30));
        Product product4 = new Product();
        product4.setId(UUID.randomUUID());
        product4.setName("qwe");
        product4.setPrice((10));
        Client client = new Client(UUID.randomUUID(),"test","1234",1,1,1999);
        client.getBasket().getProductList().add(product1);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),10);
        client.getBasket().getProductList().add(product2);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),60);
        client.getBasket().getProductList().add(product3);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),90);
        client.getBasket().getProductList().add(product4);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),100);
        client.getBasket().getProductList().remove(product3);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),70);
        client.getBasket().getProductList().remove(product2);
        Assertions.assertEquals(client.getBasket().calculateBasketPrice(),20);
    }
}
