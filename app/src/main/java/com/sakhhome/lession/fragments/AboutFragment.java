package com.sakhhome.lession.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class AboutFragment extends Fragment {
    public static final String TAG = "About";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, null);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
