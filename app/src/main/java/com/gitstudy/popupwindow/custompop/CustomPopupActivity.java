package com.gitstudy.popupwindow.custompop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.gitstudy.R;
import com.gitstudy.utils.CommonUtil;
import com.gitstudy.utils.ToastUtils;

public class CustomPopupActivity extends AppCompatActivity {
    private Context mContext;
    public PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_popup);
        mContext = CustomPopupActivity.this;
        initPop();
    }

    private void initPop() {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.fragment_pop, null);
        popupWindow = new PopupWindow(view1, CommonUtil.dp2px(mContext, 80), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        // setBackgroundAlpha(0.5f);//设置屏幕透明度

        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        view1.findViewById(R.id.t1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(mContext, "t1被点击了");
                popupWindow.dismiss();
            }
        });
        view1.findViewById(R.id.t2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(mContext, "t2被点击了");
                popupWindow.dismiss();
            }
        });
        view1.findViewById(R.id.t3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(mContext, "t3被点击了");
                popupWindow.dismiss();
            }
        });
        final ImageView rBtn = (ImageView) findViewById(R.id.search_tv);
        findViewById(R.id.search_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAsDropDown(rBtn, CommonUtil.dp2px(mContext, 0), CommonUtil.dp2px(mContext, 6));

            }
        });
    }
}
