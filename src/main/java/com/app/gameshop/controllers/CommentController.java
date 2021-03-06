package com.app.gameshop.controllers;

import com.app.gameshop.model.Client;
import com.app.gameshop.model.Comment;
import com.app.gameshop.services.CommentService;
import com.app.gameshop.model.Basket;
import com.app.gameshop.model.Client;
import com.app.gameshop.model.Comment;
import com.app.gameshop.model.Product;
import com.app.gameshop.services.CommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/add")
    public Comment add(@RequestParam String ownerUuid, @RequestParam String commentContent, @RequestParam int rating) {
        try {
            //TODO get existing client
            Client owner = new Client(UUID.fromString(ownerUuid), "Client", "1234", 1, 1, 1999);
            Comment comment = new Comment(owner, commentContent, rating, LocalDateTime.now());
            if (commentService.add(comment)) {
                return comment;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/comments/id/{id}")
    public Comment get(@PathVariable String id) {
        return commentService.get(Integer.parseInt(id));
    }

    @GetMapping("/comments")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/comments/find/{uuid}")
    public Comment find(@PathVariable String uuid) {
        return commentService.find(UUID.fromString(uuid));
    }
}
