package com.app.gameshop.model;

import com.app.gameshop.model.Metadata;
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
    private Metadata details;

    public Product() {
        this.id = UUID.randomUUID();
        this.name = "...";
        this.price = 0.0f;
        this.genre = Genre.Undefined;
        this.releaseDate = LocalDate.of(1,1,1);
        this.details = new Metadata();
    }

    public Product(String name, float price, Genre genre, LocalDate releaseDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = Math.round(price * 100.f) / 100.f;
        if(price < 0.0f) {
            this.price = 0.0f;
        }
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.details = new Metadata();
    }

    public void setPrice(float price) {
        this.price = Math.round(price * 100.f) / 100.f;
        if(price < 0.0f) {
            this.price = 0.0f;
        }
    }

    @Override
    public String toString() {
        return "Product (" + id + ")" +
                "</br>{" +
                "</br>name='" + name + "'," +
                "</br>price=" + price + "," +
                "</br>genre=" + genre + "," +
                "</br>sold cpy=" + details.getNumberOfPurchases() + "," +
                "</br>date add=" + details.getDateAdded().toString() +
                "</br>}</br>";
    }
}
