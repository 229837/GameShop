package com.app.gameshop.igdbconnection;

import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import com.google.protobuf.InvalidProtocolBufferException;
import io.github.cdimascio.dotenv.Dotenv;
import proto.Game;
import proto.GameResult;

import java.util.List;



public class IGDBConnection {

    public static void TestMethod() {
        try {
            Dotenv dotenv = null;
            dotenv = Dotenv.configure().load();

            //setup these in .env file
            String clientId = dotenv.get("Client-ID");
            String accessToken = dotenv.get("Access-Token");

            IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
            wrapper.setCredentials(clientId, accessToken);

            //request and print 10 games witch rating above 70
            String json = wrapper.apiJsonRequest(Endpoints.GAMES, "fields name; where rating > 70; limit 10;");
            System.out.printf(json);


        } catch (RequestException e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
            System.out.printf("Failed request. Did you setup Client ID and Access Token?");
        }
    }
}
