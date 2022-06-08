package com.app.gameshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {
    @Test
    void ownerTest() {
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        Comment comment = new Comment(client, "Test Comment", 3, LocalDateTime.now());
        assertEquals(client, comment.getOwner());
        assertThrows(IllegalArgumentException.class, () -> new Comment(null, "Test Comment", 3, LocalDateTime.now()));
    }

    @Test
    void contentTest() {
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        Comment comment = new Comment(client, "Test Comment", 3, LocalDateTime.now());
        assertEquals("Test Comment", comment.getComment());
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, "", 3, LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, null, 3, LocalDateTime.now()));
    }

    @Test
    void dateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        Comment comment = new Comment(client, "Test Comment", 3, localDateTime);
        assertEquals(localDateTime, comment.getDateTime());
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, "Test Comment", 3, null));
    }

    @Test
    void ratingTest() {
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        for (int i = 1; i <= 5; i++) {
            Comment comment = new Comment(client, "Test Comment", i, LocalDateTime.now());
            assertEquals(i, comment.getRating());
        }
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, "Test Comment", -1, LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, "Test Comment", 0, LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Comment(client, "Test Comment", 6, LocalDateTime.now()));
    }

    @Test
    void uuidTest() {
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        List<UUID> uuids = new ArrayList<>();
        for (int i = 0; i < 65536; i++) {
            Comment comment = new Comment(client, "Test Comment", 3, LocalDateTime.now());
            UUID uuid = comment.getUuid();
            assertFalse(uuids.contains(uuid));
            uuids.add(uuid);
        }
    }
}
