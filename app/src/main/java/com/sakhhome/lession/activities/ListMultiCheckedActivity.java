package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class ListMultiCheckedActivity extends Activity {
    private ListView listMultiChecked;
    private String[] array = {"one", "two", "three", "four", "five", "six"};
    private Button btnMultiChoseInfo;
    private TextView txtMultiChose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_checked);

        txtMultiChose = findViewById(R.id.txtMultiChose);

        btnMultiChoseInfo = findViewById(R.id.btnMultiChoseInfo);
        btnMultiChoseInfo.setOnClickListener(btnMultiChose_click);

        listMultiChecked = findViewById(R.id.listMultiChecked);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, array);
        listMultiChecked.setAdapter(adapter);
        listMultiChecked.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    View.OnClickListener btnMultiChose_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SparseBooleanArray positions = listMultiChecked.getCheckedItemPositions();

            String test = "Выбрано: ";
            for (int i = 0; i < positions.size(); i++) {
                int key = positions.keyAt(i);
                if(positions.get(key)){
                    test += array[key]+" ";
                }
            }
            txtMultiChose.setText(test);
        }
    };
}
