package com.app.GameShop.services;

import com.app.gameshop.model.Product;
import com.app.gameshop.model.Client;
import com.app.gameshop.model.Genre;
import com.app.gameshop.services.ProductService;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecomendationService {

    public static List<Product> getRecommentatedProducts(Client client, ProductService gameService) {

        List<Product> myGames = client.getAccount().getMyGames().getAll();
        List<Product> allGames = gameService.sortByPopularityZA();

        Genre favoriteGenre = getCommonGenre(client.getAccount().getMyGames());
        Point2D releaseYear = getYears(client.getAccount().getMyGames());

        return null;
    }

    public static Genre getCommonGenre(ProductService gameService) {

        if(gameService.getAll().size() == 0) {
            return Genre.Undefined;
        }
        List<Point2D> genreID = new ArrayList<>();
        List<Product> sortedByGenre = gameService.sortByGenreAZ();
        Genre last = sortedByGenre.get(0).getGenre();
        int amount = 0;
        for(int i=0; i<=sortedByGenre.size(); i++) {
            if(i == sortedByGenre.size()) {
                genreID.add(new Point(Genre.genreToInt(last), amount));
                break;
            }

            if(sortedByGenre.get(i).getGenre() == last) {
                amount++;
            }
            else {
                genreID.add(new Point(Genre.genreToInt(last), amount));
                last = sortedByGenre.get(i).getGenre();
                amount = 1;
            }
        }

        genreID.sort(Comparator.comparingInt(p -> (int) p.getY()));

        return Genre.intToGenre((int) genreID.get(genreID.size()-1).getX());
    }

    public static Point2D getYears(ProductService gameService) {

        if(gameService.getAll().size() == 0) {
            return new Point(0, LocalDate.now().getYear());
        }

        float avg = 0;
        List<Product> games = gameService.sortByReleaseDateAZ();
        for(int i=0; i<games.size(); i++) {
            avg += games.get(i).getReleaseDate().getYear();
        }
        avg /= games.size();

        float offsetPlus = 0;
        float offsetMinus = 0;
        for(int i=0; i<games.size(); i++) {
            if(games.get(i).getReleaseDate().getYear() > avg) {
                offsetPlus += 0.1f;
            }
            else if (games.get(i).getReleaseDate().getYear() < avg) {
                offsetMinus += 0.1f;
            }
        }

        int min = (int) (avg-offsetMinus) < games.get(0).getReleaseDate().getYear() ? games.get(0).getReleaseDate().getYear() - 5 : (int) (avg-offsetMinus-2);
        int max = (int) (avg+offsetPlus) > games.get(games.size()-1).getReleaseDate().getYear() ? games.get(games.size()-1).getReleaseDate().getYear() + 5 : (int) (avg+offsetPlus+2);

        return new Point(min,max);
    }
}
