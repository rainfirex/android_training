package com.sakhhome.vehicle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class MainAppActivity extends Activity implements View.OnClickListener {

    Button btnAddVehicle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        btnAddVehicle = findViewById(R.id.btnAddVehicle);
        btnAddVehicle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddVehicle:
                openAddVehicleActivity();
                break;
        }
    }

    private void openAddVehicleActivity(){
        startActivity(new Intent(this, AddVehicleActivity.class));
    }
}
