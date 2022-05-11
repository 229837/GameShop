package com.app.gameshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {

    private UUID id;
    private String name;
    private float price;
    Genre genre;

    @Override
    public String toString() {
        return "Product (" + id + ")" +
                "</br>{" +
                "</br>name='" + name + "'," +
                "</br>price=" + price + "," +
                "</br>genre=" + genre +
                "</br>}</br>";
    }
}
