package com.sakhhome.lession.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sakhhome.lession.R;
import com.sakhhome.lession.services.MyService;

import androidx.annotation.Nullable;

public class ServiceActivity extends Activity {

    private final int TASK_CODE1 = 1;
    private final int TASK_CODE2 = 2;
    private final int TASK_CODE3 = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_UPDATE = 150;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    private TextView servTextView1;
    private TextView servTextView2;
    private TextView servTextView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        servTextView1 = findViewById(R.id.servTextView1);
        servTextView1.setText("Task1");

        servTextView2 = findViewById(R.id.servTextView2);
        servTextView2.setText("Task2");

        servTextView3 = findViewById(R.id.servTextView3);
        servTextView3.setText("Task3");

        Button btnStartService = findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(btnStartService_click);

        Button btnStopService = findViewById(R.id.btnStopService);
        btnStopService.setOnClickListener(btnStopService_click);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int result = data.getIntExtra(PARAM_RESULT, 0);


        if (resultCode == STATUS_START) {
            switch (requestCode){
                case TASK_CODE1:
                    servTextView1.setText("Task1 start");
                    break;
                case TASK_CODE2:
                    servTextView2.setText("Task2 start" );
                    break;
                case TASK_CODE3:
                    servTextView3.setText("Task3 start");
                    break;
            }
        }

        if (resultCode == STATUS_UPDATE) {
            switch (requestCode){
                case TASK_CODE1:
                    servTextView1.setText("Task1 i = "+ result);
                    break;
                case TASK_CODE2:
                    servTextView2.setText("Task2 i = " + result);
                    break;
                case TASK_CODE3:
                    servTextView3.setText("Task3 i = "+ result);
                    break;
            }
        }

        if (resultCode == STATUS_FINISH){
            switch (requestCode){
                case TASK_CODE1:
                    servTextView1.setText("Task1 end " + result);
                    break;
                case TASK_CODE2:
                    servTextView2.setText("Task2 end " + result);
                    break;
                case TASK_CODE3:
                    servTextView3.setText("Task3 end " + result);
                    break;
            }
        }
    }

    View.OnClickListener btnStartService_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PendingIntent pi;

            Intent intent = new Intent(view.getContext(), MyService.class);

            pi = createPendingResult(TASK_CODE1, intent,0);
            intent.putExtra(PARAM_TIME, 1).putExtra(PARAM_PINTENT, pi);
            startService(intent);


            pi = createPendingResult(TASK_CODE2, intent,0);
            intent.putExtra(PARAM_TIME, 2).putExtra(PARAM_PINTENT, pi);
            startService(intent);


            pi = createPendingResult(TASK_CODE3, intent,0);
            intent.putExtra(PARAM_TIME, 2).putExtra(PARAM_PINTENT, pi);
            startService(intent);

            //startService(new Intent(view.getContext(), MyService.class).putExtra("time_wait", 2).putExtra("param", "string"));
        }
    };

    View.OnClickListener btnStopService_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopService(new Intent(view.getContext(), MyService.class));
        }
    };
}
