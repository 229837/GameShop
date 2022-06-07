package com.app.gameshop.controllers;

import com.app.gameshop.model.*;
import com.app.gameshop.services.ClientService;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.*;
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
    @GetMapping("/clients")
    public List<Client> getAll(){
        return clientService.getAll();
    }
    @GetMapping("/clients/id/{id}")
    public Client get(@PathVariable String id){
        return clientService.getByID(Integer.parseInt(id));
    }
    @GetMapping("/clients/find/{uuid}")
    public Client find(@PathVariable String uuid){
        return clientService.find(UUID.fromString(uuid));
    }


    @GetMapping("/clients/add")
    public Client add(@RequestParam String name, @RequestParam String password){
        LocalDate today = LocalDate.now();
        Client client = new Client(UUID.randomUUID(),name,password,today.getDayOfMonth(),today.getMonthValue(),today.getYear());
        client.getAccount().getWallet().setBalance(100);
        if(clientService.add(client)){
            return client;
        }
        return null;
    }

    @GetMapping("/clients/find/{uuid}/basket")
    public Basket getBasket(@PathVariable String uuid){
        return clientService.find(UUID.fromString(uuid)).getBasket();
    }
    @GetMapping("/clients/find/{uuid}/account")
    public Account getAccount(@PathVariable String uuid){
        return clientService.find(UUID.fromString(uuid)).getAccount();
    }
    @GetMapping("/clients/find/{uuid_client}/basket/id")
    public Client addProductToBasket(@PathVariable("uuid_client") String uuid_client, @RequestParam String uuid_product){
     //   clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().add(productController.find(uuid_product));
        if(!Objects.isNull(clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().find(UUID.fromString(uuid_product)))){
            return clientService.find(UUID.fromString(uuid_client));
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/products/find/" + uuid_product;
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url,Product.class);
        clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().add(responseEntity.getBody());
        return  clientService.find(UUID.fromString(uuid_client));
    }
    @GetMapping("/clients/find/{uuid_client}/basket/buy_products")
    public Client buyProducts(@PathVariable("uuid_client") String id){
        clientService.find(UUID.fromString(id)).BuyGames();
        for(Product product : clientService.find(UUID.fromString(id)).getAccount().getMyGames().getAll()){
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/products/find/" + product.getId();
            ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url,Product.class);
            if(responseEntity.hasBody() && responseEntity.getBody() != null){
                responseEntity.getBody().getDetails().increasePurchases();
            }
        }
        return clientService.find(UUID.fromString(id));
    }

    @GetMapping("/clients/find_by_login/{login}")
    public Client findByLogin(@PathVariable String name) {
        return clientService.findByLogin(name);
    }
}
