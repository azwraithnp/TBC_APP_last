package com.example.b50i7d.tbcapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by B50i7D on 4/7/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationCompat.Builder notificaitonBuilder = new NotificationCompat.Builder(this);
        notificaitonBuilder.setContentTitle("TBC APP");
        //notificaitonBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificaitonBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()))
                .setPriority(Notification.PRIORITY_MAX);
        notificaitonBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));
        notificaitonBuilder.setAutoCancel(true);
        notificaitonBuilder.setSmallIcon(R.mipmap.ic_launcher);
        PendingIntent pendingIntent = PendingIntent.getActivities(this,0, new Intent[]{intent.putExtra("notification",remoteMessage.getNotification().getBody())},PendingIntent.FLAG_ONE_SHOT);
        notificaitonBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificaitonBuilder.build());
    }
}
