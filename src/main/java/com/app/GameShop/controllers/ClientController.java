package com.app.gameshop.controllers;

import com.app.GameShop.model.Client;
import com.app.gameshop.services.ClientService;
import com.app.gameshop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
public class ClientController {

    private final ClientService clientService;


    @Autowired
    FirebaseService fireBaseService;

    public ClientController(ClientService clientservice) {
        this.clientService = clientservice;
    }

    @GetMapping("/clients/id/{id}")
    public Client get(@PathVariable String id) {
        return clientService.getByID(Integer.parseInt(id));
    }

    @GetMapping("/createUser")
    public String postExample(@RequestBody Client client) throws ExecutionException, InterruptedException {
        return fireBaseService.saveClientDetails(client);
    }

    @GetMapping("/clients/find/{id}")
    public Client find(@PathVariable String id) {
        return clientService.find(UUID.fromString(id));
    }

    @GetMapping("/clients/find_by_login/{login}")
    public Client findByLogin(@PathVariable String name) {
        return clientService.findByLogin(name);
    }

}
