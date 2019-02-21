package com.iprismech.alertnikkiresidence.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.SplashScreenActivity;


import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessageService";
    Bitmap bitmap;
    private Intent intent;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        //
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        //The message which i send will have keys named [message, image, AnotherActivity] and corresponding values.
        //You can change as per the requirement.

        //message will contain the Push Message
//         String message = remoteMessage.getData().get("message");
//        String message = remoteMessage.getNotification().getBody();


        Map<String, String> msg = remoteMessage.getData();
//        String msgbody = msg.get(0);
        String msgbody  = remoteMessage.getData().toString();
//        Log.d("msg_data", msg.get("message"));
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (msgbody.equalsIgnoreCase("Services person is coming check alerts")) {
            intent = new Intent(this, SplashScreenActivity.class);
//            intent.putExtra("key", "visitors");
        /*} else {
            intent = new Intent(this, SplashScreenActivity.class);
            intent.putExtra("key", "alerts");
        }*/

//        Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("key", "alerts");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder notificationbuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(msgbody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setFullScreenIntent(pendingIntent, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getApplicationContext().getString(R.string.default_notification_channel_id);
            NotificationChannel channel = new NotificationChannel(channelId, msg.get("message"), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(msg.get("message"));
            notificationManager.createNotificationChannel(channel);
            notificationbuilder.setChannelId(channelId);
        }
        notificationManager.notify(0, notificationbuilder.build());
        //  }
        //imageUri will contain URL of the image to be displayed with Notification
        // String imageUri = remoteMessage.getData().get("image");
        //If the key AnotherActivity has  value as True then when the user taps on notification, in the app AnotherActivity will be opened.
        //If the key AnotherActivity has  value as False then when the user taps on notification, in the app MainActivity will be opened.
        //  String TrueOrFlase = remoteMessage.getData().get("AnotherActivity");

        //To get a Bitmap image from the URL received
        //  bitmap = getBitmapfromUrl(imageUri);

        //sendNotification(message);

    }


    /**
     * Create and show a simple notification containing the received FCM message.
     */

}
