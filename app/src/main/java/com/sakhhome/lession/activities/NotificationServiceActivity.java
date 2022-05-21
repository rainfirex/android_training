package com.sakhhome.lession.activities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sakhhome.lession.R;
import com.sakhhome.lession.services.ServiceNotification;
import com.sakhhome.lession.services.ServicePlaySound;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class NotificationServiceActivity extends Activity implements View.OnClickListener {

    public static final String FILE_NAME = "filename";

    private boolean isTest = false;

    public static final int REQUEST_CODE = 891;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_notification);

        TextView txtNotificationTV = findViewById(R.id.txtNotificationTV);
        Button btnNotifiStart = findViewById(R.id.btnNotifiStart);
        btnNotifiStart.setOnClickListener(this);

        Button btnNotifiStop = findViewById(R.id.btnNotifiStop);
        btnNotifiStop.setOnClickListener(this);

        Button btnNotifiTEST = findViewById(R.id.btnNotifiTEST);
        btnNotifiTEST.setOnClickListener(this);

        Intent intent = getIntent();

        String filename = intent.getStringExtra(FILE_NAME);

        if (!TextUtils.isEmpty(filename)) {
            txtNotificationTV.setText(filename);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNotifiStart:
                Log.d("TEST", "btnNotifiStart");
                startService(new Intent(NotificationServiceActivity.this, ServiceNotification.class));
                Log.d("TEST", "!!!!!!!!");

                break;
            case R.id.btnNotifiStop:
                Log.d("TEST", "btnNotifiStop");
                stopService(new Intent(NotificationServiceActivity.this, ServiceNotification.class));
                Log.d("TEST", "!!!!!!!!");

                break;
            case R.id.btnNotifiTEST:


                if (isTest == false){
                    Log.d("TEST", "start");
                    startService(new Intent(this, ServicePlaySound.class));
                    isTest = true;
                }
                else{
                    Log.d("TEST", "stop");
                    stopService(new Intent(this, ServicePlaySound.class));
                    isTest = false;
                }

//                NotificationCompat.Builder builder =
//                        new NotificationCompat.Builder(this)
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("Title")
//                                .setContentText("Notification text");
//
//                Notification notification = builder.build();
//
//                NotificationManager notificationManager =
//                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(1, notification);


//                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//                Intent intent = new Intent(this, NotificationServiceActivity.class);
//                intent.putExtra(NotificationServiceActivity.FILE_NAME, "Параметр какой-то!");
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, NotificationServiceActivity.REQUEST_CODE, intent, 0);
//
//                Notification.Builder builder = new Notification.Builder(this);
//                builder.setContentIntent(pendingIntent)
//                        .setSmallIcon(R.mipmap.ic_launcher_round)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
//                        .setWhen(System.currentTimeMillis())
//                        .setAutoCancel(true);
//
//                Notification notification = builder.build();
//                notification.flags = Notification.FLAG_AUTO_CANCEL;
//
//
//                nm.notify(1124, notification);

                break;

        }
    }
}
