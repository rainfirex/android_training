package com.sakhhome.lession.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.sakhhome.lession.activities.ServiceBroadcastActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

public class MyServiceBroadcast extends Service {

    private final String TAG = "service broadcast";

    private ExecutorService executor;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service broadcast work start");
        executor = Executors.newFixedThreadPool(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: service broadcast");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service command start");

        int time = intent.getIntExtra(ServiceBroadcastActivity.PARAM_TIME, 0);
        int task = intent.getIntExtra(ServiceBroadcastActivity.PARAM_TASK, 0);

        MyRunnable runnable = new MyRunnable(time, startId, task);
        executor.execute(runnable);

        return super.onStartCommand(intent, flags, startId);
    }

    private class MyRunnable implements Runnable {

        private int timeWait;
        private int startId;
        private int task;

        public MyRunnable(int timeWait, int startId, int task) {
            this.timeWait = timeWait;
            this.startId = startId;
            this.task = task;
            Log.d(TAG, "Create MyRunnable: " + startId);
        }

        @Override
        public void run() {
            Intent intent = new Intent(ServiceBroadcastActivity.BROADCASE_ACTION);
            Log.d(TAG, "Start: " + timeWait);

            try {
                intent.putExtra(ServiceBroadcastActivity.PARAM_TASK, task);
                intent.putExtra(ServiceBroadcastActivity.PARAM_STATUS, ServiceBroadcastActivity.STATUS_START);
                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(timeWait);

                intent.putExtra(ServiceBroadcastActivity.PARAM_STATUS, ServiceBroadcastActivity.STATUS_FINISH);
                intent.putExtra(ServiceBroadcastActivity.PARAM_RESULT, timeWait + 100);
                sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop(startId); // Самоостанавливаеться
        }

        private void stop(int startId) {
            stopSelf(startId);
        }
    }
}
