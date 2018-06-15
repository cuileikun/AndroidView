package com.gitstudy.html;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * html参考链接：https://blog.csdn.net/sunchaohui5741/article/details/79023525
 * 包含html控制字体的一般用法及特殊用法
 *
 * TextView设置图片参考链接：https://blog.csdn.net/hwe_xc/article/details/50906220
 */
public class HtmlTestActivity extends AppCompatActivity {
    @BindView(R.id.tv_rmb_amt)
    TextView mTvRmbAmt;
    @BindView(R.id.tv_rmb_rte)
    TextView mTvRmbRte;
    @BindView(R.id.tv_address)
    TextView address;
    private String rmbAmtSmall;
    private Spanned rmbAmtSmallSpanned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_test);
        ButterKnife.bind(this);

        firstMethod();
        secondMethod();
    }

    private void secondMethod() {
        address.setText(Html.fromHtml("上海浦东的" + "<font color='#ff0000'>" + "(预约)" + "</font>"));
    }

    private void firstMethod() {
        //     2,151.80(百万)
        String mRmbAmt = "2,151.80";
        if (null != mRmbAmt && !TextUtils.isEmpty(mRmbAmt)) {
            String rmbSymbol = "<myfont color='#ff0000' size='33px'>" + "¥ " + "</myfont>";
            Spanned SymbolSpanned = Html.fromHtml(rmbSymbol, null, new HtmlTagHandler("myfont"));
            if (mRmbAmt.contains(".")) {
                String rmbAmt = "<myfont color='#000000' size='45px'>" + mRmbAmt.substring(0, mRmbAmt.indexOf(".")) + "</myfont>";
                Spanned rmbAmtSpanned = Html.fromHtml(rmbAmt, null, new HtmlTagHandler("myfont"));

                if (null != "百万" && !TextUtils.isEmpty("百万")) {
                    rmbAmtSmall = "<myfont color='#000000' size='33px'>" + mRmbAmt.substring(mRmbAmt.indexOf("."), mRmbAmt.length()) + "(" + "百万" + ")" + "</myfont>";
                    rmbAmtSmallSpanned = Html.fromHtml(rmbAmtSmall, null, new HtmlTagHandler("myfont"));
                } else {
                    rmbAmtSmall = "<myfont color='#000000' size='33px'>" + mRmbAmt.substring(mRmbAmt.indexOf("."), mRmbAmt.length()) + "百万" + "</myfont>";
                    rmbAmtSmallSpanned = Html.fromHtml(rmbAmtSmall, null, new HtmlTagHandler("myfont"));
                }
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(SymbolSpanned).append(rmbAmtSpanned).append(rmbAmtSmallSpanned);
                mTvRmbAmt.setText(builder);
            }
            String mRmbArw = "UP";
            if (null != mRmbArw && !TextUtils.isEmpty(mRmbArw)) {
                if (mRmbArw.equals("UP")) {
                    mTvRmbRte.setTextColor(Color.parseColor("#bd102b"));
                    mTvRmbRte.setText("同比 " + "70.09");
                    Drawable drawable = getResources().getDrawable(R.drawable.business_up);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvRmbRte.setCompoundDrawables(null, null, drawable, null);
                } else if (mRmbArw.equals("DOWN")) {
                    mTvRmbRte.setTextColor(Color.parseColor("#53a893"));
                    mTvRmbRte.setText("同比 " + "70.09");
                    Drawable drawable = getResources().getDrawable(R.drawable.business_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvRmbRte.setCompoundDrawables(null, null, drawable, null);
                }
            } else {
                mTvRmbRte.setTextColor(Color.parseColor("#000000"));
                mTvRmbRte.setText("同比 " + "70.09");
            }
        }
    }


}
