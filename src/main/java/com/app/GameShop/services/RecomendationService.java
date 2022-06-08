package com.app.GameShop.services;

import com.app.gameshop.model.Product;
import com.app.gameshop.model.Client;
import com.app.gameshop.model.Genre;
import com.app.gameshop.services.ProductService;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecomendationService {

    public static List<Product> getRecommentatedProducts(Client client, ProductService gameService) {

        List<Product> propositions = new ArrayList<>();

        List<Product> myGames = client.getAccount().getMyGames().getAll();
        List<Product> allGames = gameService.sortByPopularityZA();

        Genre favoriteGenre = getCommonGenre(client.getAccount().getMyGames());
        Point releaseYear = getYears(client.getAccount().getMyGames());
        Point price = getPrice(client.getAccount().getMyGames());

        int points = 0;
        boolean exist = false;
        for (Product allGame : allGames) {
            if(allGame.getDetails().getNumberOfPurchases() < 0.75*allGames.get(0).getDetails().getNumberOfPurchases()) {
                break;
            }

            if (allGame.getGenre() == favoriteGenre) {
                points += 50;
            }

            if (allGame.getReleaseDate().getYear() >= releaseYear.x && allGame.getReleaseDate().getYear() <= releaseYear.y) {
                points += 50;
            } else {
                if (allGame.getReleaseDate().getYear() < releaseYear.x) {
                    points += 50 - (releaseYear.x - allGame.getReleaseDate().getYear()) * 10;
                } else if (allGame.getReleaseDate().getYear() > releaseYear.y) {
                    points += 50 - (allGame.getReleaseDate().getYear() - releaseYear.y) * 10;
                }
            }

            if (allGame.getPrice() >= price.x && allGame.getPrice() <= price.y) {
                points += 15;
            }

            if (points >= 75) {
                for (Product myGame : myGames) {
                    if (myGame == allGame) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    propositions.add(allGame);
                }
            }
            points = 0;
            exist = false;
        }

        return propositions;
    }

    private static Genre getCommonGenre(ProductService gameService) {
        if(gameService.getAll().size() == 0) {
            return Genre.Undefined;
        }

        List<Point> genreID = new ArrayList<>();
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

    private static Point getYears(ProductService gameService) {
        if(gameService.getAll().size() == 0) {
            return new Point(0, LocalDate.now().getYear());
        }

        float avg = 0;
        List<Product> games = gameService.sortByReleaseDateAZ();

        for (Product game : games) {
            avg += game.getReleaseDate().getYear();
        }

        avg /= games.size();

        float offsetPlus = 0;
        float offsetMinus = 0;

        for (Product game : games) {
            if (game.getReleaseDate().getYear() > avg) {
                offsetPlus += 0.1f;
            } else if (game.getReleaseDate().getYear() < avg) {
                offsetMinus += 0.1f;
            }
        }

        int min = (int) (avg-offsetMinus) < games.get(0).getReleaseDate().getYear() ? games.get(0).getReleaseDate().getYear() - 5 : (int) (avg-offsetMinus-2);
        int max = (int) (avg+offsetPlus) > games.get(games.size()-1).getReleaseDate().getYear() ? games.get(games.size()-1).getReleaseDate().getYear() + 5 : (int) (avg+offsetPlus+2);

        return new Point(min,max);
    }

    private static Point getPrice(ProductService gameService) {
        if(gameService.getAll().size() == 0) {
            return new Point(0, 9999);
        }

        float avg = 0;
        int free = 0;
        List<Product> games = gameService.sortByPriceAZ();

        for (Product game : games) {
            if (game.getPrice() == 0) {
                free++;
            } else {
                avg += game.getPrice();
            }
        }
        avg /= (games.size() - free);

        if(free > games.size() * 0.90) {
            return new Point(0, 0);
        }
        else if(free < games.size() * 0.10) {
            return new Point(1, (int) games.get(games.size()-1).getPrice()+25);
        }
        else {
            int min = (int) (-1.25*avg);
            int max = (int) (1.25*avg);

            if(min < 0) min = 0;

            return new Point(min,max);
        }
    }
}
