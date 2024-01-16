
package com.example.mobile_pfe.notificationActivities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.mobile_pfe.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;

public class FriendRequestActivity extends AppCompatActivity {

    private static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 1;
    private Button inviteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list_item);

        inviteButton = findViewById(R.id.invite_button);
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if permission is granted
                if (ActivityCompat.checkSelfPermission(FriendRequestActivity.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted, show the notification
                    showNotification();
                } else {
                    // Permission is not granted, request the permission
                    ActivityCompat.requestPermissions(FriendRequestActivity.this, new String[]{Manifest.permission.VIBRATE}, NOTIFICATION_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    private void showNotification() {
        // Check if the VIBRATE permission is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, proceed with showing the notification
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            String fcmToken = task.getResult();
                            // Utilize fcmToken to send a notification
                            createNotificationWithToken(fcmToken);

                            Log.d("FCM Token", fcmToken);
                        } else {
                            Log.e("FCM Token", "Erreur lors de la récupération du token", task.getException());
                        }
                    });
        } else {
            // Permission is not granted, request the VIBRATE permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, NOTIFICATION_PERMISSION_REQUEST_CODE);
        }
    }

// Reste du code...

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            // Check if the VIBRATE permission has been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, show the notification
                showNotification();
            } else {
                // Permission denied, handle accordingly (e.g., show an error message)
            }
        }
    }

    private void createNotificationWithToken(String fcmToken) {
        // Customize the notification using fcmToken
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("invite_channel", "Invite Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel for sending invite notifications");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "invite_channel")
                .setSmallIcon(R.drawable.icon_person)
                .setContentTitle("Friend Request")
                .setContentText("You have received a friend request.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intent = new Intent(this, notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("screen", "notification");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }



}
