package com.app.gameshop.repositories;

import com.app.gameshop.model.Comment;
import com.app.gameshop.repositories.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository
public class CommentRepository implements Repository<Comment> {
    private final List<Comment> comments = new ArrayList<>();

    @Override
    public Comment get(int id) {
        return comments.get(id);
    }

    @Override
    public List<Comment> getAll() {
        return Collections.unmodifiableList(comments);
    }

    @Override
    public boolean add(Comment newObject) {
        return comments.add(newObject);
    }

    @Override
    public boolean remove(Comment object) {
        return comments.remove(object);
    }

    @Override
    public int size() {
        return comments.size();
    }
}
