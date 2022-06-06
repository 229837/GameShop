package com.app.GameShop.services;
import com.app.GameShop.model.Genre;
import com.app.GameShop.model.Product;
import com.app.GameShop.repositories.ProductRepository;
import org.apache.maven.model.Developer;
import com.app.GameShop.model.Product;
import com.app.GameShop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

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

    public List<Product> getMostPopular(int amount) {
        List<Product> top = sortByPopularityZA();

        if(amount > productRepository.size()) {
            return Collections.unmodifiableList(top);
        }

        List<Product> result = new ArrayList<>();
        for(int i=0; i<amount; i++) {
            result.add(top.get(i));
        }
        return Collections.unmodifiableList(result);
    }

    private List<Product> sort(Comparator<? super Product> cmp) {
        List<Product> result = new ArrayList<>();

        for(int i=0; i<productRepository.size(); i++) {
            result.add(productRepository.get(i));
        }

        result.sort(cmp);

        return result;
    }

    /// ==== DIFFERENT SORT METHODS

    public List<Product> sortByPriceAZ() {
        return sort(Comparator.comparingDouble(p -> p.getPrice()));
    }

    public List<Product> sortByPriceZA() {
        List<Product> result = sortByPriceAZ();
        Collections.reverse(result);
        return result;
    }

    public List<Product> sortByNameAZ() {
        return sort(Comparator.comparing(Product::getName));
    }

    public List<Product> sortByNameZA() {
        List<Product> result = sortByNameAZ();
        Collections.reverse(result);
        return result;
    }

    public List<Product> sortByGenreAZ() {
        return sort(Comparator.comparing(Product::getGenre));
    }

    public List<Product> sortByGenreZA() {
        List<Product> result = sortByGenreAZ();
        Collections.reverse(result);
        return result;
    }

    public List<Product> sortByPopularityAZ() {
        return sort(Comparator.comparingInt(p -> p.getDetails().getNumberOfPurchases()));
    }

    public List<Product> sortByPopularityZA() {
        List<Product> result = sortByPopularityAZ();
        Collections.reverse(result);
        return result;
    }

    public List<Product> sortByAddDateAZ() {
        return sort(Comparator.comparing(p -> p.getDetails().getDateAdded()));
    }

    public List<Product> sortByAddDateZA() {
        List<Product> result = sortByAddDateAZ();
        Collections.reverse(result);
        return result;
    }

    public List<Product> sortByReleaseDateAZ() {
        return sort(Comparator.comparing(p -> p.getReleaseDate()));
    }

    public List<Product> sortByReleaseDateZA() {
        List<Product> result = sortByReleaseDateAZ();
        Collections.reverse(result);
        return result;
    }
}
