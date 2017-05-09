package com.qk.applibrary.listener;

import com.qk.applibrary.bean.ResponseResult;

/**
 * Created by acer on 2016-5-12.
 * 检查重复登录,要回调接口
 */
public interface CheckRepeatLoginCallbackInterface {
    /**
     * 没有重新登录
     */
    public void noRepeatLogin(ResponseResult result);

}
