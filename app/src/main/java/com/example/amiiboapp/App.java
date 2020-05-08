package com.example.amiiboapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "androidAlert1";

    @Override
    public void onCreate() {
        super.onCreate();
        //method to create alert
        createNotificationChannels();
    }

    //creates android notification
    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID, "Test Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is a notification Test");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

}
