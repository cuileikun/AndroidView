package com.gitstudy.xrcycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.gitstudy.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class LinearActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private MyMultiTypeAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

//        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);

//        mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
//        mRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");


        // if you use setFooterView,the default footerView will unUseful
//        TextView tv = new TextView(this);
//        tv.setText("自定义 footer");
//        mRecyclerView.setFootView(tv, new CustomFooterViewCallBack() {
//            @Override
//            public void onLoadingMore(View yourFooterView) {
//
//            }
//
//            @Override
//            public void onLoadMoreComplete(View yourFooterView) {
//
//            }
//
//            @Override
//            public void onSetNoMore(View yourFooterView, boolean noMore) {
//
//            }
//        });

        final int itemLimit = 10;

        // When the item number of the screen number is list.size-2,we call the onLoadMore
//        mRecyclerView.setLimitNumberToCallLoadMore(2);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        listData.clear();
                        for(int i = 0; i < itemLimit ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.e("aaaaa","call onLoadMore");
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new  ArrayList<String>();
        mAdapter = new MyMultiTypeAdapter(listData);
//        mAdapter.setClickCallBack(
//                new MyMultiTypeAdapter.ItemClickCallBack() {
//                    @Override
//                    public void onItemClick(int pos) {
//                        // a demo for notifyItemRemoved
//                        listData.remove(pos);
////                        mRecyclerView.notifyItemRemoved(listData,pos);
//                    }
//                }
//        );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
