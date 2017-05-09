package com.qk.applibrary.util;

import android.util.Log;

import com.qk.applibrary.BuildConfig;


/**
 * 版权所有：青客 保留所有权利
 * 创建日期：2015-07-29 10:17
 * 创建作者：刘继凤
 * 文件名称：L.java
 * 版本：
 * 功能：日志输出
 * 最后修改时间：
 * 修改记录：
 */
public class L {
	public static void e(String tag,String msg){
		if(BuildConfig.DEBUG){
			try{
				Log.e(tag, msg);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void w(String tag, String msg,Throwable ex) {
		if(BuildConfig.DEBUG){
			try{
				Log.w(tag, msg, ex);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void w(String tag, String msg) {
		if(BuildConfig.DEBUG){
			try{
				Log.w(tag, msg);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void i(String tag, String msg) {
		if(BuildConfig.DEBUG){
			try{
				Log.i(tag, msg);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
  