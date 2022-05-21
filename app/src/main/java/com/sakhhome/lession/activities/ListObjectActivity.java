package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;

import com.sakhhome.lession.R;
import com.sakhhome.lession.fragments.ListObjectFragment;

import androidx.annotation.Nullable;

public class ListObjectActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_object);
        initFragment();
    }

    public void initFragment(){
        getFragmentManager().beginTransaction().add(R.id.frameListObject, new ListObjectFragment()).commit();
    }
}
