package com.app.gameshop.igdbconnection;

import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import com.app.gameshop.model.Genre;
import com.google.protobuf.InvalidProtocolBufferException;
import io.github.cdimascio.dotenv.Dotenv;
import proto.Game;
import proto.GameResult;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;



public class IGDBConnection {


    public static void getTop10GamesAllTime() {
        var listOfGames = queryGamesRequest("fields name, total_rating, first_release_date; where category=0 & total_rating_count>1000; sort total_rating desc; limit 10;");
        if (listOfGames == null) return;
        for (var game : listOfGames) {
            LocalDate date = Instant
                    .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                    .atZone(ZoneId.of("GMT+1")).toLocalDate();

            System.out.println(game.getName() + " " + game.getTotalRating() + " " + date.getYear());
        }
    }

    public static void getGame(String gameName) {
        String query = String.format("fields name, total_rating, first_release_date; where name ~ \"%s\"; sort total_rating desc; limit 5;", gameName);
        var listOfGames = queryGamesRequest(query);

        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        } else {
            var game = listOfGames.get(0);
            LocalDate date = Instant
                    .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                    .atZone(ZoneId.of("GMT+1")).toLocalDate();

            System.out.println(game.getName() + " " + game.getTotalRating() + " " + date.getYear());
        }
    }

    public static void getBestGamesByGenre(Genre genre, int count) {
        int genreId = genreToIgdbId(genre);
        String query = String.format("fields name, total_rating, first_release_date; where genres = %d & total_rating > 1; sort total_rating desc; limit %d;", genreId, count);

        var listOfGames = queryGamesRequest(query);

        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        } else {
            for (var game : listOfGames) {
                LocalDate date = Instant
                        .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                        .atZone(ZoneId.of("GMT+1")).toLocalDate();

                System.out.println(game.getName() + " " + game.getTotalRating() + " " + date.getYear());
            }
        }
    }

    public static List<Game> getUpcomingGames(int count) {
        long time = System.currentTimeMillis() / 1000;
        String query = String.format("f name, first_release_date; w first_release_date > %d; sort first_release_date asc; limit %d;", time, count);

        var listOfGames = queryGamesRequest(query);
        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        } else {
            for (var game : listOfGames) {
                LocalDate date = Instant
                        .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                        .atZone(ZoneId.of("GMT+1")).toLocalDate();

                System.out.println(game.getName() + " " + date);
            }
        }

        return listOfGames;
    }

    public static List<Game> getUpcomingGamesByGenre(Genre genre, int count) {
        long time = System.currentTimeMillis() / 1000;
        int genreId = genreToIgdbId(genre);
        String query = String.format("f name, first_release_date; w first_release_date > %d & genres = %d; sort first_release_date asc; limit %d;", time, genreId, count);

        var listOfGames = queryGamesRequest(query);
        if (listOfGames == null || listOfGames.size() < 1) {
            System.out.println("No games found");
        } else {
            for (var game : listOfGames) {
                LocalDate date = Instant
                        .ofEpochSecond(game.getFirstReleaseDate().getSeconds(), game.getFirstReleaseDate().getNanos())
                        .atZone(ZoneId.of("GMT+1")).toLocalDate();

                System.out.println(game.getName() + " " + date);
            }
        }

        return listOfGames;
    }


    private static int genreToIgdbId(Genre genre) {
        return switch (genre) {
            //4; //Fighting
            case FPS -> 5; //Shooter
            //7; //Music
            //8; //Platform
            //9; //Puzzle
            //10; //Racing
            case STRATEGY -> 11; //Real Time Strategy (RTS)
            //12; //Role-playing (RPG)
            //13; //Simulator
            //14; //Sport
            default -> -1;
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
