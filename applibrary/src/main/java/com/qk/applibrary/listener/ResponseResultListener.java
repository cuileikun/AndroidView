package com.qk.applibrary.listener;

import com.qk.applibrary.bean.ResponseResult;

/**
 * 服务器结果回调
 * @author benhua
 *
 */
public abstract class ResponseResultListener {			
	public abstract void onResult(ResponseResult result);
}
