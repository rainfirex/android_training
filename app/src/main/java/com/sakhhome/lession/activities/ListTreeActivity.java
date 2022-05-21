package com.sakhhome.lession.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.sakhhome.lession.R;
import com.sakhhome.lession.models.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import androidx.annotation.Nullable;

public class ListTreeActivity extends Activity {

    private List<Car> cars;
    private Set<String> markSet;

    // Коллекция для групп
    private List<Map<String, String>> groupData;

    // Общая коллекция для коллекций элементов
    private List<List<Map<String, String>>> childData;

    // Список атрибутов группы или элемента
    private Map<String, String> currentMap;

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tree);

        initList();
        initSet();

        //Заполнить коллекцию группы из массива с названиеми групп
        groupData = new ArrayList<Map<String, String>>();

        for (String group : markSet) {
            currentMap = new HashMap<String, String>();
            currentMap.put("groupName", group);
            groupData.add(currentMap);
        }

        // Список атрибутов для чтения
        String[] groupFrom = new String[] {"groupName"};
        // Список ID view элементов, в которые будет помещены атрибуты групп
        int[] groupTo = new int[]{ android.R.id.text1 };

        // Создаем коллекцию для коллекций элементов
        childData = initChildData();

        // Список атрибутов элементов для чтения
        String[] childFrom = new String[]{"carName"};
        int[] childTo = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        expandableListView = findViewById(R.id.expandableListView);
        expandableListView.setAdapter(adapter);
    }

    private List<List<Map<String, String>>> initChildData() {
        List<java.util.List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (String mark : markSet) {
            List<Map<String, String>> childDataItem = new ArrayList<>();

            for (Car c : cars){
                if (c.getMark().equals(mark)) {
                    Map<String, String> modelMap = new HashMap<>();
                    modelMap.put("carName", c.getModel());
                    childDataItem.add(modelMap);
                }
            }
            childData.add(childDataItem);
        }

        return childData;
    }

    private void initSet() {
        markSet = new TreeSet<>();
        for (Car c : cars){
            markSet.add(c.getMark());
        }
    }

    private void initList() {
        cars = new ArrayList<>();
        cars.add(new Car("Porsche", "Panamera turbo"));
        cars.add(new Car("Porsche", "Cayenne S"));
        cars.add(new Car("Masseratti", "Quattroporte"));
        cars.add(new Car("Audi", "A5"));
        cars.add(new Car("Audi", "R8"));
        cars.add(new Car("BMW", "X5"));
        cars.add(new Car("BMW", "M3"));
        cars.add(new Car("BMW", "750Li"));
    }
}
