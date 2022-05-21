package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.sakhhome.lession.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class BinderListActivity extends Activity {

    private final String ATTR_TEXT = "text";
    private final String ATTR_PB = "pb";
    private final String ATTR_LL = "ll";

    private ListView binderListView;


    private int load[] = {41, 48, 22 ,35, 30, 67, 51, 88};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);



        ArrayList<Map<String, Object>> data = new ArrayList<>(load.length);
        Map<String, Object> map;

        for(int i = 0; i < load.length; i++){
            map = new HashMap<String, Object>();
            map.put(ATTR_TEXT, "Day "+ (i + 1) + ". Load: " + load[i]+ "%");
            map.put(ATTR_PB, load[i]);
            map.put(ATTR_LL, load[i]);
            data.add(map);
        }

        String[] from = {ATTR_TEXT, ATTR_PB, ATTR_LL};
        int[] to = {R.id.binderText, R.id.binderProgressBar, R.id.binderLinearLayout};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.binder_progress, from, to);
        simpleAdapter.setViewBinder(new MyViewBinder());

        binderListView = findViewById(R.id.binderListView);
        binderListView.setAdapter(simpleAdapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder{

        int red = getResources().getColor(R.color.red);
        int yellow = getResources().getColor(R.color.yellow);
        int green = getResources().getColor(R.color.green);

        @Override
        public boolean setViewValue(View view, Object o, String s) {

            int i = 0;

            switch (view.getId()){
                case R.id.binderLinearLayout:
                    i = ((Integer)o).intValue();
                    if(i < 40) view.setBackgroundColor(green);
                    else if(i < 70) view.setBackgroundColor(yellow);
                    else view.setBackgroundColor(red);
                    return  true;
                case R.id.binderProgressBar:
                    i = ((Integer)o).intValue();
                    ((ProgressBar)view).setProgress(i);
                    return true;
            }

            return false;
        }
    }
}
