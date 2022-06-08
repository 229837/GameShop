package com.app.gameshop.services;

import com.app.gameshop.model.Client;
import com.app.gameshop.model.Comment;
import com.app.gameshop.repositories.CommentRepository;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceTest {
    @Test
    void sortTest() {
        Random random = new Random();
        CommentService commentService = new CommentService(new CommentRepository());
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        for(int i = 0; i < 512; i++) {
            Comment comment = new Comment(client, "Test Comment " + i, random.nextInt(5) + 1, LocalDateTime.ofEpochSecond(ThreadLocalRandom.current().nextLong(LocalDateTime.of(1970, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC), LocalDateTime.of(2022, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC)), 0, ZoneOffset.UTC));
            commentService.add(comment);
        }
        assertEquals(512, commentService.getAll().size());

        List<Comment> commentsSortedByBestRating = commentService.getSortedByBestRating();
        assertEquals(commentService.getAll().size(), commentsSortedByBestRating.size());
        int previousRating = commentsSortedByBestRating.get(0).getRating();
        for (int i = 1; i < commentsSortedByBestRating.size(); i++) {
            int currentRating = commentsSortedByBestRating.get(i).getRating();
            assertTrue(previousRating >= currentRating);
            previousRating = currentRating;
        }

        List<Comment> commentsSortedByWorstRating = commentService.getSortedByWorstRating();
        assertEquals(commentService.getAll().size(), commentsSortedByWorstRating.size());
        previousRating = commentsSortedByWorstRating.get(0).getRating();
        for (int i = 1; i < commentsSortedByWorstRating.size(); i++) {
            int currentRating = commentsSortedByWorstRating.get(i).getRating();
            assertTrue(previousRating <= currentRating);
            previousRating = currentRating;
        }

        List<Comment> commentsSortedByNewest = commentService.getSortedByNewest();
        assertEquals(commentService.getAll().size(), commentsSortedByNewest.size());
        LocalDateTime previousDateTime = commentsSortedByNewest.get(0).getDateTime();
        for (int i = 1; i < commentsSortedByNewest.size(); i++) {
            LocalDateTime currentDateTime = commentsSortedByNewest.get(i).getDateTime();
            assertTrue(previousDateTime.isAfter(currentDateTime));
            previousDateTime = currentDateTime;
        }

        List<Comment> commentsSortedByOldest = commentService.getSortedByOldest();
        assertEquals(commentService.getAll().size(), commentsSortedByOldest.size());
        previousDateTime = commentsSortedByOldest.get(0).getDateTime();
        for (int i = 1; i < commentsSortedByOldest.size(); i++) {
            LocalDateTime currentDateTime = commentsSortedByOldest.get(i).getDateTime();
            assertTrue(previousDateTime.isBefore(currentDateTime));
            previousDateTime = currentDateTime;
        }
    }
}
