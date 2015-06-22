package com.example.zumoappname;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.microsoft.windowsazure.notifications.NotificationsHandler;

/**
 * Created by kdandang on 4/29/2015.
 */
public class MyHandler extends NotificationsHandler {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    Context ctx;

    /**
     * Method called after a notification is received.
     * By default, it shows a toast with the value asociated to the "message" key in the bundle
     *
     * @param context Application Context
     * @param bundle  Bundle with notification data
     */
    @Override
    public void onReceive(Context context, Bundle bundle) {
        super.onReceive(context, bundle);
        ctx = context;
        String nhMessage = bundle.getString("message");

        Log.d("onReceive", "Notification Received");
        sendNotification(nhMessage);
    }

    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, ToDoActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("Notification Hub Demo")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    /**
     * Method called after the device is registered for notifications
     *
     * @param context           Application context
     * @param gcmRegistrationId Google Cloud Messaging registration id
     */
    @Override
    public void onRegistered(Context context, final String gcmRegistrationId) {

        Log.d("onRegistered", "calling the super class");
        super.onRegistered(context, gcmRegistrationId);

        Log.d("onRegistered", "gcmRegID :" + gcmRegistrationId);
        new AsyncTask<Void,Void,Void>() {

            protected Void doInBackground(Void... params) {
                try {
                    Log.d("doInBackgrnd", "trying to register the MobileServiceClient.push with gcmRegID");
                    ToDoActivity.mClient.getPush().register(gcmRegistrationId, null);
                    Log.d("doInBackgrnd","Push registered successfully");
                    return null;
                } catch(Exception e) {
                    // handle error
                    Log.d("doInBackgrnd", "Exception: " + e.toString());
                }
                return null;
            }

        }.execute();
        Log.d("onRegistered","AsyncTask executed");
    }

}
