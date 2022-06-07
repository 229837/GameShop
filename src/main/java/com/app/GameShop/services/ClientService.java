package com.app.gameshop.services;

import com.app.GameShop.model.Client;
import com.app.GameShop.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public boolean add(Client client) {
        return clientRepository.add(client);
    }

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Client getByID(int id) {
        if(id < 0 || id > clientRepository.size()-1) {
            return null;
        }
        else {
            return clientRepository.get(id);
        }
    }

    public Client find(UUID id) {
        for(int i=0; i< clientRepository.size(); i++) {
            if(clientRepository.get(i).getId().equals(id)) {
                return clientRepository.get(i);
            }
        }
        return null;
    }

    public com.app.GameShop.model.Client findByLogin(String name) {
        for(int i=0; i< clientRepository.size(); i++) {
            if(clientRepository.get(i).getLogin().equals(name)) {
                return clientRepository.get(i);
            }
        }
        return null;
    }
}
