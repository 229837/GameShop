package com.app.gameshop.services;

import com.app.gameshop.model.Client;
import com.app.gameshop.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private  ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean add(Client client) {
        return clientRepository.add(client);
    }

    public boolean remove(Client client) {
        return clientRepository.remove(client);
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

    public Client findByLogin(String login) {
        for(int i=0; i< clientRepository.size(); i++) {
            if(clientRepository.get(i).getLogin().equals(login)) {
                return clientRepository.get(i);
            }
        }
        return null;
    }
}
