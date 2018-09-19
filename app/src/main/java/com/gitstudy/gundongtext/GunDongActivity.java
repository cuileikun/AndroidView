package com.gitstudy.gundongtext;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.gitstudy.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GunDongActivity extends AppCompatActivity {
    private Context mContext;
    @BindView(R.id.ll_carousel_container)
    LinearLayout mCarouselContainer;
    @BindView(R.id.dollar_carsousel_container)
    LinearLayout mDollarlContainer;
    @BindView(R.id.libor_carsousel_container)
    LinearLayout mLiborContainer;
    @BindView(R.id.shibor_carsousel_container)
    LinearLayout mShiborContainer;
    private CarouselHolder carouselHolder;
    private CarouselHolder dollarHolder;
    private CarouselHolder shiborlHolder;
    private CarouselHolder liborHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gun_dong);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mContext = GunDongActivity.this;
        initCarouselHolder();
        showCarouselView();
    }

    /***
     * 暂时使用本地数据来显示轮播控件  接口好了之后
     * carouselHolder.setData(tipsHolderBeans);
     * dollarHolder.setData(data1);
     * shiborlHolder.setData(data2);
     * liborHolder.setData(data3);
     * 需要放到数据获取成功的地方
     */
    private void showCarouselView() {
        ArrayList<TipsHolderBean> tipsHolderBeans = initLocalCarouselData();
        ArrayList<TipsHolderBean> data1 = initLocalShiborData("美元");
        ArrayList<TipsHolderBean> data2 = initLocalShiborData("ShiBor");
        ArrayList<TipsHolderBean> data3 = initLocalShiborData("Libor");
        carouselHolder.setData(tipsHolderBeans);
        dollarHolder.setData(data1);
        shiborlHolder.setData(data2);
        liborHolder.setData(data3);
    }

    /***
     * 初始化轮播控件
     * 包括通知公告 资讯
     */
    private void initCarouselHolder() {
        carouselHolder = new CarouselHolder(mContext, CarouselHolder.TipsType.SINGLE);
        dollarHolder = new CarouselHolder(mContext, CarouselHolder.TipsType.DOUBLE, "美元");
        shiborlHolder = new CarouselHolder(mContext, CarouselHolder.TipsType.DOUBLE, "shibor");
        liborHolder = new CarouselHolder(mContext, CarouselHolder.TipsType.DOUBLE, "libor");
        initHolder(carouselHolder, mCarouselContainer);
        initHolder(dollarHolder, mDollarlContainer);
        initHolder(shiborlHolder, mShiborContainer);
        initHolder(liborHolder, mLiborContainer);
    }

    private void initHolder(CarouselHolder holder, LinearLayout container) {
        holder.setHolderInterval(3000);
        container.removeAllViews();
        container.addView(holder.getRootView());
    }

    //TODO 测试用 需要删除
    private ArrayList<TipsHolderBean> initLocalCarouselData() {
        ArrayList<TipsHolderBean> mData = new ArrayList<>();
        NewMsgItmeBean bean1 = new NewMsgItmeBean();
        bean1.setMSG_TLE("测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1测试的标题1");
        bean1.setPIC_URL("http://youtu.qq.com/app/img/experience/char_general/ocr_common09.jpg");
        mData.add(bean1);
        NewMsgItmeBean bean2 = new NewMsgItmeBean();
        bean2.setMSG_TLE("测试的标题2");
        bean2.setPIC_URL("http://youtu.qq.com/app/img/experience/char_general/icon_id_01.jpg");
        mData.add(bean2);
        NewMsgItmeBean bean3 = new NewMsgItmeBean();
        bean3.setMSG_TLE("测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3测试的标题3");
        bean3.setPIC_URL("http://youtu.qq.com/app/img/experience/char_general/ocr_common09.jpg");
        mData.add(bean3);
        NewMsgItmeBean bean4 = new NewMsgItmeBean();
        bean4.setMSG_TLE("测试的标题4测试的标题4测试的标题4测试的标题4");
        bean4.setPIC_URL("http://youtu.qq.com/app/img/experience/image/icon_imag_01.jpg");
        mData.add(bean4);
        return mData;
    }

    //TODO 测试用 需要删除
    private ArrayList<TipsHolderBean> initLocalShiborData(String type) {
        ArrayList<TipsHolderBean> mData = new ArrayList<>();
        NewTestBean bean21 = new NewTestBean();
        bean21.setUpText(type + "上面的标题1");
        bean21.setDownText(type + "下面的标题1");
        mData.add(bean21);
        NewTestBean bean22 = new NewTestBean();
        bean22.setUpText(type + "上面的标题2");
        bean22.setDownText(type + "下面的标题2");
        mData.add(bean22);
        NewTestBean bean23 = new NewTestBean();
        bean23.setUpText(type + "上面的标题3");
        bean23.setDownText(type + "下面的标题3");
        mData.add(bean23);
        return mData;
    }
}
