package com.app.gameshop.repositories;

import java.util.List;

public interface Repository<T> {

        public T get(int id);
        public List<T> getAll();
        public boolean add(T newObject);
        public boolean remove(T object);
        public int size();
}
