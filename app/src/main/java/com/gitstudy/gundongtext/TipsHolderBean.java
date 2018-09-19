package com.gitstudy.gundongtext;

/**
 * Created by zhudong on 2018/9/14.
 */

public interface  TipsHolderBean {

    public  interface AnnounceHolderBean extends TipsHolderBean {
        //通知公告 获取公告标题
        String getTipText();

        //通知公告 获取公告图片地址
        String getImgUrl();


    }
    public  interface ShiBorHolderBean extends TipsHolderBean {
        //资讯 获取上面文本数据
        String getUpTipText();

        //资讯 获取上面文本数据
        String getDownTipText();
    }
}
