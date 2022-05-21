package com.sakhhome.lession.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.sakhhome.lession.MainActivity;
import com.sakhhome.lession.R;
import com.sakhhome.lession.activities.NotificationServiceActivity;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

public class ServiceNotification extends Service {

    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        Log.d("TEST", "onCreate");
        super.onCreate();
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TEST", "onStartCommand");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification() {
        Log.d("TEST", "sendNotification");
        Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();

//        Notification.Builder builder = new Notification.Builder(this);
//
//        Intent intent = new Intent(this, NotificationServiceActivity.class);
//        intent.putExtra(NotificationServiceActivity.FILE_NAME, "Параметр какой-то!");
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, NotificationServiceActivity.REQUEST_CODE, intent, 0);
//
//        builder.setContentIntent(pendingIntent)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true);
//
//        Notification notification = builder.build();
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//
//        notificationManager.notify(1, notification);
        Log.d("TEST", "sendNotification OK!");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
