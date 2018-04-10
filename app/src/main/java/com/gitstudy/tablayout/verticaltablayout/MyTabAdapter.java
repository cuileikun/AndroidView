package com.gitstudy.tablayout.verticaltablayout;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/4/10.
 */
public class MyTabAdapter implements TabAdapter {
    private Context mContext;
    private List<String> titles;

    {
        titles = new ArrayList<>();
        Collections.addAll(titles, "常用网站", "个人博客", "公司博客", "开发社区", "常用工具", "在线学习", "开放平台", "互联网资讯", "求职招聘", "应用加固",
                "三方支付", "推送平台", "三方分享", "地图平台", "直播sdk", "IM即时通讯", "Bug管理", "后端云", "WebView内核", "创意&素材", "互联网统计", "快速开发"
        );
    }

    public MyTabAdapter(VerticalTablayoutActivity verticalTablayoutActivity) {
        this.mContext = verticalTablayoutActivity;

    }

    @Override
    public int getCount() {
        return 22;
    }

    @Override
    public int getBadge(int position) {
        if (position == 22) return position;
        return 0;
    }

    @Override
    public QTabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public QTabView.TabTitle getTitle(int position) {
        return new QTabView.TabTitle.Builder(mContext).
                setContent(titles.get(position)).
                setTextColor(Color.GREEN, Color.BLACK).build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }
}
