package com.sakhhome.lession.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sakhhome.lession.R;
import com.sakhhome.lession.adaptors.UserAdaptor;
import com.sakhhome.lession.models.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListObjectFragment extends Fragment {

    private List<User> listUser;
    private Button btnAddObject;
    private Button btnRemoveObject;

    private UserAdaptor userAdaptor;

    private User selectUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_object, null);

        btnAddObject = v.findViewById(R.id.btnAddObject);
        btnAddObject.setOnClickListener(btnAddObject_click);

        btnRemoveObject = v.findViewById(R.id.btnRemoveObject);
        btnRemoveObject.setOnClickListener(btnRemoveObject_click);

        ListView listViewObject = v.findViewById(R.id.listViewObject);
        listUser = initUserList();

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        userAdaptor = new UserAdaptor(listUser, layoutInflater);
        listViewObject.setAdapter(userAdaptor);

        // прослушка событий листа
        listViewObject.setOnItemClickListener(adaptor_click_item);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<User> initUserList(){
        List<User> users = new ArrayList<>();
        users.add(new User("Dark", "dark@mail.com", 22));
        users.add(new User("Nikolas", "nik_231@mail.com", 33));
        users.add(new User("Yalya", "yalka1@mail.com", 23));
        return users;
    }

    AdapterView.OnItemClickListener adaptor_click_item = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectUser = listUser.get(i);
            Toast.makeText(getActivity(), "Выбран: " + selectUser.getName(), Toast.LENGTH_SHORT).show();

            TextView name = view.findViewById(R.id.userName);
            TextView email = view.findViewById(R.id.userEmail);
            TextView age = view.findViewById(R.id.userAge);

            Log.d("LISTOBJECT", name.getText() + " " + email.getText() + " " + age.getText());
        }
    };

    View.OnClickListener btnAddObject_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listUser.add(new User("Test", "test@mail.com", 21));
            userAdaptor.notifyDataSetChanged();
        }
    };

    View.OnClickListener btnRemoveObject_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(selectUser != null){
                listUser.remove(selectUser);
                selectUser = null;
            }
            else if(listUser.size()> 0) {
                listUser.remove(listUser.size()-1);
            }
            userAdaptor.notifyDataSetChanged();
        }
    };
}
