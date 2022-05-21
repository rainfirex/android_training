package com.sakhhome.lession.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sakhhome.lession.R;
import com.sakhhome.lession.models.User;

import java.util.List;

public class UserAdaptor extends BaseAdapter {
    private List<User> users;
    private LayoutInflater inflater;

    public UserAdaptor(List<User> users, LayoutInflater inflater){
        this.users = users;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.user_list_view, viewGroup, false);
        }
        User user = (User)getItem(i);
        TextView name = view.findViewById(R.id.userName);
        TextView email = view.findViewById(R.id.userEmail);
        TextView age = view.findViewById(R.id.userAge);

        name.setText("Имя: " +user.getName().toString() + " ID: "+ user.getId());
        email.setText("Почта: " +user.getEmail());
        age.setText("Возраст: " + user.getAge());

        return view;
    }
}
