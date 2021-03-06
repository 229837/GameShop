package com.app.gameshop.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Comment> comments;

    public Product() {
        this.id = UUID.randomUUID();
        this.name = "...";
        this.price = 0.0f;
        this.genre = Genre.Undefined;
        this.releaseDate = LocalDate.of(1,1,1);
        this.details = new Metadata();
        this.comments = new ArrayList<>();
    }

    public Product(String name, float price, Genre genre, LocalDate releaseDate, List<Comment> comments) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = Math.round(price * 100.f) / 100.f;
        if(price < 0.0f) {
            this.price = 0.0f;
        }
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.details = new Metadata();
        this.comments = new ArrayList<>(comments);
    }

    public void setPrice(float price) {
        this.price = Math.round(price * 100.f) / 100.f;
        if(price < 0.0f) {
            this.price = 0.0f;
        }
    }

    public void addComment(Comment comment) {
        if (comment != null && !comments.contains(comment)) {
            comments.add(comment);
        }
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
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
