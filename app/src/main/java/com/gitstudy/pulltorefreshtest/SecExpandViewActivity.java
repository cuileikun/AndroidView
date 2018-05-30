package com.gitstudy.pulltorefreshtest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitstudy.R;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullToRefreshLayout;

public class SecExpandViewActivity extends AppCompatActivity implements PullToRefreshLayout.OnRefreshListener {
    private ExpandableListView expandableListView;
    ;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_expand_view);
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
//        ptrl.setOnRefreshListener(new MyListener());
        ptrl.setOnRefreshListener(this);
        expandableListView = (ExpandableListView) findViewById(R.id.content_view);
        initExpandableListView();
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

    private void initExpandableListView() {
        expandableListView.setAdapter(new ExpandableListAdapter(this));
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
                Toast.makeText( SecExpandViewActivity.this, " Click on group " + groupPosition + " item " + childPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,  View view, int position, long id) {
                        Toast.makeText( SecExpandViewActivity.this,"LongClick on "+ parent.getAdapter().getItemId(position), Toast.LENGTH_SHORT) .show();
                        return true;
                    }
                });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    // 如果展开则关闭
                    parent.collapseGroup(groupPosition);
                } else {
                    // 如果关闭则打开，注意这里是手动打开不要默认滚动否则会有bug
                    parent.expandGroup(groupPosition);
                }
                return true;
            }
        });
    }

    class ExpandableListAdapter extends BaseExpandableListAdapter {
        private String[] groupsStrings;// = new String[] { "这里是group 0",
        // "这里是group 1", "这里是group 2" };
        private String[][] groupItems;
        private Context context;

        public ExpandableListAdapter(Context context) {
            this.context = context;
            groupsStrings = new String[8];
            for (int i = 0; i < groupsStrings.length; i++) {
                groupsStrings[i] = new String("这里是group " + i);
            }
            groupItems = new String[8][8];
            for (int i = 0; i < groupItems.length; i++)
                for (int j = 0; j < groupItems[i].length; j++) {
                    groupItems[i][j] = new String("这里是group " + i + "里的item "
                            + j);
                }
        }

        @Override
        public int getGroupCount() {
            return groupsStrings.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groupItems[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupsStrings[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return groupItems[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.list_item_layout, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(groupsStrings[groupPosition]);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.list_item_layout, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(groupItems[groupPosition][childPosition]);
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

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
