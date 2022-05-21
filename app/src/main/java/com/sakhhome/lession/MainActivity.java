package com.sakhhome.lession;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sakhhome.lession.activities.BindServiceActivity;
import com.sakhhome.lession.activities.BinderListActivity;
import com.sakhhome.lession.activities.ListCheckedActivity;
import com.sakhhome.lession.activities.ListMultiCheckedActivity;
import com.sakhhome.lession.activities.ListObjectActivity;
import com.sakhhome.lession.activities.ListTreeActivity;
import com.sakhhome.lession.activities.NotificationServiceActivity;
import com.sakhhome.lession.activities.PrefActivity;
import com.sakhhome.lession.activities.SaveStateActivity;
import com.sakhhome.lession.activities.ServiceActivity;
import com.sakhhome.lession.activities.ServiceBroadcastActivity;
import com.sakhhome.lession.activities.SimpleAdaptorActivity;
import com.sakhhome.lession.activities.SpinnerActivity;
import com.sakhhome.lession.activities_bd.BDactivity;
import com.sakhhome.lession.fragments.AboutFragment;
import com.sakhhome.lession.services.MyService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowListTree = findViewById(R.id.btnShowListTree);
        btnShowListTree.setOnClickListener(btnShowListTree_click);

        Button btnShowServices = findViewById(R.id.btnShowServices);
        btnShowServices.setOnClickListener(btnShowServices_click);

        Button btnShowServicesBroadcast = findViewById(R.id.btnShowServicesBroadcast);
        btnShowServicesBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ServiceBroadcastActivity.class));
            }
        });

        Button btnShowServicesBind = findViewById(R.id.btnShowServicesBind);
        btnShowServicesBind.setOnClickListener(btnShowServicesBind_click);

        Button btnNotifiService = findViewById(R.id.btnNotifiService);
        btnNotifiService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NotificationServiceActivity.class));
            }
        });

        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), BDactivity.class));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(getBaseContext(), "Setting", Toast.LENGTH_LONG).show();
                return true;
            case R.id.about:
                Fragment fragment = getFragmentManager().findFragmentByTag(AboutFragment.TAG);

                if (fragment == null) {
                    getFragmentManager().beginTransaction().add(R.id.frameBlock, new AboutFragment(), AboutFragment.TAG).commit();
                } else {
                    getFragmentManager().beginTransaction()  .remove(fragment).commit();
                }
                return true;
            case R.id.site:
                Intent intent = new Intent(this, SiteActivity.class);
                startActivity(intent);
                return true;
            case R.id.list:
                startActivity(new Intent(this, ListActivity.class));
                return true;
            case R.id.listObject:
                startActivity(new Intent(this, ListObjectActivity.class));
                return true;
            case R.id.listChose:
                startActivity(new Intent(this, ListCheckedActivity.class));
                return true;
            case R.id.listMultiChose:
                startActivity(new Intent(this, ListMultiCheckedActivity.class));
                return true;
            case R.id.listSimpleAdaptor:
                startActivity(new Intent(this, SimpleAdaptorActivity.class));
                return true;
            case R.id.binderList:
                startActivity(new Intent(this, BinderListActivity.class));
                return true;
            case R.id.spinner:
                startActivity(new Intent(this, SpinnerActivity.class));
                return true;
            case R.id.saveStateCount:
                startActivity(new Intent(this, SaveStateActivity.class));
                return  true;
            case R.id.mnuPreferences:
                startActivity(new Intent(this, PrefActivity.class));
                return true;
        }
        return false;
    }

    View.OnClickListener btnShowServicesBind_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), BindServiceActivity.class));
        }
    };

    View.OnClickListener btnShowListTree_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), ListTreeActivity.class));
        }
    };

    View.OnClickListener btnShowServices_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(view.getContext(), ServiceActivity.class));
        }
    };
}
