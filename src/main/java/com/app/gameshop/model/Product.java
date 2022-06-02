package com.app.gameshop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private float price;
    private Genre genre;
    private LocalDate releaseDate;

    public Product() {
        this.id = UUID.randomUUID();
        this.name = "...";
        this.price = 0.0f;
        this.genre = Genre.Undefined;
        this.releaseDate = LocalDate.of(1,1,1);
    }

    public Product(String name, float price, Genre genre, LocalDate releaseDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

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
