package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sakhhome.lession.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SaveStateActivity extends Activity {

    int count = 0;
    TextView txtCount;
    Button btnAddCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_state);

        txtCount = findViewById(R.id.txtCount);
        txtCount.setText("Count: " + this.count);

        btnAddCount = findViewById(R.id.btnAddCount);
        btnAddCount.setOnClickListener(btnAddCount_click);
    }

    /**
     * Сохраняем данные активити
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    /**
     * Восстановить данные
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        txtCount.setText("Count: " + this.count);
    }

    View.OnClickListener btnAddCount_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count++;
            txtCount.setText("Count: " + count);
        }
    };
}
