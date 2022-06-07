package com.app.gameshop.igdbconnection;

import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import com.app.gameshop.model.Genre;
import com.app.gameshop.model.Product;
import com.app.gameshop.services.ProductService;
import com.google.protobuf.InvalidProtocolBufferException;
import io.github.cdimascio.dotenv.Dotenv;
import proto.Game;
import proto.GameResult;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;



public class IGDBConnection {


    public static void fillProductsRepository(ProductService productService) {
        var listOfGames = getBestGames(50);
        if (listOfGames != null) {
            for (var game: listOfGames) {
                try {
                    float price = (float) (Math.random() * 100.0 + 50.0);

                    Genre genre = igdbIdToGenre(game.getGenres(0).getId());

                    LocalDate date = Instant
                            .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                            .atZone(ZoneId.of("GMT+1")).toLocalDate();

                    Product product = new Product(game.getName(), price, genre, date);
                    productService.add(product);

                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
        }
    }

    public static List<Game> getBestGames(int count) {
        String query = String.format(
                "f name, genres.name, total_rating, first_release_date;" +
                "w category=0 & total_rating_count>1000;"   +
                "s total_rating desc; limit %d;", count);
        var listOfGames = queryGamesRequest(query);
        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        }

        return listOfGames;
    }

    public static Game getGame(String gameName) {
        String query = String.format("fields name, genres, total_rating, first_release_date; where name ~ \"%s\"; sort total_rating desc; limit 5;", gameName);
        var listOfGames = queryGamesRequest(query);

        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
            return null;
        } else {
            return listOfGames.get(0);
        }
    }

    public static List<Game> getBestGamesByGenre(Genre genre, int count) {
        int genreId = genreToIgdbId(genre);
        String query = String.format("fields name, genres, total_rating, first_release_date; where genres = %d & total_rating > 1; sort total_rating desc; limit %d;", genreId, count);

        var listOfGames = queryGamesRequest(query);

        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        }

        return listOfGames;
    }

    public static List<Game> getUpcomingGames(int count) {
        long time = System.currentTimeMillis() / 1000;
        String query = String.format("f name, genres, first_release_date; w first_release_date > %d; sort first_release_date asc; limit %d;", time, count);

        var listOfGames = queryGamesRequest(query);
        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        }

        return listOfGames;
    }

    public static List<Game> getUpcomingGamesByGenre(Genre genre, int count) {
        long time = System.currentTimeMillis() / 1000;
        int genreId = genreToIgdbId(genre);
        String query = String.format("f name, genres, first_release_date; w first_release_date > %d & genres = %d; sort first_release_date asc; limit %d;", time, genreId, count);

        var listOfGames = queryGamesRequest(query);
        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        }

        return listOfGames;
    }


    private static int genreToIgdbId(Genre genre) {
        return switch (genre) {
            case POINTNCLICK-> 2; //Point and click
            case FIGHTING   -> 4;  //Fighting
            case FPS        -> 5;  //Shooter
            case MUSIC      -> 7;  //Music
            case PLATFORMER -> 8;  //Platform
            case PUZZLE     -> 9;  //Puzzle
            case RACING     -> 10; //Racing
            case STRATEGY   -> 11; //Real Time Strategy (RTS)
            case RPG        -> 12; //Role-playing (RPG)
            case SIMULATOR  -> 13; //Simulator
            case SPORT      -> 14; //Sport
            case ACTION     -> 25; //HackNSlash
            default -> -1;
        };
    }

    private static Genre igdbIdToGenre(long id) {
        return switch ((int) id) {
            case 2 -> Genre.POINTNCLICK;//Point and click
            case 4 -> Genre.FIGHTING;   //Fighting
            case 5 -> Genre.FPS;        //Shooter
            case 7 -> Genre.MUSIC;      //Music
            case 8 -> Genre.PLATFORMER; //Platform
            case 9 -> Genre.PUZZLE;     //Puzzle
            case 10 -> Genre.RACING;    //Racing
            case 11 -> Genre.STRATEGY;  //Real Time Strategy (RTS)
            case 12 -> Genre.RPG;       //Role-playing (RPG)
            case 13 -> Genre.SIMULATOR; //Simulator
            case 14 -> Genre.SPORT;     //Sport
            case 15 -> Genre.STRATEGY;  //Strategy
            case 25 -> Genre.ACTION;    //HackNSlash
            default -> Genre.ACTION;
        };
    }

    private static List<Game> queryGamesRequest(String query) {
        try {
            Dotenv dotenv = null;
            dotenv = Dotenv.configure().load();

            //setup these in .env file
            String clientId = dotenv.get("Client-ID");
            String accessToken = dotenv.get("Access-Token");

            IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
            wrapper.setCredentials(clientId, accessToken);


            byte[] bytes = wrapper.apiProtoRequest(Endpoints.GAMES, query);
            return GameResult.parseFrom(bytes).getGamesList();

        } catch (RequestException e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
            System.out.printf("Failed request. Did you setup Client ID and Access Token?");
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Do you have correct .env file?");
        }

        return null;
    }
}
