package com.app.gameshop.controllers;

import com.app.gameshop.igdbconnection.IGDBConnection;
import com.app.gameshop.model.Genre;
import com.app.gameshop.model.Product;
import com.app.gameshop.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
        IGDBConnection.fillProductsRepository(productService);
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        List<Product> products = productService.getAll();
        return products;
    }

    @GetMapping("/products/id/{id}")
    public Product get(@PathVariable String id) {
        return productService.getByID(Integer.parseInt(id));
    }

    @GetMapping("/products/find/{id}")
    public Product find(@PathVariable String id) {
        return productService.find(UUID.fromString(id));
    }

    @GetMapping("/products/find_by_name/{name}")
    public Product findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    /// EXAMPLE : http://localhost:8080/products/add?name=Product1&price=1.25&genre=1
    @GetMapping("/products/add")
    public Product add(@RequestParam String name, @RequestParam float price, @RequestParam int genre) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(name);
        product.setPrice((price));
        product.setGenre(Genre.intToGenre(genre));

        if(productService.add(product)) {
            return product;
        }
        else {
            return null;
        }
    }

    @GetMapping("/products/remove")
    public boolean removeUUID(@RequestParam String id) {
        return productService.remove(productService.find(UUID.fromString(id)));
    }
}
