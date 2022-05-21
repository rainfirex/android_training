package com.sakhhome.lession.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sakhhome.lession.R;

import androidx.annotation.Nullable;

public class ListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        ListView list = v.findViewById(R.id.listView); //getActivity().findViewById(R.id.list);
        String[] array = {"one", "two", "three", "four", "five", "six"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity().getBaseContext(), "Test", Toast.LENGTH_LONG).show();

    }
}
