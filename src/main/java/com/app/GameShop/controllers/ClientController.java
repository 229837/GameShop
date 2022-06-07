package com.app.gameshop.controllers;

import com.app.gameshop.model.*;
import com.app.gameshop.services.ClientService;
import com.app.gameshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    public Product addProductToBasket(@PathVariable("uuid_client") String uuid_client, @RequestParam String uuid_product){
     //   clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().add(productController.find(uuid_product));
        if(!Objects.isNull(clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().find(UUID.fromString(uuid_product)))){
            return clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().find(UUID.fromString(uuid_product));
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/products/find/" + uuid_product;
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url,Product.class);
        clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().add(responseEntity.getBody());
        return  clientService.find(UUID.fromString(uuid_client)).getBasket().getProductList().find(UUID.fromString(uuid_product));
    }
}
