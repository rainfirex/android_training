package com.sakhhome.lession.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sakhhome.lession.R;
import com.sakhhome.lession.services.MyServiceBind;

import androidx.annotation.Nullable;

public class BindServiceActivity extends Activity implements View.OnClickListener {

    private final static String TAG = "bindActivity";

    private boolean isBind = false;
    private Intent intent;
    private ServiceConnection serviceConnection;

    private MyServiceBind myServiceBind;
    private TextView txtBindInterval;
    private long interval;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind);

        Button btnStartBindService = findViewById(R.id.btnStartBindService);
        Button btnStopBindService  = findViewById(R.id.btnStopBindService);
        Button btnBind   = findViewById(R.id.btnBind);
        Button btnUnbind = findViewById(R.id.btnUnbind);
        Button btnBindUP = findViewById(R.id.btnBindUP);
        Button btnUnbindDown = findViewById(R.id.btnUnbindDown);

        txtBindInterval = findViewById(R.id.txtBindInterval);

        btnStartBindService.setOnClickListener(this);
        btnStopBindService.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);

        intent = new Intent(this, MyServiceBind.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "onServiceConnected");

                myServiceBind = ((MyServiceBind.MyBinder) iBinder).getServiceBind();
                isBind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "onServiceDisconnected");
                isBind = false;
            }
        };
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStartBindService:
                startService(intent);
                break;
            case R.id.btnStopBindService:
                stopService(intent);
                break;
            case R.id.btnBind:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                isBind = true;
                break;
            case R.id.btnUnbind:
                if(isBind) {
                    unbindService(serviceConnection);
                    isBind = false;
                }
                break;
            case R.id.btnBindUP:
                if(isBind){
                    Log.d(TAG, "UP");
                    interval = myServiceBind.upInterval(500);
                    txtBindInterval.setText("interval = " + interval);
                }
                break;
            case R.id.btnUnbindDown:
                if(isBind){
                    Log.d(TAG, "DOWN");
                    interval = myServiceBind.downInterval(500);
                    txtBindInterval.setText("interval = " + interval);
                }
                break;
        }
    }
}
