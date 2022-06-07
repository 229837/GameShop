package com.app.gameshop.dbmanager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class DBManager  {

    protected void onCreate() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("ameshop-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

    }

}
