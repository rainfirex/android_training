package com.sakhhome.lession;

import android.app.Activity;
import android.os.Bundle;

import com.sakhhome.lession.fragments.ListFragment;

import androidx.annotation.Nullable;

public class ListActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initFragment();
    }

    public void initFragment(){
        getFragmentManager().beginTransaction().add(R.id.frameList, new ListFragment()).commit();
    }
}
