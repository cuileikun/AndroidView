package com.gitstudy.pulltorefreshtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gitstudy.R;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class SecListViewActivity extends AppCompatActivity implements PullToRefreshLayout.OnRefreshListener{
    private ListView listView;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_list_view);
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
//        ptrl.setOnRefreshListener(new MyListener());
        ptrl.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.content_view);
        View mFooterView = LayoutInflater.from(SecListViewActivity.this).inflate(R.layout.list_test_footer, null);
        listView.addFooterView(mFooterView);
        initListView();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 第一次进入自动刷新
        if (isFirstIn) {
            ptrl.autoRefresh();
            isFirstIn = false;
        }
    }

    /**
     * ListView初始化方法
     */
    private void initListView() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            items.add("这里是item " + i);
        }
        SecListAdapter adapter = new SecListAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText( SecListViewActivity.this,"LongClick on "+ parent.getAdapter().getItemId(position),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Toast.makeText(SecListViewActivity.this," Click on " + parent.getAdapter().getItemId(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        // 加载操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件加载完毕了哦！
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }


//    public class MyListener implements PullToRefreshLayout.OnRefreshListener {
//
//        @Override
//        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
//            // 下拉刷新操作
//            new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    // 千万别忘了告诉控件刷新完毕了哦！
//                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//                }
//            }.sendEmptyMessageDelayed(0, 5000);
//        }
//
//        @Override
//        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
//            // 加载操作
//            new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    // 千万别忘了告诉控件加载完毕了哦！
//                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//                }
//            }.sendEmptyMessageDelayed(0, 5000);
//        }
//
//    }
}
