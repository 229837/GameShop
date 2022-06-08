package com.app.gameshop.services;

import com.app.gameshop.model.Comment;
import com.app.gameshop.repositories.CommentRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean add(Comment comment) {
        return commentRepository.add(comment);
    }

    public Comment get(int id) {
        if (id < 0 || id > commentRepository.size() - 1) {
            return null;
        } else {
            return commentRepository.get(id);
        }
    }

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment find(UUID uuid) {
        for (int i = 0; i < commentRepository.size(); i++) {
            if (commentRepository.get(i).getUuid().equals(uuid)) {
                return commentRepository.get(i);
            }
        }
        return null;
    }

    private List<Comment> sort(Comparator<? super Comment> comparator) {
        List<Comment> sortedComments = new ArrayList<>(commentRepository.getAll());
        sortedComments.sort(comparator);
        return sortedComments;
    }

    public List<Comment> getSortedByWorstRating() {
        return Collections.unmodifiableList(sort(Comparator.comparingInt(Comment::getRating)));
    }

    public List<Comment> getSortedByBestRating() {
        List<Comment> comments = sort(Comparator.comparingInt(Comment::getRating));
        Collections.reverse(comments);
        return Collections.unmodifiableList(comments);
    }

    public List<Comment> getSortedByOldest() {
        return sort(Comparator.comparing(Comment::getDateTime));
    }

    public List<Comment> getSortedByNewest() {
        List<Comment> comments = sort(Comparator.comparing(Comment::getDateTime));
        Collections.reverse(comments);
        return comments;
    }
}
