package com.app.gameshop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MetadataTest {

    @Test
    void dataTest() {
        Metadata metadata = new Metadata();

        assertEquals(metadata.getDateAdded().getYear(), LocalDateTime.now().getYear());
        assertEquals(metadata.getDateAdded().getMonth(), LocalDateTime.now().getMonth());
        assertEquals(metadata.getDateAdded().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
        assertEquals(metadata.getDateAdded().getHour(), LocalDateTime.now().getHour());
        assertEquals(metadata.getDateAdded().getMinute(), LocalDateTime.now().getMinute());
        assertEquals(metadata.getDateAdded().getSecond(), LocalDateTime.now().getSecond());

        assertEquals(metadata.getNumberOfPurchases(), 0);
        metadata.increasePurchases();
        assertEquals(metadata.getNumberOfPurchases(), 1);
    }

}