package com.app.GameShop.controllers;

import com.app.GameShop.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public String GetProducts() throws Exception {

        return "Wszystko";
    }

    @GetMapping("/products/{id}")
    public String GetProduct(@PathVariable int id) throws Exception {

        return "Tylko " + Integer.toString(id);
    }

}
