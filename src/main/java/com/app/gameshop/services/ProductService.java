package com.app.gameshop.services;

import com.app.gameshop.model.Product;
import com.app.gameshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public boolean add(Product product) {
        return productRepository.add(product);
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
}
