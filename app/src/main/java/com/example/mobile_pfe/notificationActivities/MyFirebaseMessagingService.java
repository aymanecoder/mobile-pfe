package com.example.mobile_pfe.notificationActivities;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d("FCM Token", "Refreshed token: " + token);
        // Vous pouvez envoyer le token à votre backend ici si nécessaire
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Gérez les messages reçus ici
    }
}

