package com.sakhhome.lession.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sakhhome.lession.R;
import com.sakhhome.lession.services.MyServiceBroadcast;

import androidx.annotation.Nullable;

public class ServiceBroadcastActivity extends Activity implements View.OnClickListener {
    private final String TAG = "broadcast";

    private final int TASK_CODE1 = 100;
    private final int TASK_CODE2 = 200;
    private final int TASK_CODE3 = 300;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCASE_ACTION = "com.sakhhome";

    private TextView servBTextView1;
    private TextView servBTextView2;
    private TextView servBTextView3;

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_broadcast);

        servBTextView1 = findViewById(R.id.servBTextView1);
        servBTextView1.setText("Task1");

        servBTextView2 = findViewById(R.id.servBTextView2);
        servBTextView2.setText("Task2");

        servBTextView3 = findViewById(R.id.servBTextView3);
        servBTextView3.setText("Task3");

        Button btnBroadcast = findViewById(R.id.btnBroadcast);
        btnBroadcast.setOnClickListener(this);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_STATUS, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(TAG, "onReceive - task:" + task + " status: "+ status);


                if(status == STATUS_START){
                    switch (task){
                        case TASK_CODE1:
                            servBTextView1.setText("Task1 start");
                            break;
                        case TASK_CODE2:
                            servBTextView2.setText("Task2 start");
                            break;
                        case TASK_CODE3:
                            servBTextView3.setText("Task3 start");
                            break;
                    }
                }

                if (status == STATUS_FINISH){
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task){
                        case TASK_CODE1:
                            servBTextView1.setText("Task1 finish, result = "+ result);
                            break;
                        case TASK_CODE2:
                            servBTextView2.setText("Task2 finish, result = "+result);
                            break;
                        case TASK_CODE3:
                            servBTextView3.setText("Task3 finish, result = "+result);
                            break;
                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCASE_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "!!!!", Toast.LENGTH_SHORT).show();

        startService(new Intent(view.getContext(), MyServiceBroadcast.class).putExtra(PARAM_TIME, 3).putExtra(PARAM_TASK, TASK_CODE1));

        startService(new Intent(view.getContext(), MyServiceBroadcast.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK_CODE2));

        startService(new Intent(view.getContext(), MyServiceBroadcast.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK_CODE3));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
