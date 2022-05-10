package com.app.GameShop.services;

import com.app.GameShop.model.Comment;
import com.app.GameShop.repositories.CommentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public boolean add(Comment comment) {
        return commentRepository.add(comment);
    }

    public Comment get(int id) {
        if(id < 0 || id > commentRepository.size() - 1) {
            return null;
        }
        else {
            return commentRepository.get(id);
        }
    }

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment find(UUID uuid) {
        for(int i = 0; i < commentRepository.size(); i++) {
            if(commentRepository.get(i).getUuid().equals(uuid)) {
                return commentRepository.get(i);
            }
        }
        return null;
    }
}
