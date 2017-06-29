package com.gitstudy.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.gitstudy.R;

import java.util.ArrayList;

public class ListDemoActivity extends Activity implements View.OnClickListener,
        MessageAdapter.DeleteCallback {

    private ListView lv;
    private Button add;
    ArrayList<String> data = new ArrayList<String>();
    int count;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);
        lv = (ListView) findViewById(R.id.lv);
        add = (Button) findViewById(R.id.add);
        adapter = new MessageAdapter(this);
        adapter.setData(data);
        adapter.setDeleteCallback(this);
        lv.setAdapter(adapter);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        count = count + 1;
        data.add("A" + count);
        adapter.setData(data);
        adapter.notifyDataSetInvalidated();

    }

    @Override
    public void deletePosition(int position) {
        data.remove(position);
        adapter.setData(data);
        adapter.notifyDataSetInvalidated();
    }
}
