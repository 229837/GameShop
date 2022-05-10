package com.app.GameShop.controllers;

import com.app.GameShop.model.Client;
import com.app.GameShop.model.Comment;
import com.app.GameShop.services.CommentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Comment add(@RequestParam Client owner, @RequestParam String commentContent, @RequestParam int rating, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        try {
            Comment comment = new Comment(owner, commentContent, rating, dateTime);
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

    @GetMapping("/comments/find/{id}")
    public Comment find(@PathVariable String uuid) {
        return commentService.find(UUID.fromString(uuid));
    }
}
