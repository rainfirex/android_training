package com.sakhhome.lession.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class MyServiceBind extends Service {

    private final String TAG = "binding";

    private MyBinder binder = new MyBinder();

    private Timer timer;
    private TimerTask timerTask;
    private long interval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        this.timer = new Timer();

        schedule();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return this.binder; //new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onBind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    private void schedule() {
        if (timerTask != null) timerTask.cancel();
        if (interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "Run");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    public long upInterval(long val){
        this.interval = this.interval + val;
        schedule();
        return this.interval;
    }

    public long downInterval(long val){
        this.interval = this.interval - val;
        if (interval < 0) interval = 0;
        schedule();
        return this.interval;
    }

    public class MyBinder extends Binder{
        public MyServiceBind getServiceBind(){
            return MyServiceBind.this;
        }
    }
}
