package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class ListCheckedActivity extends Activity {

    private ListView listView;
    private String[] array = {"one", "two", "three", "four", "five", "six"};
    private Button btnChoseInfo;
    private TextView txtChose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_checked);

        txtChose = findViewById(R.id.txtChose);

        btnChoseInfo = findViewById(R.id.btnChoseInfo);
        btnChoseInfo.setOnClickListener(btnChose_click);

        listView = findViewById(R.id.listChecked);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, array);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Одиночный выбор
    }

    View.OnClickListener btnChose_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            txtChose.setText("Выбрано: " + array[listView.getCheckedItemPosition()]);
        }
    };
}
