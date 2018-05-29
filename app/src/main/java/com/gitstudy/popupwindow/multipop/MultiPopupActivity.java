package com.gitstudy.popupwindow.multipop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiPopupActivity extends AppCompatActivity {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"审批状态", "租赁类型", "经营主体", "主协办"};
    private List<View> popupViews = new ArrayList<>();

    private ListDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ListDropDownAdapter constellationAdapter;

    private String citys[] = {"不限", "审批中", "审批通过", "再议", "否决", "撤销中", "已撤销"};
    private String ages[] = {"不限", "融资租赁直租", "经营租赁回租", "经营租赁回租", "融资租赁直租"};
    private String sexs[] = {"不限", "本部", "境内spv", "境外spv"};
    private String constellations[] = {"不限", "我主办", "我协办"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_popup);
        ButterKnife.bind(this);
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MultiPopupActivity.this, OriginActivity.class));
            }
        });
        initView();
    }

    private void initView() {

        //init city menu
        final ListView cityView = new ListView(this);
        cityView.setDividerHeight(0);
        cityAdapter = new ListDropDownAdapter(this, Arrays.asList(citys));
        cityView.setAdapter(cityAdapter);


        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation

        final ListView constellationView = new ListView(this);
        constellationView.setDividerHeight(0);
        constellationAdapter = new ListDropDownAdapter(this, Arrays.asList(constellations));
        constellationView.setAdapter(constellationAdapter);

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
//                flstatus.setText(Html.fromHtml("审批状态:" + "<font color='#ff0000'>" + str + "</font>"));
                String city = citys[position];
//                String spanned = String.valueOf(Html.fromHtml("<font color='#ff0000'>" + city + "</font>"));
                if (position == 0) {
                    mDropDownMenu.setTabText(headers[0]);
                } else {
                    mDropDownMenu.setText(Html.fromHtml("" + "<font color='#bf1932'>" + city + "</font>"));
                }


                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(position == 0 ? headers[3] : constellations[position]);
                mDropDownMenu.closeMenu();
            }
        });

        //将布局文件转化成view对象
        View view = View.inflate(this, R.layout.layout_below_pop, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView textView1 = (TextView) view.findViewById(R.id.text1);
        textView1.setText("pup下面的第一行文字");

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


}
