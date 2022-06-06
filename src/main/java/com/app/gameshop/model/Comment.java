package com.app.gameshop.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Comment {
    private final UUID uuid;
    private final Client owner;
    private final String comment;
    private final int rating;
    private final LocalDateTime dateTime;

    public Comment(Client owner, String comment, int rating, LocalDateTime dateTime) throws IllegalArgumentException {
        if (owner == null) {
            throw new IllegalArgumentException("Invalid owner");
        }
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("Comment can not be empty");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating not in range");
        }
        if (dateTime == null) {
            throw new IllegalArgumentException("Invalid date time");
        }
        this.uuid = UUID.randomUUID();
        this.owner = owner;
        this.comment = comment;
        this.rating = rating;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Comment (" + uuid + "): [Owner: " + owner.toString() + "] [Rating: " + rating + "] [" + dateTime + "] \"" + comment + "\"";
    }
}
