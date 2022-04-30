package com.example.goodtaste;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class NotificationIntentService extends IntentService {

    //this the channel ID for notification management
    private static final int NOTIFICATION_ID = 3;

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder noBuilder = new Notification.Builder(this);
        //todo need to change the notification title
        noBuilder.setContentTitle("New Recipe is added");
        noBuilder.setSmallIcon(R.drawable.ic_food);


        Intent noIntent1 = new Intent(this, NavDrawerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, noIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

        //this connects between the notification builder and the pending intent
        noBuilder.setContentIntent(pendingIntent);

        Notification notification = noBuilder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notification);

        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            noBuilder.setChannelId(channelId);
        }

        noBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }
}