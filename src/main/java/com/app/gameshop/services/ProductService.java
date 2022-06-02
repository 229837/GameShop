package com.app.gameshop.services;

import com.app.gameshop.model.Genre;
import com.app.gameshop.model.Product;
import com.app.gameshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean add(Product product) {
        return productRepository.add(product);
    }

    public boolean remove(Product product) {
        return productRepository.remove(product);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product getByID(int id) {
        if(id < 0 || id > productRepository.size()-1) {
            return null;
        }
        else {
            return productRepository.get(id);
        }
    }

    public Product find(UUID id) {
        for(int i=0; i< productRepository.size(); i++) {
            if(productRepository.get(i).getId().equals(id)) {
                return productRepository.get(i);
            }
        }
        return null;
    }

    public Product findByName(String name) {
        for(int i=0; i<productRepository.size(); i++) {
            if(name.equals(productRepository.get(i).getName())) {
                return productRepository.get(i);
            }
        }
        return null;
    }

    public List<Product> findByGenre(Genre genre) {
        List<Product> result = new ArrayList<>();

        for(int i=0; i<productRepository.size(); i++) {
            if(productRepository.get(i).getGenre() == genre) {
                result.add(productRepository.get(i));
            }
        }

        return Collections.unmodifiableList(result);
    }

    public List<Product> findFreeGames() {
        List<Product> result = new ArrayList<>();

        for(int i=0; i<productRepository.size(); i++) {
            if(productRepository.get(i).getPrice() < 0.001f) {
                result.add(productRepository.get(i));
            }
        }

        return Collections.unmodifiableList(result);
    }
}
