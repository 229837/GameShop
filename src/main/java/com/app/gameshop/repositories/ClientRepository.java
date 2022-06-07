package com.app.gameshop.repositories;

import com.app.gameshop.model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository
public class ClientRepository implements Repository <Client>{
    private final List<Client> clients = new ArrayList<>();
    @Override
    public Client get(int id) {
        return  clients.get(id);
    }

    @Override
    public List<Client> getAll() {
        return Collections.unmodifiableList(clients);
    }

    @Override
    public boolean add(Client newObject) {
        return clients.add(newObject);
    }

    @Override
    public boolean remove(Client object) {
        return clients.remove(object);
    }

    @Override
    public int size() {
        return clients.size();
    }
}
