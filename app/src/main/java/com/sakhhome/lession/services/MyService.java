package com.sakhhome.lession.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.sakhhome.lession.activities.ServiceActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;


public class MyService extends Service {

    private final String TAG = "service";
    private ExecutorService executorService;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service work start");
        executorService = Executors.newFixedThreadPool(2); // Потоки будут запускаться по одному!!!!!
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service start command");

        int time = intent.getIntExtra(ServiceActivity.PARAM_TIME, 0);
        PendingIntent pi = intent.getParcelableExtra(ServiceActivity.PARAM_PINTENT);

        MyRunnable myRunnable = new MyRunnable(time, startId, pi);
        executorService.execute(myRunnable);


//        task();
        return super.onStartCommand(intent, flags, startId);
    }

    private void task() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 5; i++) {
                    Log.d(TAG, "Task: i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(); // Самоостанавливаеться
            }
        }).start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: service work stop");
    }

    private class MyRunnable implements Runnable {

        private int timeWait;
        private int startId;
        private PendingIntent pi;

        public MyRunnable(int timeWait, int startId, PendingIntent pi) {
            this.timeWait = timeWait;
            this.startId = startId;
            this.pi = pi;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    Log.d(TAG, "Task: i = " + i);

                    // Сообщаем о старте задачи
                    pi.send(ServiceActivity.STATUS_START);

                    TimeUnit.SECONDS.sleep(timeWait);

                    pi.send(MyService.this, ServiceActivity.STATUS_UPDATE, new Intent().putExtra(ServiceActivity.PARAM_RESULT, i));

                }

                pi.send(ServiceActivity.STATUS_FINISH);
                pi.send(MyService.this, ServiceActivity.STATUS_FINISH, new Intent().putExtra(ServiceActivity.PARAM_RESULT, 55));


                stop(startId); // Самоостанавливаеться
            } catch (InterruptedException | PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

        private void stop(int startId) {
            stopSelf(startId);
        }
    }
}
