package com.gitstudy.gundongtext;

/**
 * Created by zhudong on 2018/9/14.
 */

/***
 *
 *  TODO 暂无资讯的数据接口 本地写的测试bean代替显示 需要删除
 */
public class NewTestBean implements TipsHolderBean.ShiBorHolderBean {
    private  String upText="";
    private String downText="";

    public String getUpText() {
        return upText;
    }

    public void setUpText(String upText) {
        this.upText = upText;
    }

    public String getDownText() {
        return downText;
    }

    public void setDownText(String downText) {
        this.downText = downText;
    }

    @Override
    public String getUpTipText() {
        return getUpText();
    }

    @Override
    public String getDownTipText() {
        return getDownText();
    }
}