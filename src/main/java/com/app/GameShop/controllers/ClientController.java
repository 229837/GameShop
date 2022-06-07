package com.app.gameshop.controllers;

import com.app.gameshop.model.Client;
import com.app.gameshop.model.Product;
import com.app.gameshop.services.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/clients/id/{id}")
    public Client get(@PathVariable String id) {
        return clientService.getByID(Integer.parseInt(id));
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
