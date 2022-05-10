package com.app.GameShop.controllers;

import com.app.GameShop.model.Genre;
import com.app.GameShop.model.Product;
import com.app.GameShop.services.ProductService;
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
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        List<Product> products = productService.getAll();
        return products;

//        String productsDescription = "";
//        for(int i=0; i<products.size(); i++) {
//            productsDescription += products.get(i).toString();
//        }
//
//        return productsDescription;
    }

    @GetMapping("/products/id/{id}")
    public Product get(@PathVariable String id) {
        return productService.getByID(Integer.parseInt(id));
    }

    @GetMapping("/products/find/{id}")
    public Product find(@PathVariable String id) {
        return productService.find(UUID.fromString(id));
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
}
