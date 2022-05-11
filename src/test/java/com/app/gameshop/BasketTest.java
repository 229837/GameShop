package com.app.gameshop;

import com.app.gameshop.model.Basket;
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
        List<Product> productList = new ArrayList<>();
        Basket basket = new Basket(productList);
        Assertions.assertEquals(basket.getProductList().size(),0);
        basket.addProduct(product);
        Assertions.assertEquals(basket.getProductList().size(),1);
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
        List<Product> productList = new ArrayList<>();
        Basket basket = new Basket(productList);
        basket.addProduct(product1);
        basket.addProduct(product2);
        Assertions.assertEquals(basket.getProductList().size(),2);
        basket.removeProduct(product1);
        Assertions.assertEquals(basket.getProductList().size(),1);
        Assertions.assertTrue(basket.getProductList().contains(product2));
        Assertions.assertFalse(basket.getProductList().contains(product1));
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
        List<Product> productList = new ArrayList<>();
        Basket basket = new Basket(productList);
        basket.addProduct(product1);
        Assertions.assertEquals(basket.calculateBasketPrice(),10);
        basket.addProduct(product2);
        Assertions.assertEquals(basket.calculateBasketPrice(),60);
        basket.addProduct(product3);
        Assertions.assertEquals(basket.calculateBasketPrice(),90);
        basket.addProduct(product4);
        Assertions.assertEquals(basket.calculateBasketPrice(),100);
        basket.removeProduct(product3);
        Assertions.assertEquals(basket.calculateBasketPrice(),70);
        basket.removeProduct(product2);
        Assertions.assertEquals(basket.calculateBasketPrice(),20);
    }
}
