package com.gitstudy.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gitstudy.R;

import java.util.ArrayList;

public class ListViewAddDeleteItemActivity extends AppCompatActivity {
    public static ListViewAddDeleteItemActivity mInstance = null;
    private Context context;
    private ListView listView;
    private ArrayList<String> cities;
    private AddDeleteItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_add_delete_item);
        initView();

    }



    private void initView() {
        mInstance = this;
        context = this;
        listView = (ListView) findViewById(R.id.list_add_delete);
        cities = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            cities.add("上海" + i);
        }
        //快速滑动条
//        listView.setFastScrollEnabled(true);
        adapter = new AddDeleteItemAdapter(context,cities);
        listView.setAdapter(adapter);

    }


}
