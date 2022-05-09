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
    public String getAll() {
        List<Product> products = productService.getAll();

        String productsDescription = "";
        for(int i=0; i<products.size(); i++) {
            productsDescription += products.get(i).toString();
        }

        return productsDescription;
    }

    @GetMapping("/products/id/{id}")
    public String get(@PathVariable String id) {
        String productDescription = "";
        Product pointer = productService.getByID(Integer.parseInt(id));
        if(pointer != null) {
            productDescription = pointer.toString();
        }
        else {
            productDescription = "Product not found, wrong ID:" + id;
        }
        return productDescription;
    }

    @GetMapping("/products/find/{id}")
    public String find(@PathVariable String id) {
        String productDescription = "";
        Product pointer = productService.find(UUID.fromString(id));
        if(pointer != null) {
            productDescription = pointer.toString();
        }
        else {
            productDescription = "Product not found, wrong UUID:" + id;
        }
        return productDescription;
    }

    /// EXAMPLE : http://localhost:8080/products/add?name=Product1&price=1.25&genre=1
    @GetMapping("/products/add")
    public String add(@RequestParam String name, @RequestParam float price, @RequestParam int genre) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(name);
        product.setPrice((price));
        product.setGenre(Genre.intToGenre(genre));

        if(productService.add(product)) {
            return "Add new object \n" + product.toString();
        }
        else {
            return "Add method error";
        }
    }
}
