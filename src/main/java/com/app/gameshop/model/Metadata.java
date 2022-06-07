package com.app.gameshop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Metadata {
    public int numberOfPurchases;
    public LocalDateTime dateAdded;

    public void increasePurchases() {
        numberOfPurchases++;
    }

    public Metadata() {
        this.numberOfPurchases = 0;
        this.dateAdded = LocalDateTime.now();
    }
}
