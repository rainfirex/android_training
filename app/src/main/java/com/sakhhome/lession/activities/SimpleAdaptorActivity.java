package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sakhhome.lession.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;

public class SimpleAdaptorActivity extends Activity {

    private String[] values = {"text1", "text2", "text3", "text4", "text5"};
    private boolean[] checked = {false, true, false, true, false};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adaptor);


        List<Map<String, Object>> valuesMapList = new ArrayList<>();
        Map<String, Object> currentMap;

        int img = R.mipmap.ic_launcher_round;

        for (int i = 0; i < values.length; i++){
            currentMap = new HashMap<>();
            currentMap.put("text", values[i]);
            currentMap.put("check", checked[i]);
            currentMap.put("img", img);

            valuesMapList.add(currentMap);
        }

        String[] fromKey = {"text", "check", "img"};
        int[] to = {R.id.txtSimpleAdaptor, R.id.chkSimpleAdaptor, R.id.imgSimpleAdaptor};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, valuesMapList, R.layout.simple_adaptor_list_view, fromKey, to);

        ListView listSimpleAdaptor = findViewById(R.id.listSimpleAdaptor);
        listSimpleAdaptor.setAdapter(simpleAdapter);
    }
}
