package com.example.project_ss_b;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;


public class PushNote {

    NotificationManager notifManager;
    Notification notify;
    Context context;

    public PushNote(Context context, String title, String content){

        this.context = context;
        //Creating notification manager to show the notification.
        notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Building the notification, defining title, text and image.
        notify = new Notification.Builder(context.getApplicationContext()).setContentTitle(title).setContentText(content).
                setContentTitle(title).setSmallIcon(R.drawable.ic_launcher_background).build();

        //Assigns a flag to notify that will cancel the notification when it is clicked by the user.
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
    }

    public void Display(){

        //Display the notification.
        notifManager.notify(0, notify);
    }
}
